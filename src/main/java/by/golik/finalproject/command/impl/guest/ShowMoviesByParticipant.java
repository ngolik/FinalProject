package by.golik.finalproject.command.impl.guest;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.service.MovieService;
import by.golik.finalproject.service.ServiceFactory;
import by.golik.finalproject.service.exception.ServiceException;
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
public class ShowMoviesByParticipant implements Command {

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/common/moviesPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ShowMoviesByGenre.class);

    private static final String PARTICIPANT_NAME = "participantName";
    private static final String PARTICIPANT_SURNAME = "participantSurname";

    private static final String PAGE = "page";
    private static final String AMOUNT_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final int RECORDS_PER_PAGE = 10;

    private static final String REQUEST_ATTRIBUTE = "all_movies";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No movies matching your query";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        CommandHelper.saveCurrentQueryToSession(request);

        String participantName = request.getParameter(PARTICIPANT_NAME);
        String participantSurname = request.getParameter(PARTICIPANT_SURNAME);

        List<Movie> movies;
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            int page = 1;
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            }
            movies = movieService.getMoviesByParticipant((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, participantName, participantSurname);
            request.setAttribute(REQUEST_ATTRIBUTE, movies);

//            int numberOfMovies = movieService.countMoviesByGenre(genre);
//            int noOfPages = (int) Math.ceil(numberOfMovies * 1.0 / RECORDS_PER_PAGE);

//            request.setAttribute(AMOUNT_OF_PAGES, noOfPages);
//            request.setAttribute(CURRENT_PAGE, page);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
