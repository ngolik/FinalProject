package by.golik.finalproject.service.impl;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.MovieDAO;
import by.golik.finalproject.dao.ParticipantDAO;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.ParticipantService;
import by.golik.finalproject.service.Validator;
import by.golik.finalproject.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class ParticipantServiceImpl implements ParticipantService {
    @Override
    public Participant getParticipant(String id) throws ServiceException {
            if (!Validator.validateNumber(id)) {
                throw new ServiceException("Illegal data input");
            }
            DaoFactory daoFactory = DaoFactory.getInstance();
            MovieDAO movieDAO = daoFactory.getMovieDAO();
            ParticipantDAO participantDAO = daoFactory.getParticipantDAO();
            Participant participant;
            List<Movie> movieList;
            int normId;
            try {
                normId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                throw new ServiceException("No person with such ID");
            }
            try {
                participant = participantDAO.readParticipant(normId);
                if (participant != null) {
                    movieList = movieDAO.getMoviesByParticipant(normId);

                    participant.setMovies(movieList);

                } else {
                    throw new ServiceException("No persons matching your query");
                }
            } catch (DAOException e) {
                throw new ServiceException("Error in source!", e);
            }
            return participant;
    }

    @Override
    public List<Participant> getFullList(int offset, int recordsPerPage) throws ServiceException {
        if (!Validator.validate(offset, recordsPerPage)) {
            throw new ServiceException("Illegal data input");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        List<Participant> participants;
        try {
            participants = dao.getAllParticipants(offset, recordsPerPage);
            if (participants == null || participants.size() == 0) {
                throw new ServiceException("No participants matching your query");
            }
        } catch (DAOException | SQLException e) {
            throw new ServiceException("Error in source!");
        }
        return participants;
    }

    @Override
    public int countAllParticipantsAmount() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        ParticipantDAO dao = daoFactory.getParticipantDAO();
        int amount;
        try {
            amount = dao.countAllParticipantsAmount();
            if (amount == 0) {
                throw new ServiceException("No movies matching your query");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error in source!", e);
        }
        return amount;
    }
}
