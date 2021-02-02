package by.golik.finalproject.command.impl.admin;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.CommandHelper;
import by.golik.finalproject.entity.Movie;
import by.golik.finalproject.service.AdministratorService;
import by.golik.finalproject.service.MovieService;
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
 * @author Nikita Golik
 */
public class AddGenreForMovie implements Command {

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/admin/addGenrePage.jsp";
    private static final Logger logger = LogManager.getLogger(AddGenreForMovie.class);
    private static final String MOVIE_ID = "movieID";
    private static final String GENRE_ID = "genreID";

    private static final String ERROR = "errorMessage";
    private static final String MESSAGE_OF_ERROR = "Cannot add genre for movie";
    private static final String REQUEST = "movies";
    private static final String SUCCESS = "successMessage";
    private static final String MESSAGE_OF_SUCCESS = "Genre successfully added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        CommandHelper.saveCurrentQueryToSession(request);

        List<Movie> movies;

        String movieID = request.getParameter(MOVIE_ID);
        String genreID = request.getParameter(GENRE_ID);

        AdministratorService administratorService = AdministratorHelper.getAdminService(request, response);

        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        movies = movieService.readAllMovies();
        request.setAttribute(REQUEST, movies);

        if (genreID == null && movieID == null) {
            request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
        } else {
            try {
                administratorService.addGenreForMovie(movieID, genreID);
                request.setAttribute(SUCCESS, MESSAGE_OF_SUCCESS);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }  catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                request.setAttribute(ERROR, MESSAGE_OF_ERROR);
                request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
            }
        }
    }
}
