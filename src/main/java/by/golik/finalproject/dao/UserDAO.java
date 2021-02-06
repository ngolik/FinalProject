package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.User;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface UserDAO {

    /**
     * This method is used to register visitor of site in database.
     * @param login - username of user
     * @param email - mail of user
     * @param password - password from personal area
     * @return - user bean
     * @throws Exception - if something went wrong.
     */
    User register(String login, String email, String password) throws Exception;

    /**
     * This method is used to enter to personal area on site using personal login and password.
     * @param login - username of user
     * @param password - password from personal area of user
     * @return - user bean
     * @throws Exception - if something went wrong.
     */
    User authorise(String login, String password) throws Exception;

    /**
     * This method is used for getting list of all users.
     * @return list of all users
     * @throws DAOException - if some error occurred while processing data.
     */
    List<User> getAllUsers() throws DAOException;

    /**
     * This method is used to get user by his username
     * @param userName - username of user
     * @return - user bean
     * @throws DAOException - if some error occurred while processing data.
     */
    User getUserByUsername (String userName) throws DAOException;

    /**
     * This method is used to delete user from database
     * @param userName - username of user
     * @throws DAOException - if some error occurred while processing data.
     */
    void deleteUser(String userName) throws DAOException;
}
