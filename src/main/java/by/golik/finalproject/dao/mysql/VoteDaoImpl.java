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
            "SELECT user_u_nick, rating_score FROM rating WHERE movies_m_id=? AND user_u_nick=?";
    private static final String ADD_RATING =
            "INSERT INTO rating (movies_m_id, user_u_nick, rating_score) VALUES (?, ?, ?)";
    private final static String UPDATE_RATING =
            "UPDATE rating SET rating_score=? WHERE movies_m_id=? AND user_u_nick=?;";
    private final static String DELETE_RATING =
            "DELETE FROM rating WHERE movies_m_id=? AND user_u_nick=?;";
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
            throw new DAOException("Rating sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Rating pool connection error");
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
            throw new DAOException("Review sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Review pool connection error");
        }
    }

    @Override
    public Vote checkVotes(int intMovieID, String userNickname) throws DAOException {
        return null;
    }

    @Override
    public void addVotes(int intMovieID, String userNickname, int rating) throws DAOException {

    }

    @Override
    public void updateVotes(int intMovieID, String userNickname, int intRating) throws DAOException {

    }

    @Override
    public void deleteVotes(int movieID, String userNickname) throws DAOException {

    }
}
