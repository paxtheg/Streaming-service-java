package actions;

import app.BackendApplication;
import data.Movie;
import data.User;

import java.util.ArrayList;

public final class LikeAction extends OnPageAction {
    public LikeAction(final String type, final String page, final String feature) {
        super(type, page, feature);
    }

    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("see details")) {
            User currentUser = app.getCurrentUser();
            Movie movie = app.getCurrentMovie();

            if (currentUser.getWatchedMovies().contains(movie)) {
                currentUser.getLikedMovies().add(movie);
                movie.setNumLikes(movie.getNumLikes() + 1);
                return new app.Error(null, new ArrayList<>() {{
                    add(movie);
                }}, currentUser);
            }
            return new app.Error("Error", new ArrayList<>(), null);
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
