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
     * Used for representing pagination on pages
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> readAllMovies(int offset, int noOfRecords) throws DAOException;

    /**
     * This method is used to get the list of all movies from data source.
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
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
     * This method is used to get movies of a particular participant from data source.
     * @param participantId - unique identifier of participant
     * @return list of filled movies with chosen participant
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesByParticipant(int participantId) throws DAOException;

    /**
     * This method is used to get movies of a particular participant from data source.
     * Used for showing pagination on page.
     * @param participantName - name of participant
     * @param participantSurname - surname of participant
     * @param offset - of pages
     * @param recordsPerPage - maximum count of movies on one page
     * @return list of filled movies with chosen participant
     * @throws DAOException if some error occurred while processing data.
     */
    List<Movie> getMoviesByParticipant(String participantName, String participantSurname, int offset, int recordsPerPage) throws DAOException;

    /**
     * This method is used to get movie by unique identifier.
     * @param id - unique identifier
     * @return movie
     * @throws DAOException if some error occurred while processing data.
     */
    Movie getMovieById(int id) throws DAOException;

    /**
     * This method is used to get count of all movies from data source.
     * @return - count of movies
     * @throws DAOException if some error occurred while processing data.
     */
    int countAllMoviesAmount() throws DAOException;

    /**
     * This method is used to add new movie to data source
     * @param title - title of movie
     * @param year - year of movie
     * @param runtime - runtime of movie
     * @param budget - budget of movie
     * @param gross - gross of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void createMovie(String title, int year, int runtime, long budget, long gross) throws DAOException;

    /**
     * This method is used to update information about movie from data source
     * @param title - title of movie
     * @param year - year of movie
     * @param runtime - runtime of movie
     * @param budget - budget of movie
     * @param gross - gross of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void updateMovie(int id, String title, int year, int runtime, long budget, long gross) throws DAOException;

    /**
     * This method is used to delete movie from data source
     * @param id - unique identifier of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteMovie(int id) throws DAOException;

    /**
     * This method is used to get movie, that has been inserted last
     * @return movie bean
     * @throws DAOException if some error occurred while processing data.
     */
    Movie getLastInsertedMovie() throws DAOException;

    /**
     * This method is used to get count of all movies by particular genre
     * @param genre - genre of movie
     * @return count of movies by particular genre
     * @throws DAOException if some error occurred while processing data.
     */
    int countMoviesByGenre(String genre) throws DAOException;

    /**
     * This method is used to get count of all movies by particular genre
     * @param participantName - name of participant
     * @param participantSurname - surname of participant
     * @return count of movies by particular genre
     * @throws DAOException if some error occurred while processing data.
     */
    int countMoviesByParticipant(String participantName, String participantSurname) throws DAOException;

}
