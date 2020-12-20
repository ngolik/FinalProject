package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.VoteDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Vote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class VoteDaoImpl implements VoteDAO {

    private final static String SHOW_RATINGS_BY_ID =
            "SELECT score, date, users_id FROM votes WHERE movies_id=?";
    private static final String SHOW_RATINGS_OF_USER =
            "SELECT score, date, movies_id FROM votes WHERE users_id=?";
    private static final String CHECK_RATING =
            "SELECT users_id, score FROM votes WHERE movies_id=? AND users_id=?";
    private static final String ADD_RATING =
            "INSERT INTO votes (movies_id, users_id, score) VALUES (?, ?, ?)";
    private final static String UPDATE_RATING =
            "UPDATE votes SET score=? WHERE movies_id=? AND users_id=?;";
    private final static String DELETE_RATING =
            "DELETE FROM votes WHERE movies_id=? AND users_id=?;";
    private static final String USER_NAME = "userName";
    private static final String MOVIES_ID = "movies_id";
    private static final String SCORE = "score";
    private static final VoteDAO instance = new VoteDaoImpl();

    public static VoteDAO getInstance() {
        return instance;
    }
    @Override
    public List<Vote> getVotesForMovie(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_RATINGS_BY_ID);
            st.setInt(1, id);
            rs = st.executeQuery();

            List<Vote> voteList = new ArrayList<>();
            Vote vote = null;
            while (rs.next()) {
                vote = new Vote();
                vote.setMovieID(id);
                vote.setUserName(rs.getString(USER_NAME));
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
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    @Override
    public List<Vote> getVotesOfUser(String nickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_RATINGS_OF_USER);
            st.setString(1, nickname);
            rs = st.executeQuery();

            List<Vote> voteList = new ArrayList<>();
            Vote vote = null;
            while (rs.next()) {
                vote = new Vote();
                vote.setMovieID(rs.getInt(MOVIES_ID));
                vote.setUserName(nickname);
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
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {}
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    @Override
    public Vote checkVotes(int intMovieID, String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(CHECK_RATING);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            rs = st.executeQuery();

            Vote vote = null;
            if (rs.next()) {
                vote = new Vote();
                vote.setMovieID(intMovieID);
                vote.setUserName(userNickname);
                vote.setScore(rs.getInt(SCORE));
            }
            return vote;

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
    public void addVotes(int intMovieID, String userNickname, int rating) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_RATING);
            st.setInt(1, intMovieID);
            st.setString(2, userNickname);
            st.setInt(3, rating);
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
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    @Override
    public void updateVotes(int intMovieID, String userNickname, int intRating) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_RATING);
            st.setInt(1, intRating);
            st.setInt(2, intMovieID);
            st.setString(3, userNickname);
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
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    @Override
    public void deleteVotes(int movieID, String userNickname) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_RATING);
            st.setInt(1, movieID);
            st.setString(2, userNickname);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Rating udalen vse ok");
                return;
            }
            throw new DAOException("Wrong rating data");
        } catch (SQLException e) {
            throw new DAOException("Rating sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Rating pool connection error");
        }
        finally {
            try {
                st.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }
}
