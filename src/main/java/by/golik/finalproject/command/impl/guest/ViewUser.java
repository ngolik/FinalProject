package by.golik.finalproject.command.impl.guest;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.ServiceFactory;
import by.golik.finalproject.service.UserService;
import by.golik.finalproject.service.exception.ServiceAuthorizationException;
import by.golik.finalproject.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is used to handle client request to show
 * profile page of a particular user.
 * @author Nikita Golik
 */
public class ViewUser implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/user/userPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final Logger logger = LogManager.getLogger(ViewUser.class);

    private static final String USERNAME = "username";

    private static final String REQUEST_ATTRIBUTE = "user";
    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No user with such nickname";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandHelper.saveCurrentQueryToSession(request);

        String username = request.getParameter(USERNAME);

        User user;
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            user = userService.getUserByUserName(username);
            request.setAttribute(REQUEST_ATTRIBUTE, user);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException | ServiceAuthorizationException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
