package actions;

import app.BackendApplication;
import data.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FilterAction extends OnPageAction {
    private final Filter filter;

    public FilterAction(final String type, final String page,
                        final String feature, final Filter filter) {
        super(type, page, feature);
        this.filter = filter;
    }

    @Override
    public app.Error apply() {
        BackendApplication app = BackendApplication.getApp();
        String currentPage = app.getCurrentPage();

        if (currentPage.equals("movies")) {
            app.setFilteredMovies(app.getMovies());
            List<Movie> movies = new ArrayList<>(app.getAvailableMovies());

            if (filter.getContains() != null) {
                List<String> actorSort = filter.getContains().get("actors");
                List<String> genreSort = filter.getContains().get("genre");

                if (actorSort != null) {
                    movies = new ArrayList<>(movies.stream().filter(m -> !Collections.
                            disjoint(m.getActors(), actorSort)).toList());
                }
                if (genreSort != null) {
                    movies = new ArrayList<>(movies.stream().filter(m -> !Collections.
                            disjoint(m.getGenres(), genreSort)).toList());
                }
            }

            if (filter.getSort() != null) {
                SortType durationSort = filter.getSort().get("duration");
                SortType ratingSort = filter.getSort().get("rating");
                movies.sort((m1, m2) -> {
                    if (durationSort != null && ratingSort != null) {
                        if (m1.getDuration().compareTo(m2.getDuration()) == 0) {
                            if (ratingSort == SortType.decreasing) {
                                return m2.getRating().compareTo(m1.getRating());
                            } else {
                                return m1.getRating().compareTo(m2.getRating());
                            }
                        } else {
                            if (durationSort == SortType.increasing) {
                                return m1.getDuration().compareTo(m2.getDuration());
                            } else {
                                return m2.getDuration().compareTo(m1.getDuration());
                            }
                        }
                    } else if (durationSort != null) {
                        if (durationSort == SortType.increasing) {
                            return m1.getDuration().compareTo(m2.getDuration());
                        } else {
                            return m2.getDuration().compareTo(m1.getDuration());
                        }
                    } else {
                        if (ratingSort == SortType.decreasing) {
                            return m2.getRating().compareTo(m1.getRating());
                        } else {
                            return m1.getRating().compareTo(m2.getRating());
                        }
                    }
                });
            }
            app.setFilteredMovies(movies);
            return new app.Error(null, movies, app.getCurrentUser());
        }
        return new app.Error("Error", new ArrayList<>(), null);
    }
}
