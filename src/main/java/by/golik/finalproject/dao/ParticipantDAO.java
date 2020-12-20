package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Participant;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface ParticipantDAO {
    /**
     * This method gets list of Actors from data source.
     *
     * @param normId id of actor
     * @return list of filled Actor beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Participant> getParticipantsForMovie(int normId) throws DAOException;
    /**
     * This method is used to retrieve actor by id from data source.
     *
     * @param normId id of actor
     * @return filled Actor bean
     * @throws DAOException if some error occurred while processing data.
     */
    Participant getParticipant(int normId) throws DAOException;

    /**
     * This method is used to insert data about new actor into data source.
     *
     * @param nameRu name of actor in russian
     * @param nameEn name of actor in english
     * @throws DAOException if some error occurred while processing data.
     */
    void addParticipant(String nameRu, String nameEn) throws DAOException;

    /**
     * This method is used to update data about any actor in data source.
     *
     * @param ID actor id
     * @param nameRu  name of actor in russian
     * @param nameEn  name of actor in english
     * @throws DAOException if some error occurred while processing data.
     */
    void updateParticipant(int ID, String nameRu, String nameEn) throws DAOException;

    /**
     * This method is used to delete connection between some Movie and Actor from data source.
     *
     * @param participantID id of actor
     * @param movieID id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteParticipantForMovie(int participantID, int movieID) throws DAOException;

    /**
     * This method is used to get list of all actors from data source.
     *
     * @return list of filled Actor beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Participant> getAllParticipants() throws DAOException;

    /**
     * This method is used to retrieve the most recently added actor from data source.
     *
     * @return filled Actor bean
     * @throws DAOException if some error occurred while processing data.
     */
    Participant getLastInsertedParticipant() throws DAOException;

    /**
     * This method is used to delete actor from data source, used only for tests.
     *
     * @param id of actor
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteParticipant(int id) throws DAOException;

}
