package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Vote;
import java.util.List;

/**
 * @author Nikita Golik
 */
public interface RatingDAO {
    /**
     * This method is used to get Rating for a movie.
     *
     * @param movieId movieId of movie
     * @return list of filled rating beans
     * @throws DAOException if some error occurred while processing data.
     */
    Vote getRatingForMovie(int movieId) throws DAOException;

    /**
     * This method is used to get rating user gave to any movies.
     *
     * @param userName of user
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Vote> getRatingFromUser(String userName) throws DAOException;

    /**
     * This method is used to check if there is an entry of this user for this movie.
     *
     * @param intMovieID   movie id
     * @param userName user nickname
     * @return filled rating bean
     * @throws DAOException if some error occurred while processing data.
     */
    Vote checkRating(int intMovieID, String userName) throws DAOException;

    /**
     * This method is used to add rating of some user for some movie.
     *
     * @param intMovieID   id of movie
     * @param userName nickname of user
     * @param rating       user gave
     * @throws DAOException if some error occurred while processing data.
     */
    void createRating(int intMovieID, String userName, int rating) throws DAOException;

    /**
     * This method is used to update rating some user gave to some movie in data source.
     *
     * @param intMovieID   id of movie
     * @param userName user nickname
     * @param intRating    rating user gave
     * @throws DAOException if some error occurred while processing data.
     */
    void updateRating(int intMovieID, String userName, int intRating) throws DAOException;

}
