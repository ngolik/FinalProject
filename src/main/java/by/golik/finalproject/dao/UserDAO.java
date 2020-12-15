package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.User;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface UserDAO {
    User authorise(String login, String password) throws DAOException;
    User register(String email, String login, String password) throws DAOException;
    List<User> getAllUsers() throws DAOException;
    User getUserByUsername (String userName) throws DAOException;
    void banUser(String userName);
    void unBanUser(String userName);
    void deleteUser(String userName) throws DAOException;
}
