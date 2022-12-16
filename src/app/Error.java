package app;

import data.Movie;
import data.User;

import java.util.List;

public final class Error {
    //used for saving the output of actions
    private String error;
    private List<Movie> currentMoviesList;
    private User currentUser;

    public Error(final String error, final List<Movie> currentMoviesList, final User currentUser) {
        this.error = error;
        this.currentMoviesList = currentMoviesList;
        this.currentUser = currentUser;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final List<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
}
