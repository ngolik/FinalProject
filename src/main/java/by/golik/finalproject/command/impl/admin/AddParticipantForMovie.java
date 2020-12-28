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
public class AddParticipantForMovie implements Command {

    private static final Logger logger = LogManager.getLogger(AddParticipantForMovie.class);

    private static final String ID = "id";
    private static final String PARTICIPANT_ID = "participant-id";

    private static final String ERROR = "errorParticipant";
    private static final String MESSAGE_OF_ERROR = "Cannot add participant for movie";
    private static final String MESSAGE_OF_ERROR_2 = "Cannot add participant for movie, wrong input";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String movieID = request.getParameter(ID);
        String actorID = request.getParameter(PARTICIPANT_ID);
        String previousQuery = CommandHelper.getPreviousQuery(request);

        AdministratorService adminService = AdministratorHelper.getAdminService(request, response);

        if (actorID != null && movieID !=null) {
            try {
                adminService.addParticipantForMovie(actorID, movieID);
                response.sendRedirect(previousQuery);
            }  catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(previousQuery).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
