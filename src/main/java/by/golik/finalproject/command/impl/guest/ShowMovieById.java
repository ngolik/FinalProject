package by.golik.finalproject.command.impl.guest;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.entity.Vote;
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

/**
 * @author Nikita Golik
 */
public class ShowMovieById implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/common/moviePage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ShowMovieById.class);

    private static final String ID = "id";

    private static final String REQUEST_ATTRIBUTE = "movie";
    private static final String REQUEST_ATTRIBUTE_RATING = "vote";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No movie with such id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandHelper.saveCurrentQueryToSession(request);

        String id = request.getParameter(ID);
        Movie movie;
        Vote vote;

        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            movie = movieService.getMovieByID(id);
            vote = movieService.getRatingForMovie(id);
            request.setAttribute(REQUEST_ATTRIBUTE, movie);
            request.setAttribute(REQUEST_ATTRIBUTE_RATING, vote);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            logger.info("Error in command");
        }
    }
}
