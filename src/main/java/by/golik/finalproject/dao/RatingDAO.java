package by.golik.finalproject.dao;

import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.entity.Rating;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface RatingDAO {
    List<Rating> getRatingForMovie(int id);
    void addRating(int movieId, String userName, int rating);
    void updateRating(int movieId, String userName, int rating);
    void deleteRating(int movieId, String userName);
}
