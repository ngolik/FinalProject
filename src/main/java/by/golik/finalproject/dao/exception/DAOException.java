package by.golik.finalproject.dao.exception;

import java.sql.SQLException;

/**
 * @author Nikita Golik
 */
public class DAOException extends Exception {
    public DAOException(String s, SQLException e) {
        super(s, e);
    }
    public DAOException(String s) {
        super(s);
    }
}
