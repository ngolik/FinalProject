package by.golik.finalproject.service;

import by.golik.finalproject.entity.User;

/**
 * @author Nikita Golik
 */
public interface UserService {
    User getUserByNickname(String nickname);
    User register(String login, byte[] password, byte[] passwordrep, String email);
    User authorize(String login, byte[] password);
}
