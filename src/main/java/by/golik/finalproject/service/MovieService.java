package by.golik.finalproject.service;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.exception.ServiceException;

import javax.mail.Part;
import java.util.List;

/**
 * @author Nikita Golik
 */
public interface MovieService {
    List<Movie> getFullList(int offset, int recordsPerPage) throws ServiceException;
    List<Movie> readAllMovies() throws ServiceException;

    List<Movie> getMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceException;
    Movie getMovieByID(String id) throws ServiceException;
    Participant getParticipantByID(String id) throws ServiceException;
    public void addRating(String movieID, String userName, String rating) throws ServiceException;
    List<Movie> findMovieByTitle(String title) throws ServiceException;
    int countAllMoviesAmount() throws ServiceException;
    List<Participant> readAllParticipants() throws ServiceException;
    int countMoviesByGenre(String genre) throws ServiceException, DAOException;
}
