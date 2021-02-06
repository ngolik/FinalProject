package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Genre;

import java.sql.SQLException;
import java.util.List;

/**
 *  interface is mainly used to interact with Genre bean mainly.
 * @author Nikita Golik
 */
public interface GenreDAO {
    /**
     * This method is used to get genres for a particular movie from data source.
     *
     * @param id of movie
     * @return filled Genre beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Genre> readGenresByMovie(int id) throws DAOException;

    /**
     * This method is used to add connection between some movie and genre into data source.
     *
     * @param intMovieID id of movie
     * @param intGenreID genre id
     * @throws DAOException if some error occurred while processing data.
     */
    void createGenreForMovie(int intMovieID, int intGenreID) throws DAOException;

    /**
     * This method is used to get all genres that are user in system
     * @return list of genres
     * @throws DAOException if some error occurred while processing data.
     * @throws SQLException if some error occurred while getting information from database.
     */
    List<Genre> readAllGenres() throws DAOException, SQLException;

    /**
     * This method is used to remove connection between some movie and genre from data source.
     *
     * @param intMovieID movie id
     * @param intGenreID genre id
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteGenreForMovie(int intMovieID, int intGenreID) throws DAOException;
}
