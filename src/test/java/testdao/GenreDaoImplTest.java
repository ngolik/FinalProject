package testdao;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.GenreDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Genre;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Nikita Golik
 */
public class GenreDaoImplTest {

    @BeforeMethod
    public void takeConnection() {
        DaoFactory factory = null;
        ConnectionPool pool = null;
        try {
            factory = DaoFactory.getInstance();
            pool = factory.getConnectionPool();
            pool.init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteGenreForMovieTest() {
        DaoFactory factory = null;
        GenreDAO dao = null;
        try {
            factory = DaoFactory.getInstance();
            dao = factory.getGenreDAO();
            int genreID = 3;

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            int sizeBefore = dao.readGenresByMovie(movieId).size();
            dao.createGenreForMovie(movieId, genreID);
            int sizeAfter = dao.readGenresByMovie(movieId).size();
            List<Genre> genres = dao.readGenresByMovie(movieId);
            Genre genre = null;
            for (Genre entry : genres) {
                genre = entry.getId() == genreID ? entry : null;
            }
            dao.deleteGenreForMovie(movieId, genreID);
            int sizeFinal = dao.readGenresByMovie(movieId).size();

            assert genre != null;
            Assert.assertNotEquals(sizeBefore, sizeAfter);
            Assert.assertEquals(sizeBefore, sizeFinal);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addGenreTest() {
        DaoFactory factory = null;
        GenreDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            dao = factory.getGenreDAO();
            int genreID = 3;
            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            dao.createGenreForMovie(movieId, genreID);
            List<Genre> genres = dao.readGenresByMovie(movieId);
            Genre genre = null;
            for (Genre entry : genres) {
                genre = entry.getId() == genreID ? entry : null;
            }
            dao.deleteGenreForMovie(movieId, genreID);
            assert genre != null;
            Assert.assertEquals(genreID, genre.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void destroyConnection() {
        DaoFactory factory = null;
        ConnectionPool pool = null;
        try {
            factory = DaoFactory.getInstance();
            pool = factory.getConnectionPool();
            pool.destroy();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
