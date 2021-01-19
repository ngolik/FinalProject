package by.golik.finalproject.service.impl;

import by.golik.finalproject.dao.*;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.*;
import by.golik.finalproject.service.MovieService;
import by.golik.finalproject.service.Validator;
import by.golik.finalproject.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class MovieServiceImpl implements MovieService {
    Logger logger = LogManager.getLogger(MovieServiceImpl.class);

    @Override
    public List<Movie> readAllMovies() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.readAllMovies();
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No participants matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public List<Movie> getFullList(int offset, int recordsPerPage) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.readAllMovies();
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            Validator.fillVotesForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)
                || !Validator.validate(genre)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesByGenre(genre, offset, recordsPerPage);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            Validator.fillVotesForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public Movie getMovieByID(String id) throws ServiceException {
        if (!Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        Movie movie;

        int normId;
        try {
            normId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No film with such ID");
        }
        try {
            movie = dao.getMovieById(normId);
            logger.info(movie.toString());
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movie;
    }

    @Override
    public Participant getParticipantByID(String id) throws ServiceException {
        if (!Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        Participant participant;

        int normId;
        try {
            normId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No film with such ID");
        }
        try {
            participant = dao.readParticipant(normId);
            logger.info(participant.toString());
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return participant;
    }

    @Override
    public List<Movie> findMovieByTitle(String title) throws ServiceException {
        if (!Validator.validate(title)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        RatingDAO ratingDAO = daoFactory.getRatingDAO();
        List<Movie> movies;
        try {
            movies = dao.searchMovieByTitle(title);
            logger.info(movies.toString());
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            Validator.fillVotesForMovie(ratingDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    @Override
    public int countAllMoviesAmount() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int amount;
        try {
            amount = dao.countAllMoviesAmount();
            if (amount == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return amount;
    }

    @Override
    public void addRating(String movieID, String userName, String rating) throws ServiceException {
        if (!Validator.validate(movieID, userName, rating)
                || !Validator.validateNumber(movieID)
                || !Validator.validateNumber(rating)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        RatingDAO dao = daoFactory.getRatingDAO();

        int intMovieID;
        int intRating;
        try {
            intMovieID = Integer.parseInt(movieID);
            intRating = Integer.parseInt(rating);
            if (intRating < 1 && intRating > 10) {
                throw new ServiceException("Wrong rating input, while adding rating");
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data input, while adding rating");
        }
        try {
            Vote vote = dao.checkRating(intMovieID, userName);
            if (vote != null) {
                dao.updateRating(intMovieID, userName, intRating);
            } else {
                dao.createRating(intMovieID, userName, intRating);
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }
    @Override
    public int countMoviesByGenre(String genre) throws ServiceException, DAOException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int amount;
        amount = dao.countMoviesByGenre(genre);
        if (amount == 0) {
            throw new ServiceException("No movies matching your query");
        }
        return amount;
    }
    @Override
    public List<Participant> readAllParticipants() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        List<Participant> participants;
        try {
            participants = dao.getAllParticipants();
            if (participants == null || participants.size() == 0) {
                throw new ServiceException("No participants matching your query");
            }
        } catch (DAOException | SQLException e) {
            throw new ServiceException("Error in source!", (ConnectionPoolException) e);
        }
        return participants;
    }

}
