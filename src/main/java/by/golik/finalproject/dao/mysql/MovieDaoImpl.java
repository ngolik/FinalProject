package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.MovieDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.dao.pool.ConnectionPoolHelper;
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
    private static final String RUNTIME = "runtime";
    private static final String BUDGET = "budget";
    private static final String GROSS = "gross";

    private static final String RATING = "rating";
    private static final String VOTES = "votes";

    private static final String AMOUNT = "amount";

    private final static String SHOW_ALL_PAGINATION =
            "SELECT id, title, year, runtime, budget, gross  FROM movies ORDER BY id DESC LIMIT ?, ?";
    private final static String SHOW_ALL =
            "SELECT id, title, year, runtime, budget, gross  FROM movies";

    private static final String SHOW_BY_GENRE =
            "SELECT movies.id, movies.title, movies.budget, movies.gross, movies.runtime, movies.year, genres.name\n" +
                    "FROM   movies_genres \n" +
                    "       INNER JOIN movies \n" +
                    "               ON movies.id = movies_genres.movies_id \n" +
                    "       INNER JOIN genres \n" +
                    "               ON genres.id = movies_genres.genres_id where genres.name = ?  ORDER BY title DESC LIMIT ?, ?;";

    private static final String SHOW_BY_TITLE =
            "SELECT `id`, `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE `title` LIKE ? ORDER BY `title`";

    private static final String SHOW_BY_PARTICIPANT =
            "SELECT `id`, `title`, `year`, `runtime`, `budget`, `gross` FROM `movies` " +
                    "WHERE movies_db.movies_participants.participants_id = ? ORDER BY `title`";

    private static final String SHOW_BY_ID =
            "SELECT id, title, year, runtime, budget, gross FROM `movies` " +
                    "WHERE `id` = ?";

    private static final String COUNT_ALL_MOVIES =
            "SELECT COUNT(movies.id) AS amount FROM movies";

    private static final String ADD_MOVIE =
            "INSERT INTO movies (title, year, runtime, budget, gross) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_BY_ID =
            "UPDATE `movies`\n" +
                    "SET title = ?, year = ?, `runtime` = ?, `budget` = ?,`gross` = ?\n" +
                    "WHERE `id` = ?;\n";
    private static final String SHOW_MOVIES_BY_PARTICIPANT  =
            "SELECT movies.id, movies.title, movies.budget, movies.gross, movies.runtime, movies.year, participants.name, participants.surname\n" +
                    "                    FROM   movies_participants\n" +
                    "                    INNER JOIN movies\n" +
                    "                    ON movies.id = movies_participants.movies_id\n" +
                    "                    INNER JOIN participants\n" +
                    "                    ON participants.id = movies_participants.participants_id where participants.name = ? AND participants.surname = ? ORDER BY movies.id";

    private static final String DELETE_BY_ID =
            "DELETE FROM `movies` WHERE id=?;";

    private static final String LAST_INSERTED_MOVIE =
            "SELECT id, title, year, runtime, budget, gross FROM movies ORDER BY id DESC LIMIT 1;";

    private static final String COUNT_ALL_MOVIES_BY_GENRE =
            "SELECT COUNT(genres_id) AS amount FROM movies_genres " +
                    "       INNER JOIN movies \n" +
                    "               ON movies.id = movies_genres.movies_id \n" +
                    "       INNER JOIN genres \n" +
                    "               ON genres.id = movies_genres.genres_id where genres.name = ?";

    private static final String COUNT_ALL_MOVIES_BY_PARTICIPANT =
            "SELECT COUNT(participants_id) AS amount FROM movies_participants " +
                    "       INNER JOIN movies \n" +
                    "               ON movies.id = movies_participants.movies_id \n" +
                    "       INNER JOIN participants \n" +
                    "               ON participants.id = movies_participants.participants_id where participants.name = ?" +
                    "AND participants.surname = ?";

    private static final MovieDAO instance = new MovieDaoImpl();

    private MovieDaoImpl() {

    }

    public static MovieDAO getInstance() {
        return instance;
    }

    /**
     * This method is used to get the list of all movies from data source.
     * Used for representing pagination on pages
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Movie> readAllMovies(int offset, int noOfRecords) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL_PAGINATION);
            st.setInt(1, offset);
            st.setInt(2, noOfRecords);

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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get the list of all movies from data source.
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to add new movie to data source
     * @param title - title of movie
     * @param year - year of movie
     * @param runtime - runtime of movie
     * @param budget - budget of movie
     * @param gross - gross of movie
     * @throws DAOException if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to update information about movie from data source
     * @param title - title of movie
     * @param year - year of movie
     * @param runtime - runtime of movie
     * @param budget - budget of movie
     * @param gross - gross of movie
     * @throws DAOException if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to delete movie from data source
     * @param id - unique identifier of movie
     * @throws DAOException if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st);
        }
    }

    /**
     * This method is used to get movies of a particular genre from data source.
     * @param genre of movie
     * @param offset first entry offset
     * @param recordsPerPage number of records to return
     * @return list of movies
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Movie> getMoviesByGenre(String genre,int offset, int recordsPerPage) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_BY_GENRE);
            st.setString(1, genre);
            st.setInt(2, offset);
            st.setInt(3, recordsPerPage);
            rs = st.executeQuery();

            List<Movie> movies = new ArrayList<>();
            Movie movie;
            while (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt(ID));
                movie.setTitle(rs.getString(TITLE));
                movie.setBudget(rs.getInt(BUDGET));
                movie.setGross(rs.getInt(GROSS));
                movie.setRuntime(rs.getInt(RUNTIME));
                movie.setYear(rs.getInt(YEAR));
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to search movies into data source.
     * @param title  movie
     * @return list of filled movie beans
     * @throws DAOException  if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get movies of a particular participant from data source.
     * @param participantId - unique identifier of participant
     * @return list of filled movies with chosen participant
     * @throws DAOException if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get movie by unique identifier.
     * @param id - unique identifier
     * @return movie
     * @throws DAOException if some error occurred while processing data.
     */
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
                movie.setRuntime(rs.getInt(RUNTIME));
                movie.setBudget(rs.getLong(BUDGET));
                movie.setGross(rs.getLong(GROSS));

            }
            return movie;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get count of all movies from data source.
     * @return - count of movies
     * @throws DAOException if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get movie, that has been inserted last
     * @return movie bean
     * @throws DAOException if some error occurred while processing data.
     */
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
                movie.setRuntime(rs.getInt(RUNTIME));
                movie.setBudget(rs.getLong(BUDGET));
                movie.setGross(rs.getLong(GROSS));

            }
            return movie;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get count of all movies by particular genre
     * @param genre - genre of movie
     * @return count of movies by particular genre
     * @throws DAOException if some error occurred while processing data.
     */
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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get count of all movies by particular genre
     * @param participantName - name of participant
     * @param participantSurname - surname of participant
     * @return count of movies by particular genre
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public int countMoviesByParticipant(String participantName, String participantSurname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_MOVIES_BY_PARTICIPANT);
            st.setString(1, participantName);
            st.setString(2, participantSurname);
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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    /**
     * This method is used to get movies of a particular participant from data source.
     * Used for showing pagination on page.
     * @param participantName - name of participant
     * @param participantSurname - surname of participant
     * @param offset - of pages
     * @param recordsPerPage - maximum count of movies on one page
     * @return list of filled movies with chosen participant
     * @throws DAOException if some error occurred while processing data.
     */
    @Override
    public List<Movie> getMoviesByParticipant(String participantName, String participantSurname, int offset, int recordsPerPage) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_MOVIES_BY_PARTICIPANT);
            st.setString(1, participantName);
            st.setString(2, participantSurname);

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
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }
}
