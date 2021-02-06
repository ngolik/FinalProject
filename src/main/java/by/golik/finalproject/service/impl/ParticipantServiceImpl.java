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

    /**
     * This method shows any participant by it's id.
     * @param participantId - id of participant
     * @return Participant bean object
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public Participant getParticipant(String participantId) throws ServiceException {
            if (!Validator.validateNumber(participantId)) {
                throw new ServiceException("Illegal data input");
            }
            DaoFactory daoFactory = DaoFactory.getInstance();
            MovieDAO movieDAO = daoFactory.getMovieDAO();
            ParticipantDAO participantDAO = daoFactory.getParticipantDAO();
            Participant participant;
            List<Movie> movieList;
            int normId;
            try {
                normId = Integer.parseInt(participantId);
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

    /**
     * This method shows list of all participants.
     * @param offset - page
     * @param recordsPerPage - maximum count of records on page
     * @return list of participants
     * @throws ServiceException if any error occurred while processing method.
     */
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

    /**
     * This method is used to get count of all participants
     * @return count of participants
     * @throws ServiceException if any error occurred while processing method.
     */
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
