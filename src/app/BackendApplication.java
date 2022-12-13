package app;

import actions.Action;
import actions.ActionFactory;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import data.Credentials;
import data.Movie;
import data.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public final class BackendApplication {
    private static BackendApplication app = null;

    private List<User> users;
    private List<Movie> movies;
    private List<Movie> filteredMovies;

    private List<Action> actions;

    private List<Error> errors;
    private final HashMap<String, List<String>> correctTransitions;

    private String currentPage;
    private User currentUser;
    private Movie currentMovie;

    private BackendApplication() {
        errors = new ArrayList<>();
        currentPage = "Homepage neautentificat";

        correctTransitions = new HashMap<>();
        correctTransitions.put("Homepage neautentificat", new ArrayList<>() {
            {
                add("login");
                add("register");
            }
        });
        correctTransitions.put("Homepage autentificat", new ArrayList<>() {
            {
                add("movies");
                add("upgrades");
                add("logout");
            }
        });
        correctTransitions.put("movies", new ArrayList<>() {
            {
                add("Homepage autentificat");
                add("search");
                add("filter");
                add("movies");
                add("see details");
                add("logout");
            }
        });

        correctTransitions.put("upgrades", new ArrayList<>() {
            {
                add("Homepage autentificat");
                add("movies");
                add("logout");
            }
        });

        correctTransitions.put("see details", new ArrayList<>() {
            {
                add("Homepage autentificat");
                add("movies");
                add("upgrades");
                add("logout");
            }
        });
    }

    public static BackendApplication getApp() {
        if (app == null) {
            app = new BackendApplication();
        }
        return app;
    }

    public void reset() {
        errors = new ArrayList<>();
        currentPage = "Homepage neautentificat";
        currentUser = null;
        users.clear();
        movies.clear();
        actions.clear();
    }

    public List<User> getUsers() {
        return users;
    }

    public User addUserWithCredentials(final Credentials credentials) {
        User user = new User(credentials);
        users.add(user);
        return user;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Optional<Movie> getMovieByName(String name) {
        return movies.stream().filter(m -> m.getName().equals(name)).findFirst();
    }

    public List<Movie> getAvailableMovies() {
        return filteredMovies.stream().filter(m -> !m.getCountriesBanned().contains(currentUser.getCredentials().getCountry())).toList();
    }

    public void setFilteredMovies(final List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public List<Action> getActions() {
        return actions;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentMovie(final Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public List<String> getCorrectTransitions() {
        return correctTransitions.get(currentPage);
    }

    public Optional<User> getUserByName(final String name) {
        return users.stream().filter(u -> u.getCredentials().getName().equals(name)).findFirst();

    }

    public Optional<User> getUserByCredentials(final Credentials credentials) {
        return users.stream().filter(u -> u.getCredentials().equals(credentials)).findFirst();
    }

    public void startBackend(final String inputPath, final String outputPath) throws CloneNotSupportedException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File inputFile = new File(inputPath);

            DataParser data = mapper.readValue(inputFile, DataParser.class);

            users = data.getUsers();
            movies = data.getMovies();
            filteredMovies = movies;
            actions = ActionFactory.createActions(data.getActions());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Action action : actions) {
            app.Error error = action.apply();
            if (error != null) {
                if (error.getCurrentUser() != null)
                    error.setCurrentUser((User) error.getCurrentUser().clone());
                List<Movie> copies = new ArrayList<>();
                for (Movie movie : error.getCurrentMoviesList()) {
                    copies.add((Movie) movie.clone());
                }
                error.setCurrentMoviesList(copies);
                errors.add(error);
            }
        }

        mapper = new ObjectMapper();

        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            File outputFile = new File(outputPath);
            writer.writeValue(outputFile, errors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
