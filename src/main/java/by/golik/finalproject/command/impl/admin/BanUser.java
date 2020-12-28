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
public class BanUser implements Command {

    private static final Logger logger = LogManager.getLogger(BanUser.class);
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final String USERNAME = "userName";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Can't ban user";
    private static final String MESSAGE_OF_ERROR_2 = "You don't have permission do that";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter(USERNAME);
        String previousQuery = CommandHelper.getPreviousQuery(request);

        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);

        if(userName != null) {
            try {
                administratorService.banUser(userName);
                response.sendRedirect(previousQuery);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
