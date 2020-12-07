package by.golik.finalproject.dao;

import by.golik.finalproject.entity.User;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface UserDAO {
    User authorise(String login, String password);
    User register(String email, String login, String password, String sex);
    List<User> getAllUsers();
    User getUserByUsername (String userName);
    void banUser(String userName);
    void unBanUser(String userName);
    void deleteUser(String userName);
    void updateImage(String fileName, String path);
}
