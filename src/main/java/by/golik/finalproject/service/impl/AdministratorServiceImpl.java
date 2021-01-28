package by.golik.finalproject.service.impl;

import by.golik.finalproject.dao.*;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.AdministratorService;
import by.golik.finalproject.service.Validator;
import by.golik.finalproject.service.exception.ServiceException;

import java.util.List;

/**
 * @author Nikita Golik
 */
public class AdministratorServiceImpl implements AdministratorService {
    private static final String USER = "user";
    private static final String BANNED = "banned";
    private static final String MOVIE = "movies";
    private static final String PARTICIPANT = "participants";
    private static final String USERS = "users";
    private static final String IMAGES = "images/";
    private static final String DELIM = "/";

    @Override
    public List<User> readAllUsers() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        List<User> users;
        try {
            users = dao.getAllUsers();
            if (users == null || users.size() == 0) {
                throw new ServiceException("No users matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return users;
    }

    @Override
    public void addMovie(String title, String year, String runtime, String budget, String gross) throws ServiceException {
        if (!Validator.validate(title)
                || !Validator.validateYear(year)
                || !Validator.validateNumber(runtime)
                || !Validator.validateNumber(budget)
                || !Validator.validateNumber(gross)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int intYear;
        int intRuntime;
        long longBudget;
        long longGross;
        try {
            intYear = Integer.parseInt(year);
            intRuntime = Integer.parseInt(runtime);
            longBudget = Long.parseLong(budget);
            longGross = Long.parseLong(gross);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.createMovie(title, intYear, intRuntime, longBudget, longGross);

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void updateMovie(String id, String title, String year, String runtime, String budget, String gross) throws ServiceException {
        if (!Validator.validate(title)
                || !Validator.validateYear(year)
                || !Validator.validateNumber(runtime)
                || !Validator.validateNumber(budget)
                || !Validator.validateNumber(gross)
                || !Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int intID;
        int intYear;
        int intRuntime;
        long longBudget;
        long longGross;
        try {
            intID = Integer.parseInt(id);
            intYear = Integer.parseInt(year);
            intRuntime = Integer.parseInt(runtime);
            longBudget = Long.parseLong(budget);
            longGross = Long.parseLong(gross);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.updateMovie(intID, title, intYear, intRuntime, longBudget, longGross);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addGenreForMovie(String movieID, String genreID) throws ServiceException {
        if (!Validator.validateNumber(genreID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        GenreDAO dao = daoFactory.getGenreDAO();
        int intMovieID;
        int intGenreID;
        try {
            intMovieID = Integer.parseInt(movieID);
            intGenreID = Integer.parseInt(genreID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.createGenreForMovie(intMovieID, intGenreID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteGenreForMovie(String movieID, String genreID) throws ServiceException {
        if (!Validator.validateNumber(genreID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        GenreDAO dao = daoFactory.getGenreDAO();
        int intMovieID;
        int intGenreID;
        try {
            intMovieID = Integer.parseInt(movieID);
            intGenreID = Integer.parseInt(genreID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.deleteGenreForMovie(intMovieID, intGenreID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addParticipant(String name, String surName, String secondName, String position) throws ServiceException {
        if (!Validator.validate(name, surName, secondName, position)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        try {
            dao.createParticipant(name, surName, secondName, position);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void updateParticipant(String id, String name, String surname, String secondName,String position) throws ServiceException {
        if (!Validator.validate(name, surname, secondName, position)
                || !Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        int intActorId;
        try {
            intActorId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while updating actor for movie");
        }
        try {
            dao.updateParticipant(intActorId, name, surname, secondName, position);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addParticipantForMovie(String participantId, String movieId) throws ServiceException {
        if (!Validator.validateNumber(participantId)
                || !Validator.validateNumber(movieId)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        int intMovieId;
        int intParticipantId;
        try {
            intMovieId = Integer.parseInt(movieId);
            intParticipantId = Integer.parseInt(participantId);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.addParticipantForMovie(intParticipantId, intMovieId);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteParticipantForMovie(String participantID, String movieID) {

    }

    @Override
    public void updateImage(String entity, String filename, String path) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO movieDAO;
        try {
            switch (entity) {
                case MOVIE:
                    movieDAO = daoFactory.getMovieDAO();
                    int id = Integer.parseInt(filename);
                    movieDAO.updateImage(id, IMAGES + MOVIE + DELIM + path);
                    break;
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteUser(String userName) throws DAOException, ServiceException {
        if (!Validator.validate(userName)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDAO dao = daoFactory.getUserDAO();
        try {
            dao.deleteUser(userName);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }
}
