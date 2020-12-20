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
                    "WHERE `movies_db`.movies_participants.participants_id = ? ORDER BY `title`";
    private static final String SHOW_BY_ID =
            "SELECT `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `id` = ?";
    private static final String ADD_MOVIE =
            "INSERT INTO movies (title, `year`, `runtime`, `budget`, `gross`) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_BY_ID =
            "UPDATE `movies_db`.`movies`\n" +
                    "SET title = ?, `year` = ?, `runtime` = ?, `budget` = ?,`gross` = ?\n" +
                    "WHERE `id` = ?;\n";
    private static final String DELETE_BY_ID =
            "DELETE FROM `movies_db`.`movies` WHERE id=?;";
    private static final String UPDATE_IMAGE =
            "UPDATE movies SET image_path= ? WHERE id= ?;";

    private static final MovieDAO instance = new MovieDaoImpl();

    private MovieDaoImpl() {

    }
    public static MovieDAO getInstance() {
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
        finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
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
        finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
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
        finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    @Override
    public List<Movie> getMoviesByParticipant(int actorId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SEARCH_BY_ACTOR);
            st.setInt(1, actorId);
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
        finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
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
        }
        finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    @Override
    public void addMovie(String title, int year, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_MOVIE);
            st.setString(1, title);
            st.setInt(2, year);
            st.setLong(3, budget);
            st.setLong(4, gross);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Filmec dobavlen vse ok" + titleRu);
                return;
            }
            throw new DAOException("Wrong movie data");
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

    @Override
    public void updateMovie(int id, String title, int year, long budget, long gross) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_BY_ID);
            st.setString(1, title);
            st.setInt(2, year);
            st.setLong(3, budget);
            st.setLong(4, gross);
            st.setInt(5, id);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Filmec obnovlen vse ok" + titleRu);
                return;
            }
            throw new DAOException("Wrong movie data");
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
                //System.out.println("Filmec udalen vse ok " + id);
                return;
            }
            throw new DAOException("Wrong movie data");
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
                //System.out.println("Movie obnovlen vse ok " + path);
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
