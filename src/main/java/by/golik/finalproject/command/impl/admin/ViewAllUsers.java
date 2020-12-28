package by.golik.finalproject.command.impl.admin;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.AdministratorService;
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
public class ViewAllUsers implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/usersPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final Logger logger = LogManager.getLogger(ViewAllUsers.class);

    private static final String REQUEST_ATTRIBUTE = "all_users";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No users matching your query";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandHelper.saveCurrentQueryToSession(request);

        List<User> users;
        AdministratorService adminService = AdministratorHelper.getAdminService(request, response);

        try {
            users = adminService.getAllUsers();

            request.setAttribute(REQUEST_ATTRIBUTE, users);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
