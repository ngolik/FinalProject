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

    /**
     * This method is used to get list of all movies in the system.
     * @return list of movies
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to get rating of movie.
     * @param movieID unique identifier of movie
     * @return mark from user
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public Vote getRatingForMovie(String movieID) throws ServiceException {
        if (!Validator.validateNumber(movieID)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        RatingDAO dao = daoFactory.getRatingDAO();
        Vote vote;

        int normId;
        try {
            normId = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            throw new ServiceException("No film with such ID");
        }
        try {
            vote = dao.getRatingForMovie(normId);
            logger.info(vote.toString());
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return vote;
    }

    /**
     * This method is used to get list of all movies in the system with pagination.
     * @param offset starting from
     * @param recordsPerPage amount of movies
     * @return list of filled movie beans
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getFullList(int offset, int recordsPerPage) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.readAllMovies(offset, recordsPerPage);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    /**
     * This method is used to get movies of a particular genre.
     * @param offset starting from
     * @param recordsPerPage amount of movies
     * @param genre of movies
     * @return list of movies
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getMoviesByGenre(int offset, int recordsPerPage, String genre) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)
            ||!Validator.validate(genre)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesByGenre(genre,offset, recordsPerPage);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    /**
     * This method is used to get all movies by concrete participant with pagination.
     * @param movieId - id of movie
     * @return movie bean
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public Movie getMovieByID(String movieId) throws ServiceException {
        if (!Validator.validateNumber(movieId)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        Movie movie;
        int normId;
        try {
            normId = Integer.parseInt(movieId);
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

    /**
     * This method is used to get participant by ID.
     * @param participantId unique identifier of participant
     * @return participant bean
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public Participant getParticipantByID(String participantId) throws ServiceException {
        if (!Validator.validateNumber(participantId)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        Participant participant;

        int normId;
        try {
            normId = Integer.parseInt(participantId);
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

    /**
     * This method is used to get movie by movie title.
     * @param title - title of movie
     * @return list of movies with such name
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> findMovieByTitle(String title) throws ServiceException {
        if (!Validator.validate(title)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.searchMovieByTitle(title);
            logger.info(movies.toString());
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    /**
     * This method is used to get count of all movies.
     * @return count of movies
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to add 1-10 rating for a particular movie.
     * @param movieID unique identifier of movie
     * @param userName name of user
     * @param rating mark from user to movie
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to get all movies by particular genre.
     * @param genre name of genre
     * @return count of movies
     * @throws ServiceException if any error occurred while processing method.
     * @throws DAOException some error occurred while processing data.
     */
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

    /**
     * This method is used to get all count of movies by participant
     * @param participantName name of participant
     * @param participantSurname surname of participant
     * @return count of movies
     * @throws ServiceException if any error occurred while processing method.
     * @throws DAOException some error occurred while processing data.
     */
    @Override
    public int countMoviesByParticipant(String participantName, String participantSurname) throws ServiceException, DAOException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        int amount;
        amount = dao.countMoviesByParticipant(participantName, participantSurname);
        if (amount == 0) {
            throw new ServiceException("No movies matching your query");
        }
        return amount;
    }

    /**
     * This method is used to get all participants.
     * @return list of participants
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to get all movies by concrete participant.
     * @param offset starting from
     * @param recordsPerPage amount of movies
     * @param participantName name of participant
     * @param participantSurname surname of participant
     * @return list of movies
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public List<Movie> getMoviesByParticipant(int offset, int recordsPerPage, String participantName, String participantSurname) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)
                ||!Validator.validate(participantName)
                || !Validator.validate(participantSurname)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesByParticipant(participantName, participantSurname, offset, recordsPerPage);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }
}
