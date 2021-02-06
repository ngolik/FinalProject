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

    /**
     * This method is used to show all users registered in the system.
     * @return List of User beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add new movie to the system.
     * @param title movie title
     * @param year year of movie
     * @param runtime runtime of movie
     * @param budget budget of movie
     * @param gross gross of movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to update any movie information.
     * @param id unique identifier of movie
     * @param title movie title
     * @param year year of movie
     * @param runtime runtime of movie
     * @param budget budget of movie
     * @param gross gross of movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add any genre for any movie.
     * @param movieID unique identifier of movie
     * @param genreID - unique identifier of genre
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to delete genre for movie.
     * @param movieID unique identifier of movie
     * @param genreID unique identifier of genre
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add new participant to the system.
     * @param name - name of participant
     * @param surName - surname of participant
     * @param secondName - second name of participant
     * @param position - role of participant
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to update any participant information.
     * @param participantID - unique identifier of participant
     * @param name - name of participant
     * @param surname - surname of participant
     * @param secondName - second name of participant
     * @param position - role of participant
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void updateParticipant(String participantID, String name, String surname, String secondName, String position) throws ServiceException {
        if (!Validator.validate(name, surname, secondName, position)
                || !Validator.validateNumber(participantID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        int intActorId;
        try {
            intActorId = Integer.parseInt(participantID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while updating actor for movie");
        }
        try {
            dao.updateParticipant(intActorId, name, surname, secondName, position);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    /**
     * This method is used to add any participant for any movie.
     * @param participantID unique identifier of participant
     * @param movieID unique identifier of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void addParticipantForMovie(String participantID, String movieID) throws ServiceException {
        if (!Validator.validateNumber(participantID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        int intMovieId;
        int intParticipantId;
        try {
            intMovieId = Integer.parseInt(movieID);
            intParticipantId = Integer.parseInt(participantID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding actor for movie");
        }
        try {
            dao.addParticipantForMovie(intParticipantId, intMovieId);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    /**
     * This method is used to delete participant from any movie.
     * @param participantID unique identifier of participant
     * @param movieID unique identifier of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void deleteParticipantForMovie(String participantID, String movieID) throws ServiceException {
        if (!Validator.validateNumber(participantID)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        int intParticipantID;
        int intMovieID;
        try {
            intParticipantID = Integer.parseInt(participantID);
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.deleteParticipantForMovie(intParticipantID, intMovieID);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    /**
     * This method lets admin to delete user from system
     * @param userName - name of user
     * @throws DAOException if some error occurred while getting information from database.
     * @throws ServiceException if any error occurred while processing method.
     */
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
