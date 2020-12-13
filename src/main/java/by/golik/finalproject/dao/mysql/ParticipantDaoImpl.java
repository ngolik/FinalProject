package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.ParticipantDAO;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Participant;

import java.util.List;

/**
 * @author Nikita Golik
 */
public class ParticipantDaoImpl implements ParticipantDAO {

    private static final String ACTORS_FOR_MOVIE =
            "SELECT a_id, a_name FROM participants\n" +
                    "LEFT JOIN actor_starred\n" +
                    "ON participants.a_id=actor_starred.participants_a_id\n" +
                    "WHERE movies_m_id=?;";
    private final static String DIRECTOR_FOR_MOVIE =
            "SELECT a_id, a_name FROM participants\n" +
                    "LEFT JOIN director\n" +
                    "ON participants.a_id=director.participants_a_id\n" +
                    "WHERE movies_m_id=?;";
    private final static String PRODUCER_FOR_MOVIE =
            "SELECT a_id, a_name FROM participants\n" +
                    "LEFT JOIN producer\n" +
                    "ON participants.a_id=producer.participants_a_id\n" +
                    "WHERE movies_m_id=?;";
    private final static String ARTIST_FOR_MOVIE =
            "SELECT a_id, a_name FROM participants\n" +
                    "LEFT JOIN artist\n" +
                    "ON participants.a_id=artist.participants_a_id\n" +
                    "WHERE movies_m_id=?;";

    private static final String ACTOR_BY_ID =
            "SELECT a_name,a_surname, a_secondname FROM participants WHERE a_id=?;";
    private final static String ADD_ACTOR =
            "INSERT INTO participants (a_name, a_surname, a_secondname) VALUES (?, ?)";
    private final static String UPDATE_ACTOR =
            "UPDATE participants SET a_name = ?, a_surname = ?, a_secondname = ? WHERE a_id = ?;\n";
    private final static String ADD_ACTOR_FOR_MOVIE =
            "INSERT INTO actor_starred (participants_a_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_ACTOR_FOR_MOVIE =
            "DELETE FROM actor_starred\n" +
                    "WHERE movies_m_id=? AND participants_a_id=?;";
    private final static String ADD_DIRECTOR_FOR_MOVIE =
            "INSERT INTO director (participants_a_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_DIRECTOR_FOR_MOVIE =
            "DELETE FROM director\n" +
                    "WHERE movies_m_id=? AND participants_a_id=?;";
    private static final String ALL_ACTORS =
            "SELECT * FROM participants;";
    private static final String LAST_INSERTED_ACTOR =
            "SELECT * FROM movies_db.participants ORDER BY a_id DESC LIMIT 1;";
    private static final String DELETE_ACTOR_BY_ID =
            "DELETE FROM `movies_db`.`participants` WHERE a_id=?;";

    private static final String A_ID = "a_id";
    private static final String A_NAME_RU = "a_name";
    private static final String A_SURNAME = "a_surname";
    private static final ParticipantDAO instance = new ParticipantDaoImpl();

    private ParticipantDaoImpl() {
    }

    public static ParticipantDAO getInstance() {
        return instance;
    }

    @Override
    public List<Participant> getActorsForMovie(int normId) throws DAOException {
        return null;
    }

    @Override
    public Participant getDirectorForMovie(int normId) throws DAOException {
        return null;
    }

    @Override
    public Participant getProducerForMovie(int normId) throws DAOException {
        return null;
    }

    @Override
    public Participant getArtistForMovie(int normId) throws DAOException {
        return null;
    }

    @Override
    public Participant getActor(int normId) throws DAOException {
        return null;
    }

    @Override
    public void addActor(String nameRu, String nameEn) throws DAOException {

    }

    @Override
    public void updateActor(int actorID, String nameRu, String nameEn) throws DAOException {

    }

    @Override
    public void addActorForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public void deleteActorForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public void addDirectorForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public void addProducerForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public void addArtistForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public void deleteDirectorForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public void deleteProducerForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public void deleteArtistForMovie(int actorID, int movieID) throws DAOException {

    }

    @Override
    public List<Participant> getActorsForNews(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Participant> getAllActors() throws DAOException {
        return null;
    }

    @Override
    public Participant getLastInsertedActor() throws DAOException {
        return null;
    }

    @Override
    public void deleteActor(int id) throws DAOException {

    }

    @Override
    public void updateImage(int id, String path) throws DAOException {

    }
}
