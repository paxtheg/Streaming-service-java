package actions;

import app.BackendApplication;
import data.AccountType;
import data.Movie;
import data.User;

import java.util.ArrayList;

public final class PurchaseAction extends OnPageAction {
    public PurchaseAction(final String type, final String page, final String feature) {
        super(type, page, feature);
    }

    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("see details")) {
            User currentUser = app.getCurrentUser();
            Movie movie = app.getCurrentMovie();

            if (currentUser.getPurchasedMovies().contains(movie)) {
                return new app.Error("Error", new ArrayList<>(), null);
            }

            if (currentUser.getCredentials().getAccountType() == AccountType.premium) {
                int numFree = currentUser.getNumFreePremiumMovies();
                if (numFree > 0) {
                    currentUser.setNumFreePremiumMovies(numFree - 1);

                    currentUser.getPurchasedMovies().add(movie);
                    return new app.Error(null, new ArrayList<>() {{
                        add(movie);
                    }}, currentUser);

                } else if (currentUser.getTokensCount() >= 2) {
                    currentUser.setTokensCount(currentUser.getTokensCount() - 2);
                    currentUser.getPurchasedMovies().add(movie);

                    return new app.Error(null, new ArrayList<>() {{
                        add(movie);
                    }}, currentUser);
                }
                return new app.Error("Error", new ArrayList<>(), null);
            } else {
                if (currentUser.getTokensCount() >= 2) {
                    currentUser.setTokensCount(currentUser.getTokensCount() - 2);
                    currentUser.getPurchasedMovies().add(movie);

                    return new app.Error(null, new ArrayList<>() {{
                        add(movie);
                    }}, currentUser);
                }
                return new app.Error("Error", new ArrayList<>(), null);
            }
        }

        return new app.Error("Error", new ArrayList<>(), null);
    }
}
