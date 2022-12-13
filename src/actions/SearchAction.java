package actions;

import app.BackendApplication;
import data.Movie;

import java.util.ArrayList;
import java.util.List;

public final class SearchAction extends OnPageAction {
    private final String startsWith;

    public SearchAction(final String type, final String page,
                        final String feature, final String startsWith) {
        super(type, page, feature);
        this.startsWith = startsWith;
    }


    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("movies")) {
            app.setFilteredMovies(app.getMovies());
            List<Movie> movies = app.getAvailableMovies();
            List<Movie> filtered = movies.stream().filter(m -> m.
                    getName().startsWith(startsWith)).toList();
            app.setFilteredMovies(filtered);
            return new app.Error(null, filtered, app.getCurrentUser());
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
