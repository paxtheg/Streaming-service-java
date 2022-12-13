package actions;

import app.BackendApplication;
import data.Movie;
import data.User;

import java.util.ArrayList;
import java.util.List;

public final class RateAction extends OnPageAction {
    private final int minRate = 1;
    private final int maxRate = 5;
    private final int rate;

    public RateAction(final String type, final String page, final String feature, final int rate) {
        super(type, page, feature);
        this.rate = rate;
    }

    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("see details")) {
            User currentUser = app.getCurrentUser();
            Movie movie = app.getCurrentMovie();
            if (rate < minRate | rate > maxRate)
                return new app.Error("Error", new ArrayList<>(), null);
            if (currentUser.getWatchedMovies().contains(movie)) {
                movie.incrementNumRatings();
                movie.getRatings().add(rate);

                List<Integer> ratings = movie.getRatings();
                double avg = 0.0;
                for (Integer rating : ratings) {
                    avg += rating;
                }
                avg /= ratings.size();
                movie.setRating(avg);
                currentUser.getRatedMovies().add(movie);
                return new app.Error(null, new ArrayList<>() {{
                    add(movie);
                }}, currentUser);
            }
            return new app.Error("Error", new ArrayList<>(), null);
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
