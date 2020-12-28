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
    private final static String SHOW_GENRES_BY_ID =
            "SELECT name FROM genres\n" +
                    " WHERE id = ?";

    private static final String ADD_GENRE = "INSERT INTO `genres` " +
            "(`name`) VALUES (?)";

    private static final String ADD_GENRE_FOR_MOVIE =
            "INSERT INTO movies_genres(movies_id, movies_genres.genres_id) VALUES (?, ?)";

    private static final String DELETE_GENRE =
            "DELETE FROM `genres` WHERE `id` = ?";

    private static final String GENRE = "genres";
    private static final GenreDAO instance = new GenreDaoImpl();

    private GenreDaoImpl() {

    }

    public static GenreDAO getInstance() {
        return instance;
    }

    @Override
    public void createGenre(String name) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_GENRE);
            st.setString(1, name);
            int update = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        }finally {
            try {
                st.close();
            } catch (SQLException e) {}
        }
    }

    /**
     * This method is used to get genres for a particular movie from data source.
     *
     * @param id of movie
     * @return filled Genre beans
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Genre> readGenresByMovie(int id) throws DAOException {
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
                genre.setName(rs.getString(GENRE));
                genreList.add(genre);
            }
            return genreList;

        } catch (SQLException e) {
            throw new DAOException("Genre sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Genre pool connection error");
        } finally {
            try {
            rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
            st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    /**
     * This method is used to add connection between some movie and genre into data source.
     *
     * @param intMovieId id of movie
     * @param name       genre name in russian
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public void createGenreForMovie(int intMovieId, String name) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_GENRE_FOR_MOVIE);
            st.setInt(1, intMovieId);
            st.setString(2, name);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Genre is added " + name);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error");
        }
        finally {
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
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
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Genre is deleted " + intMovieID);
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
        finally {
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }
}
