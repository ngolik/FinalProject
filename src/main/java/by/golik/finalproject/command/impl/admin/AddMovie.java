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
public class AddMovie implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/admin/addMovie.jsp";

    private static final Logger logger = LogManager.getLogger(AddMovie.class);

    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String RUNTIME = "runtime";
    private static final String BUDGET = "budget";
    private static final String GROSS = "gross";


    private static final String ERROR = "errorMessage";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add movie";
    private static final String MESSAGE_OF_SUCCESS = "Movie successfully added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandHelper.saveCurrentQueryToSession(request);

        String title = request.getParameter(TITLE);
        String year = request.getParameter(YEAR);
        String runtime = request.getParameter(RUNTIME);
        String budget = request.getParameter(BUDGET);
        String gross = request.getParameter(GROSS);
        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);

        if (title == null && year == null && runtime == null && budget == null && gross == null) {

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

        } else {
            try {
                administratorService.addMovie(title, year, runtime, budget, gross);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
