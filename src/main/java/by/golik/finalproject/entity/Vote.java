package by.golik.finalproject.entity;

import java.util.Objects;

/**
 * entity represents rating of movie
 * @author Nikita Golik
 */
public class Vote {
    /**
     * name of user
     */
    private String userName;
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

    public Vote() {
    }


    public int getMovieID() {
        return movieID;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        Vote vote = (Vote) o;
        return movieID == vote.movieID &&
                userID == vote.userID &&
                score == vote.score;
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
