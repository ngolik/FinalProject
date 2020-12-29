package by.golik.finalproject.controller;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.GenreDAO;
import by.golik.finalproject.dao.MovieDAO;
import by.golik.finalproject.dao.ParticipantDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.mysql.ParticipantDaoImpl;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.dao.pool.ConnectionPoolMovie;
import by.golik.finalproject.dao.pool.PooledConnection;
import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.exception.ServiceException;
import by.golik.finalproject.service.impl.AdministratorServiceImpl;
import by.golik.finalproject.service.impl.MovieServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;

/**
 * @author Nikita Golik
 */


public class MovieServlet {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, DAOException, ServiceException, ConnectionPoolException {


        String url = "jdbc:mysql://localhost/test_db?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";
        String password = "canada@123";
//        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from genres");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1));
//                System.out.println(resultSet.getString(2));
//            }


        DaoFactory daoFactory;
        ConnectionPool connectionPool;
        MovieDAO movieDAO;
        daoFactory = DaoFactory.getInstance();
        connectionPool = daoFactory.getConnectionPool();
        movieDAO = daoFactory.getMovieDAO();
        connectionPool.init();
        System.out.println(movieDAO.searchMovieByTitle("власть").toString());
        MovieServiceImpl movieService = new MovieServiceImpl();
        System.out.println(movieService.findMovieByTitle("власть").toString());
    }
}


