package by.golik.finalproject.service;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.exception.ServiceException;
import java.util.List;

/**
 * AdministratorService interface represents tools available only for
 * admin to perform any kind of actions with any bean entities represented
 * @author Nikita Golik
 */
public interface AdministratorService {
    /**
     * This method is used to show all users registered in the system.
     * @return List of User beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    List<User> readAllUsers() throws ServiceException;

    /**
     * This method is used to add new movie to the system.
     * @param title movie title
     * @param year year of movie
     * @param runtime runtime of movie
     * @param budget budget of movie
     * @param gross gross of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void addMovie(String title, String year, String runtime, String budget, String gross) throws ServiceException;

    /**
     * This method is used to update any movie information.
     * @param id unique identifier of movie
     * @param title movie title
     * @param year year of movie
     * @param runtime runtime of movie
     * @param budget budget of movie
     * @param gross gross of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void updateMovie(String id, String title, String year, String runtime, String budget, String gross) throws ServiceException;

    /**
     * This method is used to add any genre for any movie.
     * @param movieID unique identifier of movie
     * @param name - name of genre
     * @throws ServiceException if any error occurred while processing method.
     */
    void addGenreForMovie(String movieID, String name) throws ServiceException;

    /**
     * This method is used to delete genre for movie.
     * @param movieID unique identifier of movie
     * @param genreID unique identifier of genre
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteGenreForMovie(String movieID, String genreID) throws ServiceException;

    /**
     * This method is used to add new participant to the system.
     * @param name - name of participant
     * @param surname - surname of participant
     * @param secondName - second name of participant
     * @param position - role of participant
     * @throws ServiceException if any error occurred while processing method.
     */
    void addParticipant(String name, String surname, String secondName, String position) throws ServiceException;

    /**
     * This method is used to update any participant information.
     * @param participantID - unique identifier of participant
     * @param name - name of participant
     * @param surname - surname of participant
     * @param secondName - second name of participant
     * @param position - role of participant
     * @throws ServiceException if any error occurred while processing method.
     */
    void updateParticipant(String participantID, String name, String surname, String secondName, String position) throws ServiceException;

    /**
     * This method is used to add any participant for any movie.
     * @param participantID unique identifier of participant
     * @param movieID unique identifier of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void addParticipantForMovie(String participantID, String movieID) throws ServiceException;

    /**
     * This method is used to delete participant from any movie.
     * @param participantID unique identifier of participant
     * @param movieID unique identifier of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteParticipantForMovie(String participantID, String movieID) throws ServiceException;

    /**
     * This method lets admin to delete user from system
     * @param userName - name of user
     * @throws DAOException if some error occurred while getting information from database.
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteUser(String userName) throws DAOException, ServiceException;

}
