package by.golik.finalproject.command.impl.admin;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
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
//public class AddParticipantForMovie implements Command {
//
//    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/admin/addParticipantForMoviePage.jsp";
//    private static final String REDIRECT = "DispatcherServlet?command=add-participant-for-movie";
//    private static final Logger logger = LogManager.getLogger(AddParticipantForMovie.class);
//    private static final String MOVIE_ID = "movieID";
//    private static final String PARTICIPANT_ID = "participantID";
//    private static final String MOVIE_LIST = "movies";
//    private static final String PARTICIPANT_LIST = "participants";
//
//    private static final String ERROR = "errorParticipant";
//    private static final String MESSAGE_OF_ERROR = "Cannot add participant for movie";
//    private static final String ADD_PARTICIPANT_FOR_MOVIE = "add_participant_for_movie";
//    private static final String MESSAGE_OF_SUCCESS = "Participant successfully added";
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
//        CommandHelper.saveCurrentQueryToSession(request);
//        String movieID = request.getParameter(MOVIE_ID);
//        String participantID = request.getParameter(PARTICIPANT_ID);
//
//        List<Movie> movies;
//        List<Participant> participants;
//
//        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);
//        MovieService movieService = ServiceFactory.getInstance().getMovieService();
//        movies = movieService.readAllMovies();
//        participants = movieService.readAllParticipants();
//
//        request.setAttribute(MOVIE_LIST, movies);
//        request.setAttribute(PARTICIPANT_LIST, participants);
//
//        if (participantID == null && movieID == null) {
//            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
//        } else {
//            try {
//                administratorService.addParticipantForMovie(movieID, participantID);
//                request.setAttribute(ADD_PARTICIPANT_FOR_MOVIE, MESSAGE_OF_SUCCESS);
//                response.sendRedirect(REDIRECT);
//            }  catch (ServiceException e) {
//                logger.log(Level.ERROR, e.getMessage(), e);
//                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
//                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
//            }
//        }
//    }
//}
