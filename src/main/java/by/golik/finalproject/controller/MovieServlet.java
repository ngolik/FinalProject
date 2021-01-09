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

        DaoFactory daoFactory;
        ConnectionPool connectionPool;
        MovieDAO movieDAO;
        UserDAO userDAO;

        daoFactory = DaoFactory.getInstance();
        connectionPool = daoFactory.getConnectionPool();

//        movieDAO = daoFactory.getMovieDAO();
//        userDAO = daoFactory.getUserDAO();

        connectionPool.init();

        UserServiceImpl userService = new UserServiceImpl();
        AdministratorServiceImpl administratorService = new AdministratorServiceImpl();

//        System.out.println(userService.getUserByUserName("administrator").toString());
//        System.out.println(userDAO.authorise("admin", "adminpass"));

//        userService.authorise("admin", "administrator".getBytes());
//        userService.register("test4", "12345678".getBytes(), "12345678".getBytes(), "test4@mail.ru");

//        administratorService.addParticipant("testname", "testsurname", "testSecond");
//        administratorService.addParticipantForMovie("3","2");
//        administratorService.addMovie("testService", "1000", "1500", "1500", "1500");
//        administratorService.addGenreForMovie("3", "1");

    }
}


