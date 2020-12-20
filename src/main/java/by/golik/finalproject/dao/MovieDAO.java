package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;
import java.util.List;

/**
 * interface describes methods for interaction with Movie beans mainly.
 * @author Nikita Golik
 */
public interface MovieDAO {
    /**
     * This method is used to get the list of all movies from data source.
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getAllMovies() throws DAOException;

    /**
     * This method is used to get movies of a particular genre from data source.
     * @param genre of movie
     * @param offset first entry offset
     * @param recordsNumber number of records to return
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesByGenre(String genre, int offset, int recordsNumber) throws DAOException;

    /**
     *
     * @param title
     * @return
     * @throws DAOException
     */
    List<Movie> searchMovieByTitle(String title) throws DAOException;
    List<Movie> getMoviesByParticipant(int actorId) throws DAOException;
    Movie getMovieById(int id) throws DAOException;
    void addMovie(String title, int year, long budget, long gross) throws DAOException;
    void updateMovie(int id, String title, int year, long budget, long gross) throws DAOException;
    void deleteMovie(int id) throws DAOException;
    void updateImage(int id, String path) throws DAOException;


}
