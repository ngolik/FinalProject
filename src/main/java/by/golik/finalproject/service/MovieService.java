package by.golik.finalproject.service;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.entity.Vote;
import by.golik.finalproject.service.exception.ServiceException;
import java.util.List;

/**
 * @author Nikita Golik
 */
public interface MovieService {

    /**
     * This method is used to get list of all movies in the system with pagination.
     * @param offset starting from
     * @param recordsPerPage amount of movies
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Movie> getFullList(int offset, int recordsPerPage) throws ServiceException;

    /**
     * This method is used to get list of all movies in the system.
     * @return list of movies
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Movie> readAllMovies() throws ServiceException;

    /**
     * This method is used to get movies of a particular genre.
     * @param offset starting from
     * @param recordsPerPage amount of movies
     * @param genre of movies
     * @return list of movies
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Movie> getMoviesByGenre(int offset, int recordsPerPage,String genre) throws ServiceException;

    /**
     * This method is used to get all movies by concrete participant.
     * @param offset starting from
     * @param recordsPerPage amount of movies
     * @param participantName name of participant
     * @param participantSurname surname of participant
     * @return list of movies
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Movie> getMoviesByParticipant(int offset, int recordsPerPage,String participantName, String participantSurname) throws ServiceException;

    /**
     * This method is used to get all movies by concrete participant with pagination.
     * @param movieId - id of movie
     * @return movie bean
     * @throws ServiceException if any error occurred while processing method.
     */
    Movie getMovieByID(String movieId) throws ServiceException;

    /**
     * This method is used to get participant by ID.
     * @param participantId unique identifier of participant
     * @return participant bean
     * @throws ServiceException if any error occurred while processing method.
     */
    Participant getParticipantByID(String participantId) throws ServiceException;

    /**
     * This method is used to add 1-10 rating for a particular movie.
     * @param movieID unique identifier of movie
     * @param userName name of user
     * @param rating mark from user to movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void addRating(String movieID, String userName, String rating) throws ServiceException;

    /**
     * This method is used to get rating of movie.
     * @param movieID unique identifier of movie
     * @return mark from user
     * @throws ServiceException if any error occurred while processing method.
     */
    Vote getRatingForMovie(String movieID) throws ServiceException;

    /**
     * This method is used to get movie by movie title.
     * @param title - title of movie
     * @return list of movies with such name
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Movie> findMovieByTitle(String title) throws ServiceException;

    /**
     * This method is used to get count of all movies.
     * @return count of movies
     * @throws ServiceException if any error occurred while processing method.
     */
    int countAllMoviesAmount() throws ServiceException;

    /**
     * This method is used to get all participants.
     * @return list of participants
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Participant> readAllParticipants() throws ServiceException;

    /**
     * This method is used to get all movies by particular genre.
     * @param genre name of genre
     * @return count of movies
     * @throws ServiceException if any error occurred while processing method.
     * @throws DAOException some error occurred while processing data.
     */
    int countMoviesByGenre(String genre) throws ServiceException, DAOException;

    /**
     * This method is used to get all count of movies by participant
     * @param participantName name of participant
     * @param participantSurname surname of participant
     * @return count of movies
     * @throws ServiceException if any error occurred while processing method.
     * @throws DAOException some error occurred while processing data.
     */
    int countMoviesByParticipant(String participantName, String participantSurname) throws ServiceException, DAOException;
}
