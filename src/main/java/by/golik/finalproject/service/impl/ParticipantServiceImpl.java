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
                participant = participantDAO.getParticipant(normId);
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
}
