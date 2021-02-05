package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.RatingDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.dao.pool.ConnectionPoolHelper;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.entity.Vote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.json.bind.JsonbConfig.DATE_FORMAT;

/**
 * @author Nikita Golik
 */
public class RatingDaoImpl implements RatingDAO {

    private final static String SHOW_RATINGS_BY_ID =
            "SELECT users_id, date,  AVG(score) AS score FROM votes WHERE movies_id=?";
    private static final String SHOW_RATING_FROM_USER =
            "SELECT score, date, movies_id FROM votes WHERE users_id=?";
    private static final String CHECK_RATING =
            "SELECT users_id, score FROM votes WHERE movies_id=? AND users_id=?";
    private static final String ADD_RATING =
            "INSERT INTO votes (movies_id, users_id, score, date) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_RATING =
            "UPDATE votes SET score=? WHERE movies_id=? AND users_id=?;";

    private static final String MOVIES_ID = "movies_id";
    private static final String USERS_ID = "users_id";
    private static final String SCORE = "score";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final RatingDAO instance = new RatingDaoImpl();

    public static RatingDAO getInstance() {
        return instance;
    }

    @Override
    public Vote getRatingForMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(SHOW_RATINGS_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            Vote vote = null;
            if (rs.next()) {
                vote = new Vote();
                vote.setMovieID(id);
                vote.setUserID(rs.getInt(USERS_ID));
                vote.setScore(rs.getInt(SCORE));
            }
            return vote;

        } catch (SQLException e) {
            throw new DAOException("Vote sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Vote pool connection error");
        }
        finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }
    @Override
    public void createRating(int intMovieID, String userName, int rating) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_RATING);
            st.setInt(1, intMovieID);
            st.setString(2, userName);
            st.setInt(3, rating);

            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat(DATE_FORMAT);
            String currentTime = sdf.format(dt);
            st.setDate(4, Date.valueOf(currentTime));
            int update = st.executeUpdate();
            if (update > 0) {
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
        finally {
            ConnectionPoolHelper.closeResource(con, st);
        }
    }

    @Override
    public void updateRating(int intMovieID, String userName, int intRating) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_RATING);
            st.setInt(1, intRating);
            st.setInt(2, intMovieID);
            st.setString(3, userName);
            int update = st.executeUpdate();
            if (update > 0) {
                return;
            }
            throw new DAOException("Wrong rating data");
        } catch (SQLException e) {
            throw new DAOException("Rating sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Rating pool connection error");
        }
        finally {
            ConnectionPoolHelper.closeResource(con, st);
        }
    }

    @Override
    public List<Vote> getRatingFromUser(String userName) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_RATING_FROM_USER);
            st.setString(1, userName);
            rs = st.executeQuery();

            List<Vote> voteList = new ArrayList<>();
            Vote vote = null;
            while (rs.next()) {
                vote = new Vote();
                vote.setMovieID(rs.getInt(MOVIES_ID));
                vote.setUserName(userName);
                vote.setScore(rs.getInt(SCORE));
                voteList.add(vote);
            }
            return voteList;

        } catch (SQLException e) {
            throw new DAOException("Vote sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Vote pool connection error");
        }
        finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    @Override
    public Vote checkRating(int intMovieID, String userName) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(CHECK_RATING);
            st.setInt(1, intMovieID);
            st.setString(2, userName);
            rs = st.executeQuery();

            Vote vote = null;
            if (rs.next()) {
                vote = new Vote();
                vote.setMovieID(intMovieID);
                vote.setUserName(userName);
                vote.setScore(rs.getInt(SCORE));
            }
            return vote;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
        finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }
}
