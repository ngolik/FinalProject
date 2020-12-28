package by.golik.finalproject.command.impl.admin;


import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.service.AdministratorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Golik
 */
public class DeleteParticipantForMovie implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteParticipantForMovie.class);

    private static final String ID = "id";
    private static final String PARTICIPANT_ID = "participant-id";
    private static final String ERROR = "errorParticipant";
    private static final String MESSAGE_OF_ERROR = "Cannot delete participant for movie";
    private static final String MESSAGE_OF_ERROR_2 = "Cannot delete participant for movie, wrong input";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String movieID = request.getParameter(ID);
        String participantID = request.getParameter(PARTICIPANT_ID);
        String previousQuery = CommandHelper.getPreviousQuery(request);

        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);

        if(participantID != null && movieID != null ) {
            administratorService.deleteParticipantForMovie(participantID, movieID);
            response.sendRedirect(previousQuery);
        } else {
            request.setAttribute(ERROR, MESSAGE_OF_ERROR_2);
            request.getRequestDispatcher(previousQuery).forward(request, response);
        }
    }
}
