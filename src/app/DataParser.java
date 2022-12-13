package app;

import actions.ActionData;
import data.Movie;
import data.User;

import java.util.List;

public final class DataParser {
    private List<User> users;
    private List<Movie> movies;
    private List<ActionData> actions;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final List<Movie> movies) {
        this.movies = movies;
    }

    public List<ActionData> getActions() {
        return actions;
    }

    public void setActions(final List<ActionData> actions) {
        this.actions = actions;
    }
}
