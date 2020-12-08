package by.golik.finalproject.dao.mysql;

import by.golik.finalproject.dao.ActorDAO;
import by.golik.finalproject.entity.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class ActorDAOImpl implements ActorDAO {

    private static final String ACTORS_FOR_MOVIE =
            "SELECT a_id, a_name, a_surname FROM actors\n" +
                    "LEFT JOIN actor_starred\n" +
                    "ON actors.a_id=actor_starred.actors_a_id\n" +
                    "WHERE movies_m_id=?;";
    private final static String PRODUCER_FOR_MOVIE =
            "SELECT a_id, a_name, a_surname FROM actors\n" +
                    "LEFT JOIN producer\n" +
                    "ON actors.a_id=Producer.actors_a_id\n" +
                    "WHERE movies_m_id=?;";
    private static final String ACTOR_BY_ID =
            "SELECT a_name, a_surname, a_image FROM actors WHERE a_id=?;";
    private final static String ADD_ACTOR =
            "INSERT INTO actors (a_name, a_surname) VALUES (?, ?)";
    private final static String UPDATE_ACTOR =
            "UPDATE actors SET a_name = ?, a_surname = ? WHERE a_id = ?;\n";
    private final static String ADD_ACTOR_FOR_MOVIE =
            "INSERT INTO actor_starred (actors_a_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_ACTOR_FOR_MOVIE =
            "DELETE FROM actor_starred\n" +
                    "WHERE movies_m_id=? AND actors_a_id=?;";
    private final static String ADD_PRODUCER_FOR_MOVIE =
            "INSERT INTO producer (actors_a_id, movies_m_id) VALUES (?, ?)";
    private static final String DELETE_PRODUCER_FOR_MOVIE =
            "DELETE FROM producer\n" +
                    "WHERE movies_m_id=? AND actors_a_id=?;";
    private static final String ALL_ACTORS =
            "SELECT * FROM actors;";
    private static final String DELETE_ACTOR_BY_ID =
            "DELETE FROM `films`.`actors` WHERE a_id=?;";
    private static final String UPDATE_IMAGE =
            "UPDATE actors SET a_image= ? WHERE a_id= ?;";

    private static final String A_ID = "a_id";
    private static final String A_NAME_RU = "a_name";
    private static final String A_NAME_EN = "a_surname";
    private static final String A_IMAGE = "a_image";
    private static final ActorDAO instance = new ActorDAOImpl();

    private ActorDAOImpl() {

    }
    public static ActorDAO getInstance() {
        return instance;
    }

    @Override
    public List<Actor> getActorsForMovie(int actorId) {
        return null;
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
