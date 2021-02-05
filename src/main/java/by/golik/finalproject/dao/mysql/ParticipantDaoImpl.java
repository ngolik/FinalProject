package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.ParticipantDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.dao.pool.ConnectionPoolHelper;
import by.golik.finalproject.entity.Participant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class ParticipantDaoImpl implements ParticipantDAO {

    private static final String PARTICIPANTS_FOR_MOVIE = "SELECT participants.id, name, surname, secondname " +
            "FROM participants\n" +
            "LEFT JOIN movies_participants mp ON participants.id = mp.participants_id\n" +
            "WHERE movies_db.movies.id=?;";

    private static final String PARTICIPANT_BY_ID =
            "SELECT name, surname, secondname, position FROM participants\n" +
                    " WHERE `id` = ?";

    private final static String CREATE_PARTICIPANT =
            "INSERT INTO participants (name, surname, secondname, position)" + "VALUES (?, ?, ?,?)";

    private final static String UPDATE_PARTICIPANT =
            "UPDATE `participants` SET `name` = ?, `surname` = ?, `secondname` = ?, `position` = ?" +
                    " WHERE participants.id = ?";

    private final static String ADD_PARTICIPANT_FOR_MOVIE =
            "INSERT INTO movies_participants (participants_id, movies_id) VALUES (?, ?)";

    private static final String DELETE_PARTICIPANT_FOR_MOVIE =
            "DELETE FROM movies_participants\n" +
                    "WHERE participants_id=? AND movies_id=?;";

    private static final String DELETE_PARTICIPANT_BY_ID =
            "DELETE FROM `participants` WHERE `id` = ?";

    private static final String SHOW_ALL_PARTICIPANTS =
            "SELECT id, name, surname, secondname, position FROM participants";
    private static final String SHOW_ALL_PARTICIPANTS_PAGINATION =
            "SELECT id, name, surname, secondname, position FROM participants ORDER BY id DESC LIMIT ?, ?";

    private static final String LAST_INSERTED_PARTICIPANT =
            "SELECT id, name, surname, secondname, position FROM participants ORDER BY participants.id DESC LIMIT 1;";
    private static final String COUNT_ALL_PARTICIPANTS = "SELECT COUNT(id) AS amount FROM participants";
    private static final String AMOUNT = "amount";


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String SECONDNAME = "secondname";
    private static final String POSITION = "position";
    private static final ParticipantDAO instance = new ParticipantDaoImpl();

    public ParticipantDaoImpl() {
    }

    public static ParticipantDAO getInstance() {
        return instance;
    }

    @Override
    public List<Participant> getParticipantsForMovie(int normId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(PARTICIPANTS_FOR_MOVIE);
            st.setInt(1, normId);
            rs = st.executeQuery();

            List<Participant> participants = new ArrayList<>();
            Participant participant;
            while (rs.next()) {
                participant = new Participant();
                participant.setId(rs.getInt(ID));
                participant.setName(rs.getString(NAME));
                participant.setSurname(rs.getString(SURNAME));
                participant.setSecondName(rs.getString(SECONDNAME));
                participant.setPosition(rs.getString(POSITION));
                participants.add(participant);
            }
            return participants;

        } catch (SQLException e) {
            throw new DAOException("Participant sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Participant pool connection error");
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }


    @Override
    public Participant readParticipant(int normId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(PARTICIPANT_BY_ID);
            st.setInt(1, normId);
            rs = st.executeQuery();

            Participant participant = null;
            if (rs.next()) {
                participant = new Participant();
                participant.setId(normId);
                participant.setName(rs.getString(NAME));
                participant.setSurname(rs.getString(SURNAME));
                participant.setSecondName(rs.getString(SECONDNAME));
                participant.setPosition(rs.getString(POSITION));
            }
            return participant;

        } catch (SQLException e) {
            throw new DAOException("Participant sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Participant pool connection error");
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    @Override
    public void createParticipant(String name, String surname, String secondName, String position) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(CREATE_PARTICIPANT);
            st.setString(1, name);
            st.setString(2, surname);
            st.setString(3, secondName);
            st.setString(4, position);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Participant is added " + name);
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

    @Override
    public void updateParticipant(int actorID, String name, String surname, String secondName, String position) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(UPDATE_PARTICIPANT);
            st.setString(1, name);
            st.setString(2, surname);
            st.setString(3, secondName);
            st.setString(4, position);
            st.setInt(5, actorID);
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

    @Override
    public void deleteParticipant(int id) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_PARTICIPANT_BY_ID);
            st.setInt(1, id);
            int update = st.executeUpdate();
            if (update > 0) {
                //System.out.println("Participant deleted " + id);
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

    @Override
    public void addParticipantForMovie(int participantId, int movieId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(ADD_PARTICIPANT_FOR_MOVIE);
            st.setInt(1, participantId);
            st.setInt(2, movieId);
            int update = st.executeUpdate();
            if (update > 0) {
                return;
            }
            throw new DAOException("Wrong movie data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            ConnectionPoolHelper.closeResource(con, st);
        }
    }

    @Override
    public void deleteParticipantForMovie(int participantId, int movieId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();
            st = con.prepareStatement(DELETE_PARTICIPANT_FOR_MOVIE);
            st.setInt(1, participantId);
            st.setInt(2, movieId);
            int update = st.executeUpdate();
            if (update > 0) {
                return;
            }
            throw new DAOException("Wrong review data");
        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error", e);
        } finally {
            ConnectionPoolHelper.closeResource(con, st);
        }
    }

    @Override
    public List<Participant> getAllParticipants() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL_PARTICIPANTS);

            rs = st.executeQuery();

            List<Participant> participants = new ArrayList<>();
            Participant participant;
            while (rs.next()) {
                participant = new Participant();
                participant.setId(rs.getInt(ID));
                participant.setName(rs.getString(NAME));
                participant.setSurname(rs.getString(SURNAME));
                participant.setSecondName(rs.getString(SECONDNAME));
                participant.setPosition(rs.getString(POSITION));
                participants.add(participant);
            }
            return participants;

        } catch (SQLException e) {
            throw new DAOException("Participant sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Participant pool connection error");
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }
    @Override
    public List<Participant> getAllParticipants(int offset, int noOfRecords) throws SQLException, DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(SHOW_ALL_PARTICIPANTS_PAGINATION);
            st.setInt(1, offset);
            st.setInt(2, noOfRecords);
            rs = st.executeQuery();

            List<Participant> participants = new ArrayList<>();
            Participant participant;
            while (rs.next()) {
                participant = new Participant();
                participant.setId(rs.getInt(ID));
                participant.setName(rs.getString(NAME));
                participant.setSurname(rs.getString(SURNAME));
                participant.setSecondName(rs.getString(SECONDNAME));
                participant.setPosition(rs.getString(POSITION));
                participants.add(participant);
            }
            return participants;

        } catch (SQLException e) {
            throw new DAOException("Participant sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Participant pool connection error");
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }


    @Override
    public Participant getLastInsertedParticipant() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(LAST_INSERTED_PARTICIPANT);
            rs = st.executeQuery();

            Participant participant = null;
            if (rs.next()) {
                participant = new Participant();
                participant.setId(rs.getInt(ID));
                participant.setName(rs.getString(NAME));
                participant.setSurname(rs.getString(SURNAME));
                participant.setSecondName(rs.getString(SECONDNAME));
                participant.setPosition(rs.getString(POSITION));
            }
            return participant;

        } catch (SQLException e) {
            throw new DAOException("Movie sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Movie pool connection error");
        }
        finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }

    @Override
    public int countAllParticipantsAmount() throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().takeConnection();

            st = con.prepareStatement(COUNT_ALL_PARTICIPANTS);
            int amount = 0;
            rs = st.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(AMOUNT);
            }
            return amount;

        } catch (SQLException e) {
            throw new DAOException("Participant sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Participant pool connection error", e);
        } finally {
            ConnectionPoolHelper.closeResource(con, st, rs);
        }
    }
}
