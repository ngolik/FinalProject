package by.golik.finalproject.command.impl.user;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.entity.User;
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
public class AddRating implements Command {

    private static final Logger logger = LogManager.getLogger(AddRating.class);

    private static final String MOVIE_ID = "movieID";
    private static final String USER = "user";
    private static final String RATING = "rating";

    private static final String ERROR_RATING = "errorRating";
    private static final String MESSAGE_OF_ERROR = "Cannot add rating to movie";
    private static final String MESSAGE_OF_ERROR_2 = "Fill in all fields";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String previousQuery = CommandHelper.getPreviousQuery(request);

        String movieID = request.getParameter(MOVIE_ID);
        String userName = null;

        Object object = request.getSession(false).getAttribute(USER);

        if (object.getClass().equals(User.class)) {
            User user = (User) object;
            userName = user.getUsername();
        }

        String rating = request.getParameter(RATING);
        MovieService movieService = ServiceFactory.getInstance().getMovieService();

        if (movieID != null && userName != null && rating != null) {
            try {
                movieService.addRating(movieID, userName, rating);

                request.getRequestDispatcher(previousQuery).forward(request, response);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR_RATING, MESSAGE_OF_ERROR);

                request.getRequestDispatcher(previousQuery).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR_RATING, MESSAGE_OF_ERROR_2);

            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
