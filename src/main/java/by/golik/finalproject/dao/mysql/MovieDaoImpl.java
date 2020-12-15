package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.MovieDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class MovieDaoImpl implements MovieDAO {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String IMAGE = "image";
    private static final String RUNTIME = "runtime";
    private static final String BUDGET = "budget";
    private static final String GROSS = "gross";
    private static final String DESCRIPTION = "description";

    private static final String R_USER_NICK = "user_u_nick";
    private static final String R_REVIEW = "review";
    private static final String R_REVIEW_DATE = "review_date";

    private static final String RATING = "rating";
    private static final String VOTES = "votes";

    private static final String AMOUNT = "amount";

    private final static String SHOW_ALL =
            "SELECT *  FROM `movies`";
    private static final String SHOW_BY_GENRE =
            "SELECT id, title, AVG(votes.score) AS m_rating, COUNT(votes.score) AS m_votes\n" +
                    "FROM movies\n" +
                    "LEFT JOIN votes ON movies.id = votes.movies_id\n" +
                    "LEFT JOIN genres ON movies.id = genres.id " +
                    "WHERE genres.name = ? GROUP BY movies_id ORDER BY m_rating DESC LIMIT ?, ?;";
    private static final String SEARCH_BY_TITLE =
            "SELECT `id`, `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `title` LIKE ? ORDER BY `title`";
    private static final String SEARCH_BY_ACTOR =
            "SELECT `id`, `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `` = ? ORDER BY `title`";

    private static final MovieDAO instance = new MovieDaoImpl();

    private MovieDaoImpl() {

    }
    private MovieDAO getInstance() {
        return instance;
    }

    @Override
    public List<Movie> getAllMovies() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL);

            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setYear(rs.getInt(YEAR));
                movie.setRuntime(rs.getInt(RUNTIME));
                movie.setBudget(rs.getInt(BUDGET));
                movie.setGross(rs.getInt(GROSS));
                movie.setAvgRating(rs.getDouble(RATING));
                movie.setRating(rs.getInt(VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre, int offset, int recordsNumber) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_GENRE);
            st.setString(1, genre);
            st.setInt(2, offset);
            st.setInt(3, recordsNumber);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setAvgRating(rs.getDouble(RATING));
                movie.setRating(rs.getInt(VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
    }

    @Override
    public List<Movie> searchMovieByTitle(String title) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SEARCH_BY_TITLE);
            st.setString(1, "%" + title + "%");
            //System.out.println("%" + title + "%");
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setAvgRating(rs.getDouble(RATING));
                movie.setRating(rs.getInt(VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
    }

    @Override
    public List<Movie> getMoviesByActor(int actorId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SEARCH_BY_ACTOR);
            st.setInt(1, actorId);
            st.setInt(2, actorId);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setAvgRating(rs.getDouble(RATING));
                movie.setRating(rs.getInt(VOTES));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
    }

    @Override
    public Movie getMovieById(int id) {
        return null;
    }

    @Override
    public void addMovie(String title, int year, long budget, long gross) {

    }

    @Override
    public void updateMovie(String title, int year, long budget, long gross) {

    }

    @Override
    public void deleteMovie(int id) {

    }

    @Override
    public void updateImage(int id, String path) {

    }
}
