package by.golik.finalproject.dao.mysql;

import java.sql.Connection;

/**
 * @author Nikita Golik
 */
abstract public class BaseDaoImpl {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
