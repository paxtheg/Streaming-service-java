package actions;

import app.BackendApplication;
import data.Credentials;
import data.User;

import java.util.ArrayList;
import java.util.Optional;

public final class RegisterAction extends OnPageAction {
    private final Credentials credentials;

    public RegisterAction(final String type, final String page,
                          final String feature, final Credentials credentials) {
        super(type, page, feature);
        this.credentials = credentials;
    }


    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("register")) {
            Optional<User> user = app.getUserByName(credentials.getName());

            if (user.isEmpty()) {
                User newUser = app.addUserWithCredentials(credentials);
                app.setCurrentPage("Homepage autentificat");
                app.setCurrentUser(newUser);
                return new app.Error(null, new ArrayList<>(), app.getCurrentUser());
            }
            return new app.Error("Error", new ArrayList<>(), null);
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
