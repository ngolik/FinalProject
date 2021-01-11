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

    private static final String RATING = "rating";
    private static final String VOTES = "votes";

    private static final String AMOUNT = "amount";

    private final static String SHOW_ALL =
            "SELECT *  FROM `movies`";
    private static final String SHOW_BY_GENRE =
            "SELECT `id`, `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `movies_db`.genres.name LIKE ? ORDER BY `title`";
    private static final String SHOW_BY_TITLE =
            "SELECT `id`, `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `title` LIKE ? ORDER BY `title`";

    private static final String SHOW_BY_PARTICIPANT =
            "SELECT `id`, `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `movies_db`.movies_participants.participants_id = ? ORDER BY `title`";

    private static final String SHOW_BY_ID =
            "SELECT `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `id` = ?";

    private static final String COUNT_ALL_MOVIES =
            "SELECT COUNT(movies.id) AS amount FROM movies";

    private static final String ADD_MOVIE =
            "INSERT INTO movies (title, year, runtime, budget, image_path, gross, description) VALUES (?, ?, ?, ?, 'sa', ?, 'empty')";

    private static final String UPDATE_BY_ID =
            "UPDATE `test_db`.`movies`\n" +
                    "SET title = ?, year = ?, `runtime` = ?, `budget` = ?,`gross` = ?\n" +
                    "WHERE `id` = ?;\n";


    private static final String DELETE_BY_ID =
            "DELETE FROM `movies_db`.`movies` WHERE id=?;";
    private static final String UPDATE_IMAGE =
            "UPDATE movies SET image_path= ? WHERE id= ?;";

    private static final String MOVIES_FOR_PARTICIPANT =
            "SELECT movies_id from movies_participants where participants_id = ?;";
    private static final String LAST_INSERTED_MOVIE =
            "SELECT * FROM test_db.movies ORDER BY id DESC LIMIT 1;";

    private static final String COUNT_ALL_MOVIES_BY_GENRE =
            "SELECT COUNT(id) AS amount FROM genres WHERE name = ?;";

    private static final MovieDAO instance = new MovieDaoImpl();

    private MovieDaoImpl() {

    }

    public static MovieDAO getInstance() {
        return instance;
    }

    @Override
    public List<Movie> readAllMovies() throws DAOException {
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
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
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
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Movie> searchMovieByTitle(String title) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_TITLE);
            st.setString(1, "%" + title + "%");
            System.out.println("%" + title + "%");
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setYear(rs.getInt(YEAR));
                movie.setRuntime(rs.getInt(RUNTIME));
                movie.setBudget(rs.getLong(BUDGET));
                movie.setGross(rs.getInt(GROSS));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Movie> getMoviesByParticipant(int participantId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_PARTICIPANT);
            st.setInt(1, participantId);
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
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Movie getMovieById(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            Movie movie = null;
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setYear(rs.getInt(YEAR));
                movie.setBudget(rs.getLong(BUDGET));
                movie.setGross(rs.getLong(GROSS));
                movie.setAvgRating(rs.getDouble(RATING));
                movie.setRating(rs.getInt(VOTES));
                movie.setImageUrl(rs.getString(IMAGE));
            }
            return movie;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public int countAllMoviesAmount() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_MOVIES);
            int amount = 0;
            rs = st.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(AMOUNT);
            }
            return amount;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void createMovie(String title, int year, int runtime, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_MOVIE);
            st.setString(1, title);
            st.setInt(2, year);
            st.setInt(3, runtime);
            st.setLong(4, budget);
            st.setLong(5, gross);
            int update = st.executeUpdate();
            if (update > 0) {
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void updateMovie(int id, String title, int year, int runtime, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_BY_ID);
            st.setString(1, title);
            st.setInt(2, year);
            st.setInt(3, runtime);
            st.setLong(4, budget);
            st.setLong(5, gross);
            st.setInt(6, id);
            int update = st.executeUpdate();
            if (update > 0) {

                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void deleteMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_BY_ID);
            st.setInt(1, id);
            int update = st.executeUpdate();
            if (update > 0) {

                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void updateImage(int id, String path) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_IMAGE);
            st.setString(1, path);
            st.setInt(2, id);
            int update = st.executeUpdate();
            if (update > 0) {

                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Movie> getMoviesForParticipant(int participantId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(MOVIES_FOR_PARTICIPANT);
            st.setInt(1, participantId);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setAvgRating(rs.getDouble(RATING));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Movie getLastInsertedMovie() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(LAST_INSERTED_MOVIE);
            rs = st.executeQuery();

            Movie movie = null;
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setYear(rs.getInt(YEAR));
                movie.setBudget(rs.getLong(BUDGET));
                movie.setGross(rs.getLong(GROSS));
                movie.setGross(rs.getLong(RUNTIME));
            }
            return movie;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            try {
                st.close();
            } catch (SQLException e){}
                try {
                    rs.close();
                } catch (SQLException e) {}
        }
    }
    @Override
    public int countMoviesByGenre(String genre) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_MOVIES_BY_GENRE);
            st.setString(1, genre);
            int amount = 0;
            rs = st.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(AMOUNT);
            }
            return amount;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
            }
        }
    }
}
