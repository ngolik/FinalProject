package by.golik.finalproject.dao.exception;

import java.sql.SQLException;

/**
 * @author Nikita Golik
 */
public class DAOException extends Exception {
    public DAOException(String message, SQLException e) {
        super(message, e);
    }

    public DAOException(String message, ConnectionPoolException e) {
        super(message, e);
    }

    public DAOException(String message) {
        super(message);
    }

}
