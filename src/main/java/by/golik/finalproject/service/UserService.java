package by.golik.finalproject.service;

import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.exception.ServiceAuthorizationException;
import by.golik.finalproject.service.exception.ServiceBanException;
import by.golik.finalproject.service.exception.ServiceException;

/**
 * @author Nikita Golik
 */
public interface UserService {
    User getUserByNickname(String nickname) throws ServiceException, ServiceAuthorizationException;
    User register(String login, byte[] password, byte[] passwordrep, String email) throws ServiceException, ServiceAuthorizationException;
    User authorise(String login, byte[] password) throws ServiceException, ServiceAuthorizationException, ServiceBanException;
}
