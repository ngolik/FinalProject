package by.golik.finalproject.dao;

import by.golik.finalproject.dao.mysql.*;
import by.golik.finalproject.dao.pool.ConnectionPool;

/**
 * DAOFactory represents the factory for obtaining DAO objects.
 * @author Nikita Golik
 */
public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    public DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }
    private GenreDAO genreDAO = GenreDaoImpl.getInstance();
    private MovieDAO movieDAO = MovieDaoImpl.getInstance();
    private ParticipantDAO participantDAO = ParticipantDaoImpl.getInstance();
    private UserDAO userDAO = UserDaoImpl.getInstance();
    private VoteDAO voteDAO = VoteDaoImpl.getInstance();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }
    public MovieDAO getMovieDAO() {
        return movieDAO;
    }
    public ParticipantDAO getParticipantDAO() {
        return participantDAO;
    }
    public UserDAO getUserDAO() {
        return userDAO;
    }
    public VoteDAO getVoteDAO() {
        return voteDAO;
    }
    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

}
