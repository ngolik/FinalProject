package by.golik.finalproject.service;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.service.exception.ServiceException;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface MovieService {
    List<Movie> getFullList() throws ServiceException;
    List<Movie> getMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceException;
    Movie getMovieByID(int offset, int recordsPerPage, String id, String lang) throws ServiceException;
    public void addVote(String movieID, String userName, String rating) throws ServiceException;
    List<Movie> findMovieByTitle(String title) throws ServiceException;
    int countAllMoviesAmount() throws ServiceException;
    int countMoviesByGenre(String genre) throws ServiceException, DAOException;
}
