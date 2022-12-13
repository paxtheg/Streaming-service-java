package actions;

import app.BackendApplication;
import data.Credentials;
import data.User;

import java.util.ArrayList;
import java.util.Optional;

public final class LoginAction extends OnPageAction {
    private final Credentials credentials;

    public LoginAction(final String type, final String page, final String feature, final Credentials credentials) {
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

        if (currentPage.equals("login")) {
            Optional<User> user = app.getUserByCredentials(credentials);

            if (user.isEmpty()) {
                BackendApplication.getApp().setCurrentPage("Homepage neautentificat");
                return new app.Error("Error", new ArrayList<>(), null);
            }
            BackendApplication.getApp().setCurrentPage("Homepage autentificat");
            BackendApplication.getApp().setCurrentUser(user.get());
            return new app.Error(null, new ArrayList<>(), app.getCurrentUser());
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
