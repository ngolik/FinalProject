package by.golik.finalproject.service;

import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.exception.ServiceAutoException;
import by.golik.finalproject.service.exception.ServiceBanException;
import by.golik.finalproject.service.exception.ServiceException;

/**
 * @author Nikita Golik
 */
public interface UserService {
    User getUserByNickname(String nickname) throws ServiceException, ServiceAutoException;
    User register(String login, byte[] password, byte[] passwordrep, String email) throws ServiceException, ServiceAutoException;
    User authorise(String login, byte[] password) throws ServiceException, ServiceAutoException, ServiceBanException;
}
