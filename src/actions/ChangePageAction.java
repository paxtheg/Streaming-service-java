package actions;

import app.BackendApplication;
import data.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ChangePageAction extends Action {
    private final String movie;

    public ChangePageAction(final String type, final String page, final String movie) {
        super(type, page);
        this.movie = movie;
    }

    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        List<String> correctTransitions = app.getCorrectTransitions();

        if (correctTransitions.contains(getPage())) {
            switch (getPage()) {
                case "logout" -> {
                    app.setFilteredMovies(app.getMovies());
                    app.setCurrentPage("Homepage neautentificat");
                    app.setCurrentUser(null);
                    return null;
                }
                case "movies" -> {
                    app.setFilteredMovies(app.getMovies());
                    app.setCurrentPage(getPage());
                    return new app.Error(null, app.getAvailableMovies(), app.getCurrentUser());
                }
                case "see details" -> {
                    Optional<Movie> m = app.getMovieByName(movie);
                    if (m.isPresent() && app.getAvailableMovies().contains(m.get())) {
                        app.setCurrentPage(getPage());
                        app.setCurrentMovie(m.get());
                        return new app.Error(null, new ArrayList<>() {{
                            add(m.get());
                        }}, app.getCurrentUser());
                    }
                    return new app.Error("Error", new ArrayList<>(), null);

                }
                default -> {
                    app.setCurrentPage(getPage());
                    return null;
                }
            }
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
