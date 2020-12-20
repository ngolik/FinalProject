package by.golik.finalproject.dao.exception;

import java.sql.SQLException;

/**
 * @author Nikita Golik
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
