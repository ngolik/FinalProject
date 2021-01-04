package by.golik.finalproject.command.impl.guest;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.ServiceFactory;
import by.golik.finalproject.service.UserService;
import by.golik.finalproject.service.exception.ServiceAuthorizationException;
import by.golik.finalproject.service.exception.ServiceBanException;
import by.golik.finalproject.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Nikita Golik
 */
public class Login implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/login.jsp";

    private static final String USER = "user";

    private static final Logger logger = LogManager.getLogger(Login.class);

    private static final String LOGIN = "username";
    private static final String PASSWORD = "pass";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR_1 = "Wrong login or pass";
    private static final String MESSAGE_OF_ERROR_2 = "Sorry access for you is temporary unavailable";
    private static final String MESSAGE_OF_ERROR_3 = "Sorry something went wrong";
    private static final String MESSAGE_OF_ERROR_4 = "All fields should be filled";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter(LOGIN);
        byte[] password = request.getParameter(PASSWORD).getBytes();

        String previousQuery = CommandHelper.getPreviousQuery(request);

        UserService userService = ServiceFactory.getInstance().getUserService();
        HttpSession session = request.getSession(true);

        if (login != null && password.length>5) {
            try {
                User user = userService.authorise(login, password);
                Arrays.fill(password, (byte) 0);
                session.setAttribute(USER, user);

                response.sendRedirect(previousQuery);
            } catch (ServiceAuthorizationException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                logger.log(Level.INFO, " authorization error");
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceBanException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_3);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_4);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        }
    }
}
