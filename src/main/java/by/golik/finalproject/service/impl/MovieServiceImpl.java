package by.golik.finalproject.service.impl;

import by.golik.finalproject.dao.*;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.*;
import by.golik.finalproject.service.MovieService;
import by.golik.finalproject.service.Validator;
import by.golik.finalproject.service.exception.ServiceException;

import java.util.List;

/**
 * @author Nikita Golik
 */
public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> getFullList() throws ServiceException {

        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        VoteDAO ratingDAO = daoFactory.getVoteDAO();
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
        VoteDAO voteDAO = daoFactory.getVoteDAO();
        List<Movie> movies;
        try {
            movies = dao.getMoviesByGenre(genre, offset, recordsPerPage);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            Validator.fillVotesForMovie(voteDAO, movies);
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movies;
    }

    //TODO (producer, director)
    @Override
    public Movie getMovieByID(int offset, int recordsPerPage, String id, String lang) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)
                || !Validator.validate(id, lang)
                || !Validator.validateNumber(id)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        VoteDAO ratingDAO = daoFactory.getVoteDAO();
        GenreDAO genreDAO = daoFactory.getGenreDAO();
        ParticipantDAO participantDAO = daoFactory.getParticipantDAO();
        Movie movie;
        List<Vote> voteList;
        List<Genre> genreList;
        List<Participant> participantList;

        int normId;
        try {
            normId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ServiceException("No film with such ID");
        }
        try {
            movie = dao.getMovieById(normId);
            if (movie != null) {

                genreList = genreDAO.readGenresByMovie(normId);
                voteList = ratingDAO.getVotesForMovie(normId);
                participantList = participantDAO.getParticipantsForMovie(normId);
                movie.setGenreList(genreList);
                movie.setVotes(voteList);
                movie.setParticipants(participantList);

            } else {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return movie;
    }

    @Override
    public List<Movie> findMovieByTitle(String title) throws ServiceException {
        if (!Validator.validate(title)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        MovieDAO dao = daoFactory.getMovieDAO();
        VoteDAO voteDAO = daoFactory.getVoteDAO();
        List<Movie> movies;
        try {
            movies = dao.searchMovieByTitle(title);
            if (movies == null || movies.size() == 0) {
                throw new ServiceException("No movies matching your query");
            }
            Validator.fillVotesForMovie(voteDAO, movies);
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
    public void addVote(String movieID, String userName, String rating) throws ServiceException {
        if (!Validator.validate(movieID, userName, rating)
                || !Validator.validateNumber(movieID)
                || !Validator.validateNumber(rating)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        VoteDAO dao = daoFactory.getVoteDAO();

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
            Vote vote = dao.checkVotes(intMovieID, userName);
            if (vote != null) {
                dao.updateVotes(intMovieID, userName, intRating);
            } else {
                dao.createVotes(intMovieID, userName, intRating);
            }

        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
    }
}
