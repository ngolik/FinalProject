package by.golik.finalproject.entity;

import java.util.Objects;

/**
 * @author Nikita Golik
 */
public class Votes {
    /**
     * id of movie
     */
    private int movieID;
    /**
     * id of user
     */
    private int userID;
    /**
     * 1-10 score
     */
    private int score;

    public Votes() {
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Votes votes = (Votes) o;
        return movieID == votes.movieID &&
                userID == votes.userID &&
                score == votes.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieID, userID, score);
    }

    @Override
    public String toString() {
        return "Votes{" +
                "movieID=" + movieID +
                ", userID=" + userID +
                ", score=" + score +
                '}';
    }
}
