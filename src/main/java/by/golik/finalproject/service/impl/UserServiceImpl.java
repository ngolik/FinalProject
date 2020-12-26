package by.golik.finalproject.service.impl;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.UserDAO;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.UserService;
import by.golik.finalproject.service.Validator;
import by.golik.finalproject.service.exception.ServiceAutoException;
import by.golik.finalproject.service.exception.ServiceBanException;
import by.golik.finalproject.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Nikita Golik
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String BANNED = "banned";

    @Override
    public User getUserByNickname(String userName) throws ServiceException, ServiceAutoException {
        if (!Validator.validate(userName)) {
            throw new ServiceAutoException("Wrong username!");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        User user;
        try {
            user = dao.getUserByUsername(userName);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return user;
    }

    @Override
    public User register(String login, byte[] password, byte[] passwordrep, String email) throws ServiceException, ServiceAutoException {
        if (!Validator.validate(login, email) ||
                !Validator.validateLogin(login) ||
                !Validator.validatePassword(password, passwordrep) ||
                !Validator.validateEmail(email)) {
            throw new ServiceAutoException("Check input parameters");
        }
        String encodedPassword = Validator.encodePassword(password);
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        User user;
        try {
            user = dao.register(login, email, encodedPassword);

            if (user == null) {
                throw new ServiceAutoException("Wrong login or password!");
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }

        return user;
    }

    @Override
    public User authorise(String login, byte[] password) throws ServiceException, ServiceAutoException, ServiceBanException {
        logger.debug("authorise begin");
        if (!Validator.validateLogin(login) ||
                !Validator.validatePassword(password)) {
            throw new ServiceAutoException("Wrong parameters!");
        }
        String encodedPassword = Validator.encodePassword(password);
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        User user;
        try {
            user = dao.authorise(login, encodedPassword);

            if (user == null) {
                throw new ServiceAutoException("Wrong login or password!");
            } else if (user.getRole().equals(BANNED)) {
                throw new ServiceBanException("Sorry access for you is temporary unavailable");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source", e);
        }

        return user;
    }
}
