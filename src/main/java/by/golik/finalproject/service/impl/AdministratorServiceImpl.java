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
    public void banUser(String userNickname) throws ServiceException {
        if (!Validator.validate(userNickname)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            User user = userDAO.getUserByUsername(userNickname);
            if (user.getRole().equals(USER)) {
                userDAO.banUser(userNickname);
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void unbanUser(String userNickname) throws ServiceException {
        if (!Validator.validate(userNickname)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            User user = userDAO.getUserByUsername(userNickname);
            if (user.getRole().equals(BANNED)) {
                userDAO.unBanUser(userNickname);
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
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
        if (!Validator.validate(title, year, runtime, budget, gross)
                || !Validator.validateYear(year)) {
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

            dao.addMovie(title, intYear, intRuntime, longBudget, longGross);

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void updateMovie(String id, String title, String year, String runtime, String budget, String gross) throws ServiceException {
        if (!Validator.validate(title, year, runtime, budget, gross)
                || !Validator.validateYear(year)
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
    public void addGenreForMovie(String movieID, String name) throws ServiceException {
        if (!Validator.validate(movieID, name)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        GenreDAO dao = daoFactory.getGenreDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.addGenreForMovie(intMovieID, name);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void deleteGenreForMovie(String movieID, String name) throws ServiceException {
        if (!Validator.validate(movieID, name)
                || !Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        GenreDAO dao = daoFactory.getGenreDAO();
        int intMovieID;
        try {
            intMovieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding film");
        }

        try {
            dao.deleteGenreForMovie(intMovieID, name);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void addParticipant(String name, String surname, String secondName) {
        if (!Validator.validate(name, surname, secondName)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        try {
            dao.addParticipant(name, surname, secondName);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }

    @Override
    public void updateParticipant(String ID, String name, String surname, String secondName) {

    }

    @Override
    public void addParticipantForMovie(String participantID, String movieID) {

    }

    @Override
    public void deleteParticipantForMovie(String participantID, String movieID) {

    }

    @Override
    public List<Participant> showAllParticipants() {
        return null;
    }

    @Override
    public void updateImage(String entity, String filename, String path) {

    }
}
