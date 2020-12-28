package by.golik.finalproject.command.impl.admin;

import by.golik.finalproject.command.Command;
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
public class UpdateMovie implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateMovie.class);

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addMoviePage.jsp";
    private static final String REDIRECT = "Controller?command=add-movie";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String RUNTIME = "runtime";
    private static final String BUDGET = "budget";
    private static final String GROSS = "gross";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot update movie";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Movie successfully updated";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter(ID);
        String title = request.getParameter(TITLE);
        String year = request.getParameter(YEAR);
        String runtime = request.getParameter(RUNTIME);
        String budget = request.getParameter(BUDGET);
        String gross = request.getParameter(GROSS);
        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);

        if (title == null && year == null && runtime == null && budget == null && gross == null) {

            try {
                administratorService.updateMovie(id, title, year, runtime, budget, gross);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                response.sendRedirect(REDIRECT);

            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).include(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(JSP_PAGE_PATH).include(request, response);
        }
    }
}
