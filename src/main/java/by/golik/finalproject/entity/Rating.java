package by.golik.finalproject.entity;

import java.util.Objects;

/**
 * @author Nikita Golik
 */
public class Rating {
    public int movieID;
    private String userName;
    private int ratingScore;

    public Rating() {
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;

        if (movieID != rating.movieID) return false;
        if (ratingScore != rating.ratingScore) return false;
        return Objects.equals(userName, rating.userName);
    }

    @Override
    public int hashCode() {
        int result = movieID;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + ratingScore;
        return result;
    }
}
