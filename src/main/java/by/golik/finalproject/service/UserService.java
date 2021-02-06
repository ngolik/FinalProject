package by.golik.finalproject.service;

import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.exception.ServiceAuthorizationException;
import by.golik.finalproject.service.exception.ServiceException;

/**
 * UserService is an interface mainly used to interact with user entity.
 * @author Nikita Golik
 */
public interface UserService {

    /**
     * This method is used to show any user by the username.
     * @param userName - nickname of user
     * @return User bean with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     * @throws ServiceAuthorizationException if any error occurred while processing method.
     */
    User getUserByUserName(String userName) throws ServiceException, ServiceAuthorizationException;

    /**
     * This method is used to register and give access to the system for
     * some new visitor.
     * @param login of user
     * @param password of user
     * @param passwordrep repeat password of user
     * @param email of user
     * @return User bean
     * @throws Exception if any error occurred while processing method.
     */
    User register(String login, byte[] password, byte[] passwordrep, String email) throws Exception;

    /**
     * This method is used to let user enter his account in the system.
     * @param login of user
     * @param password of user
     * @return USer bean
     * @throws Exception if any error occurred while processing method.
     */
    User authorise(String login, byte[] password) throws Exception;
}
