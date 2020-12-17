package by.golik.finalproject.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

/**
 * @author Nikita Golik
 */


public class MovieServlet {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

    String url = "jdbc:mysql://localhost/movies_db?serverTimezone=Europe/Moscow&useSSL=false";
    String username = "root";
    String password = "canada@123";
    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from genres");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
        }
        }
    }

}
