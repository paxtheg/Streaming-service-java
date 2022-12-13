package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"ratings"})
public final class Movie implements Cloneable {
    private String name;
    private Integer year;
    private Integer duration;
    private List<String> genres;
    private List<String> actors;
    private List<String> countriesBanned;

    private Integer numLikes = 0;
    private BigDecimal rating;

    {
        rating = new BigDecimal(0).setScale(2, RoundingMode.UNNECESSARY);
    }

    private Integer numRatings = 0;

    private final List<Integer> ratings = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(final List<String> genres) {
        this.genres = genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(final List<String> actors) {
        this.actors = actors;
    }

    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void incrementNumRatings() {
        numRatings = numRatings + 1;
    }

    public void setNumRatings(final Integer numRatings) {
        this.numRatings = numRatings;
    }

    @JsonRawValue
    public String getRating() {
        return rating.toString();
    }

    public void setRating(final Double rating) {
        this.rating = new BigDecimal(rating).setScale(2, RoundingMode.UNNECESSARY);
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Movie movie = new Movie();
        movie.name = name;
        movie.year = year;
        movie.duration = duration;
        movie.genres = genres;
        movie.countriesBanned = countriesBanned;
        movie.actors = actors;
        movie.numLikes = numLikes;
        movie.rating = rating;
        movie.ratings.addAll(ratings);
        movie.numRatings = numRatings;
        return movie;
    }
}
