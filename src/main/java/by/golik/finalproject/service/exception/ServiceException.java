package by.golik.finalproject.service.exception;

import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;

/**
 * @author Nikita Golik
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, DAOException e) {
        super(message, e);
    }

    public ServiceException(String message, ConnectionPoolException e) {
        super(message, e);
    }
}
