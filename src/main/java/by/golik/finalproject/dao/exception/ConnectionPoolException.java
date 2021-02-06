package by.golik.finalproject.dao.exception;


/**
 * ConnectionPoolException is thrown when error with ConnectionPool occurred.
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
