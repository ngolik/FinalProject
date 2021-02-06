package by.golik.finalproject.command.impl.guest;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.command.impl.admin.AdministratorHelper;
import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.AdministratorService;
import by.golik.finalproject.service.MovieService;
import by.golik.finalproject.service.ParticipantService;
import by.golik.finalproject.service.ServiceFactory;
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
 * This class is used to show all participants for client request.
 * @author Nikita Golik
 */
public class ShowAllParticipants implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAllParticipants.class);
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/common/participantsPage.jsp";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
    private static final String REQUEST_ATTRIBUTE = "all_participants";

    private static final String PAGE = "page";
    private static final String AMOUNT_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final int RECORDS_PER_PAGE = 10;

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "No participants matching your query";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandHelper.saveCurrentQueryToSession(request);

        List<Participant> participants;
        ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
        try {
            int page = 1;
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            }
            participants = participantService.getFullList((page-1)* RECORDS_PER_PAGE, RECORDS_PER_PAGE);

            int numberOfParticipants = participantService.countAllParticipantsAmount();
            int noOfPages = (int) Math.ceil(numberOfParticipants * 1.0 / RECORDS_PER_PAGE);

            request.setAttribute(AMOUNT_OF_PAGES, noOfPages);
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(REQUEST_ATTRIBUTE, participants);
            logger.info(participants);
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, MESSAGE_OF_ERROR);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
