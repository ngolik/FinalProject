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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class is used to handle register request from client.
 * @author Nikita Golik
 */
public class Register implements Command {

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/guest/register.jsp";

    private static final Logger logger = LogManager.getLogger(Register.class);

    private static final String USER = "user";

    private static final String LOGIN = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final String PASSWORD_2 = "pass2";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR_1 = "All fields should be filled";
    private static final String MESSAGE_OF_ERROR_2 = "User with such email or login is already exist";
    private static final String MESSAGE_OF_ERROR_3 = "Login and password should be at least 6 characters";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        byte[] password = request.getParameter(PASSWORD).getBytes();
        byte[] password2 = request.getParameter(PASSWORD_2).getBytes();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String previousQuery = CommandHelper.getPreviousQuery(request);
        HttpSession session = request.getSession(true);

        if (login != null && email != null && password.length>0 && password2.length>0) {
            try {
                User user = userService.register(login, password, password2, email);
                Arrays.fill(password, (byte) 0);
                Arrays.fill(password2, (byte) 0);

                session.setAttribute(USER, user);

                response.sendRedirect(previousQuery);
            } catch (ServiceAuthorizationException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_1);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_3);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        }
    }
}
