package by.golik.finalproject.command.impl.admin;

import by.golik.finalproject.entity.Role;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.AdministratorService;
import by.golik.finalproject.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Golik
 */
public class AdministratorHelper {
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    private static final String USER = "user";
    private static final String ADMIN = "admin";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot perform that action";
    private static final String MESSAGE_OF_ERROR_2 = "You don't have permission to do that";

    /**
     * This method is used to give Admin Service if user is admin
     * in other case return null
     * @param request
     * @param response
     * @return AdminService object
     * @throws ServletException
     * @throws IOException
     */
    static AdministratorService getAdminService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userRole;
        AdministratorService administratorService=null;
        Object object = request.getSession(false).getAttribute(USER);
        if (object.getClass().equals(User.class)){
            User user = (User) object;
            userRole = user.getRole();
            if (userRole.equals(ADMIN))
            {
                administratorService = ServiceFactory.getInstance().getAdministratorService();
            }
            else {
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
        return administratorService;
    }
}
