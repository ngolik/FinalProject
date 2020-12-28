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
public class AddParticipant implements Command {
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/addActorPage.jsp";
    private static final String REDIRECT = "Controller?command=add-participant";

    private static final Logger logger = LogManager.getLogger(AddParticipant.class);

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String SECOND_NAME = "secondName";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add actor";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Actor successfully added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandHelper.saveCurrentQueryToSession(request);

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String secondName = request.getParameter(SECOND_NAME);
        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);
        if (name == null && surname == null && secondName == null) {

            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

        } else {
            try {
                administratorService.addParticipant(name, surname, secondName);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                response.sendRedirect(REDIRECT);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
