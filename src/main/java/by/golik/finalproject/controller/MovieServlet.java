package by.golik.finalproject.controller;

import by.golik.finalproject.dao.*;
import by.golik.finalproject.dao.mysql.GenreDaoImpl;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Genre;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.service.impl.AdministratorServiceImpl;
import by.golik.finalproject.service.impl.MovieServiceImpl;
import by.golik.finalproject.service.impl.UserServiceImpl;

import java.util.List;

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
        GenreDAO genreDAO;
        ParticipantDAO participantDAO;
        daoFactory = DaoFactory.getInstance();
        connectionPool = daoFactory.getConnectionPool();

//        movieDAO = daoFactory.getMovieDAO();
//        userDAO = daoFactory.getUserDAO();

        connectionPool.init();

        UserServiceImpl userService = new UserServiceImpl();
        AdministratorServiceImpl administratorService = new AdministratorServiceImpl();
        MovieServiceImpl movieService = new MovieServiceImpl();
        genreDAO = daoFactory.getGenreDAO();
        movieDAO = daoFactory.getMovieDAO();
        participantDAO = daoFactory.getParticipantDAO();
//        System.out.println(administratorService.readAllParticipants());

//        System.out.println(userService.getUserByUserName("administrator").toString());
//        System.out.println(userDAO.authorise("admin", "adminpass"));

//        userService.authorise("admin", "administrator".getBytes());
//        userService.register("test4", "12345678".getBytes(), "12345678".getBytes(), "test4@mail.ru");

//        administratorService.addParticipant("lol", "lal", "fam", "actor");
//        administratorService.addParticipantForMovie("3","2");
        administratorService.addMovie("testService2", "1000", "1500", "1500", "1500");
//        administratorService.addGenreForMovie("5", "3");
//        administratorService.updateParticipant("1", "saasfs","sa","dagger");
//        administratorService.updateMovie("4","nikita", "1995","156", "152", "138");
//        administratorService.addGenreForMovie("5", "drama");
//        administratorService.deleteUser("test2");
//        movieService.addRating("1", "6", "7");
//        System.out.println(movieService.getMovieByID("1").toString());
//        administratorService.addParticipantForMovie("10", "10");
//        movieService.findMovieByTitle("nikita");
//        System.out.println(movieService.getMoviesByGenre(1,10, "comedy"));
//        genreDAO.createGenreForMovie(22,3);
//        genreDAO.deleteGenreForMovie(22,3);
//           System.out.println(movieDAO.getMoviesByParticipant("nikirta", "fas", 1,10).toString());
//        System.out.println(movieService.getParticipantByID("49"));
//            administratorService.deleteParticipantForMovie("1", "2");

    }
}


