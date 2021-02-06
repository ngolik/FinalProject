package by.golik.finalproject.command.impl.admin;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.service.AdministratorService;
import by.golik.finalproject.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is used to handle client request to
 * delete genre from particular movie.
 * @author Nikita Golik
 */
public class DeleteGenreForMovie implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteGenreForMovie.class);

    private static final String MOVIE_ID = "id";
    private static final String GENRE = "genre";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot delete review";
    private static final String MESSAGE_OF_ERROR_2 = "Fill in all fields";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String previousQuery = CommandHelper.getPreviousQuery(request);
        String movieID = request.getParameter(MOVIE_ID);
        String genre = request.getParameter(GENRE);

        AdministratorService administratorService = AdministratorHelper.getAdministratorService(request, response);

        if(movieID != null && genre != null) {
            try {
                administratorService.deleteGenreForMovie(movieID, genre);
                response.sendRedirect(previousQuery);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(previousQuery).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
