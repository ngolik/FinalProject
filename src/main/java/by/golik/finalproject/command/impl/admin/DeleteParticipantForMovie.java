package by.golik.finalproject.command.impl.admin;


import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.AdministratorService;
import by.golik.finalproject.service.MovieService;
import by.golik.finalproject.service.ServiceFactory;
import by.golik.finalproject.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class DeleteParticipantForMovie implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteParticipantForMovie.class);

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/admin/deleteParticipantForMovie.jsp";
    private static final String PARTICIPANT_ID = "participantID";
    private static final String MOVIE_ID = "movieID";
    private static final String PARTICIPANTS = "participants";
    private static final String MOVIES = "movies";
    private static final String ERROR = "errorMessage";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Participant successfully deleted";
    private static final String MESSAGE_OF_ERROR = "Cannot delete participant for movie, wrong input";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {

        String participantID = request.getParameter(PARTICIPANT_ID);
        String movieID = request.getParameter(MOVIE_ID);
        List<Movie> movies;
        List<Participant> participants;

        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        movies = movieService.readAllMovies();
        participants = movieService.readAllParticipants();
        request.setAttribute(MOVIES, movies);
        request.setAttribute(PARTICIPANTS, participants);

        if (participantID != null || movieID != null) {
            administratorService.deleteParticipantForMovie(participantID, movieID);
            request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
        }
        request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
    }
}
