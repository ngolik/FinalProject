package by.golik.finalproject.controller;

import by.golik.finalproject.dao.*;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.service.impl.AdministratorServiceImpl;
import by.golik.finalproject.service.impl.MovieServiceImpl;
import by.golik.finalproject.service.impl.UserServiceImpl;

/**
 * @author Nikita Golik
 */


public class MovieServlet {
    public static void main(String[] args) throws Exception {


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
        UserDAO userDAO;

        daoFactory = DaoFactory.getInstance();
        connectionPool = daoFactory.getConnectionPool();

//        movieDAO = daoFactory.getMovieDAO();
//        userDAO = daoFactory.getUserDAO();

        connectionPool.init();
//        System.out.println(movieDAO.searchMovieByTitle("власть").toString());
        MovieServiceImpl movieService = new MovieServiceImpl();
        System.out.println(movieService.findMovieByTitle("власть").toString());

        UserServiceImpl userService = new UserServiceImpl();

        System.out.println(userService.getUserByUserName("administrator").toString());
//        System.out.println(userDAO.authorise("admin", "adminpass"));

//        userService.authorise("admin", "administrator".getBytes());
//        userService.register("test4", "12345678".getBytes(), "12345678".getBytes(), "test4@mail.ru");
        AdministratorServiceImpl administratorService = new AdministratorServiceImpl();
//        administratorService.addParticipant("testname", "testsurname", "testSecond");
        administratorService.addParticipantForMovie("1","2");

    }
}


