package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface MovieDAO {
    List<Movie> getAllMovies() throws DAOException;
    List<Movie> getMoviesByGenre(String genre, int offset, int recordsNumber) throws DAOException;
    List<Movie> searchMovieByTitle(String title) throws DAOException;
    List<Movie> getMoviesByActor(int actorId) throws DAOException;
    Movie getMovieById(int id) throws DAOException;
    void addMovie(String title, int year, long budget, long gross) throws DAOException;
    void updateMovie(int id, String title, int year, long budget, long gross) throws DAOException;
    void deleteMovie(int id) throws DAOException;
    void updateImage(int id, String path) throws DAOException;


}
