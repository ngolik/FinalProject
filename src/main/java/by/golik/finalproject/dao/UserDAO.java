package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.User;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface UserDAO {
    User authorise(String login, String password) throws Exception;
    User register(String login, String email, String password) throws Exception;
    List<User> getAllUsers() throws DAOException;
    public void banUser(String userName) throws DAOException;
    public void unBanUser(String userName) throws DAOException;
    User getUserByUsername (String userName) throws DAOException;
    void deleteUser(String userName) throws DAOException;
}
