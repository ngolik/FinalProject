package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.GenreDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Genre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class GenreDaoImpl implements GenreDAO {
    private final static String SHOW_GENRES_BY_ID = "SELECT genres_genre_ru FROM genres WHERE movies_m_id=?";
    private static final String ADD_GENRE =
            "INSERT INTO genres (movies_m_id, genres_genre_) VALUES (?, ?, ?)";
    private static final String DELETE_GENRE =
            "DELETE FROM genres\n" +
                    "WHERE movies_m_id=? AND genres_genre=?;";

    private static final String GENRE_RU = "genres_genre_ru";
    private static final String GENRE_EN = "genres_genre_en";
    private static final GenreDAO instance = new GenreDaoImpl();

    private GenreDaoImpl() {

    }

    public static GenreDAO getInstance() {
        return instance;
    }

    /**
     * This method is used to get genres for a particular movie from data source.
     *
     * @param id of movie
     * @return filled Genre beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Genre> getGenresByMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_GENRES_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            List<Genre> genreList = new ArrayList<>();
            Genre genre = null;
            while (rs.next()) {
                genre = new Genre();
                genre.setName(rs.getString(GENRE_RU));
                genreList.add(genre);
            }
            return genreList;

        } catch (SQLException e) {
            throw new DAOException("Genre sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Genre pool connection error");
        }
    }

    /**
     * This method is used to add connection between some movie and genre into data source.
     *
     * @param intMovieID id of movie
     * @param name       genre name in russian
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void addGenreForMovie(int intMovieID, String name) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_GENRE);
            st.setInt(1, intMovieID);
            st.setString(2, name);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Genre dobavlen vse ok " + name);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error");
        }
    }

    /**
     * This method is used to remove connection between some movie and genre from data source.
     *
     * @param intMovieID movie id
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void deleteGenreForMovie(int intMovieID, String name) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_GENRE);
            st.setInt(1, intMovieID);
            st.setString(2, name);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Genre udalen vse ok  " + intMovieID);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
    }
}
