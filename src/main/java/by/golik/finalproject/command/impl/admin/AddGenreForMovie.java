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
 * @author Nikita Golik
 */
public class AddGenreForMovie implements Command {

    private static final Logger logger = LogManager.getLogger(AddGenreForMovie.class);
    private static final String ID = "id";
    private static final String NAME = "name";

    private static final String ERROR = "errorGenre";
    private static final String MESSAGE_OF_ERROR = "Cannot add genre for movie";
    private static final String MESSAGE_OF_ERROR_2 = "Cannot add genre for movie, wrong input";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String movieID = request.getParameter(ID);
        String name = request.getParameter(NAME);
        String previousQuery = CommandHelper.getPreviousQuery(request);

        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);

        if (name != null && movieID !=null) {
            try {
                administratorService.addGenreForMovie(movieID, name);

                response.sendRedirect(previousQuery);
            }  catch (ServiceException e) {
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
