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
    List<Movie> readAllMovies(int offset, int noOfRecords) throws DAOException;
    List<Movie> readAllMovies() throws DAOException;

    /**
     * This method is used to get movies of a particular genre from data source.
     * @param genre of movie
     * @param offset first entry offset
     * @param recordsPerPage number of records to return
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesByGenre(String genre, int offset, int recordsPerPage) throws DAOException;

    /**
     * This method is used to search movies into data source.
     * @param title  movie
     * @return list of filled movie beans
     * @throws DAOException  if some error occurred while processing data.
     */
    List<Movie> searchMovieByTitle(String title) throws DAOException;

    /**
     *
     * @param actorId
     * @return
     * @throws DAOException
     */
    List<Movie> getMoviesByParticipant(int actorId) throws DAOException;

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Movie getMovieById(int id) throws DAOException;

    /**
     *
     * @return
     * @throws DAOException
     */
    int countAllMoviesAmount() throws DAOException;

    /**
     *
     * @param title
     * @param year
     * @param runtime
     * @param budget
     * @param gross
     * @throws DAOException
     */
    void createMovie(String title, int year, int runtime, long budget, long gross) throws DAOException;

    /**
     *
     * @param id
     * @param title
     * @param year
     * @param runtime
     * @param budget
     * @param gross
     * @throws DAOException
     */
    void updateMovie(int id, String title, int year, int runtime, long budget, long gross) throws DAOException;

    /**
     *
     * @param id
     * @throws DAOException
     */
    void deleteMovie(int id) throws DAOException;

    /**
     *
     * @param participantId
     * @return
     * @throws DAOException
     */
    List<Movie> getMoviesForParticipant(int participantId) throws DAOException;

    /**
     *
     * @return
     * @throws DAOException
     */
    Movie getLastInsertedMovie() throws DAOException;

    /**
     *
     * @param id
     * @param path
     * @throws DAOException
     */
    void updateImage(int id, String path) throws DAOException;

    /**
     *
     * @param genre
     * @return
     * @throws DAOException
     */
    int countMoviesByGenre(String genre) throws DAOException;
    int countMoviesByParticipant(String participantName, String participantSurname) throws DAOException;
    List<Movie> getMoviesByParticipant(String participantName, String participantSurname, int offset, int recordsPerPage) throws DAOException;

}
