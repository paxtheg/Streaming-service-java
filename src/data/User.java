package data;

import java.util.ArrayList;
import java.util.List;

public final class User implements Cloneable {
    private Credentials credentials;

    private int tokensCount;
    private final int freePremium = 15;
    private int numFreePremiumMovies = freePremium;

    private List<Movie> purchasedMovies = new ArrayList<>();
    private List<Movie> watchedMovies = new ArrayList<>();
    private List<Movie> likedMovies = new ArrayList<>();
    private List<Movie> ratedMovies = new ArrayList<>();

    public User(final Credentials credentials) {
        this.credentials = credentials;
    }

    public User() {

    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public List<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final List<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final List<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public List<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final List<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final List<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        User user = new User();

        user.credentials = (Credentials) credentials.clone();
        user.tokensCount = tokensCount;
        user.numFreePremiumMovies = numFreePremiumMovies;
        for (Movie movie : purchasedMovies) {
            user.purchasedMovies.add((Movie) movie.clone());
        }
        for (Movie movie : watchedMovies) {
            user.watchedMovies.add((Movie) movie.clone());
        }
        for (Movie movie : likedMovies) {
            user.likedMovies.add((Movie) movie.clone());
        }
        for (Movie movie : ratedMovies) {
            user.ratedMovies.add((Movie) movie.clone());
        }
        return user;
    }
}

