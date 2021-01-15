package by.golik.finalproject.command.impl.admin;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.dao.exception.DAOException;
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
public class DeleteUser implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/admin/deleteUserPage.jsp";
    private static final String REDIRECT = "DispatcherServlet?command=delete-user";

    private static final Logger logger = LogManager.getLogger(DeleteUser.class);

    private static final String USERNAME = "username";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot delete user";
    private static final String SUCCESS = "delete_user";
    private static final String MESSAGE_OF_SUCCESS = "USer successfully deleted";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandHelper.saveCurrentQueryToSession(request);

        String username = request.getParameter(USERNAME);
        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);
        if (username == null) {
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

        } else {
            try {
                administratorService.deleteUser(username);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                response.sendRedirect(REDIRECT);
            } catch (ServiceException | DAOException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
