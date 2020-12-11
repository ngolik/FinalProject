package by.golik.finalproject.dao.exception;

import java.sql.SQLException;

/**
 * @author Nikita Golik
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, SQLException e) {
        super(message, e);
    }

    public ConnectionPoolException(String message, InterruptedException e) {
        super(message, e);
    }
}
