package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.ActorDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Actor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class ActorDAOImpl implements ActorDAO {

    private static final String ACTORS_FOR_MOVIE =
            "SELECT actor_id, actor_name, actor_surname FROM actors\n" +
                    "LEFT JOIN actor_starred\n" +
                    "ON actors.actor_id=actor_starred.actors_actor_id\n" +
                    "WHERE movies_id=?;";
    private final static String PRODUCER_FOR_MOVIE =
            "SELECT actor_id, actor_name, actor_surname FROM actors\n" +
                    "LEFT JOIN producer\n" +
                    "ON actors.actor_id=Producer.actors_actor_id\n" +
                    "WHERE movies_m_id=?;";
    private static final String ACTOR_BY_ID =
            "SELECT actor_name, actor_surname, actor_image FROM actors WHERE actor_id=?;";
    private final static String ADD_ACTOR =
            "INSERT INTO actors (actor_name, actor_surname) VALUES (?, ?)";
    private final static String UPDATE_ACTOR =
            "UPDATE actors SET actor_name = ?, actor_surname = ? WHERE actor_id = ?;\n";
    private final static String ADD_ACTOR_FOR_MOVIE =
            "INSERT INTO actor_starred (actors_actor_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_ACTOR_FOR_MOVIE =
            "DELETE FROM actor_starred\n" +
                    "WHERE movies_m_id=? AND actors_actor_id=?;";
    private final static String ADD_PRODUCER_FOR_MOVIE =
            "INSERT INTO producer (actors_actor_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_PRODUCER_FOR_MOVIE =
            "DELETE FROM producer\n" +
                    "WHERE movies_m_id=? AND actors_actor_id=?;";
    private static final String ALL_ACTORS =
            "SELECT * FROM actors;";
    private static final String DELETE_ACTOR_BY_ID =
            "DELETE FROM `films`.`actors` WHERE actor_id=?;";
    private static final String UPDATE_IMAGE =
            "UPDATE actors SET actor_image= ? WHERE actor_id= ?;";

    private static final String ACTOR_ID = "actor_id";
    private static final String ACTOR_NAME = "actor_name";
    private static final String ACTOR_SURNAME = "actor_surname";
    private static final String ACTOR_IMAGE = "actor_image";
    private static final ActorDAO instance = new ActorDAOImpl();

    private ActorDAOImpl() {

    }
    public static ActorDAO getInstance() {
        return instance;
    }

    @Override
    public List<Actor> getActorsForMovie(int actorId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ACTORS_FOR_MOVIE);
            preparedStatement.setInt(1, actorId);

            resultSet = preparedStatement.executeQuery();

            List<Actor> actors = new ArrayList<>();
            Actor actor;
            while (resultSet.next()) {
                actor = new Actor();
                actor.setId(resultSet.getInt(ACTOR_ID));
                actor.setName(ACTOR_NAME);
                actor.setSurname(ACTOR_SURNAME);
                actors.add(actor);
            }
            return actors;
        } catch (SQLException e) {
            throw new DAOException("Actor SQL error", e);
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException("Actor pool connection error", e);
        }
    }

    @Override
    public Actor getProducerForMovie(int producerId) {
        return null;
    }

    @Override
    public Actor getActor(int actorId) {
        return null;
    }

    @Override
    public void addActor(String name, String surname) {

    }

    @Override
    public void updateActor(String name, String surname) {

    }

    @Override
    public void deleteActor(int actorId) {

    }

    @Override
    public void addActorForMovie(int actorId, int movieId) {

    }

    @Override
    public void deleteActorForMovie(int actorId, int movieId) {

    }

    @Override
    public void addProducerForMovie(int producerId, int movieId) {

    }

    @Override
    public void deleteProducerForMovie(int producerId, int movieId) {

    }

    @Override
    public List<Actor> getAllActors() {
        return null;
    }

    @Override
    public void updateImage(int id, String path) {

    }
}
