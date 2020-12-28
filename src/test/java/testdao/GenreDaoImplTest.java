package testdao;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.GenreDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Genre;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Nikita Golik
 */
public class GenreDaoImplTest {
    @Test
    public void deleteGenreForMovieTest() {
        DaoFactory factory = null;
        ConnectionPool pool = null;
        GenreDAO dao = null;
        try {
            factory = DaoFactory.getInstance();
            pool = factory.getConnectionPool();
            dao = factory.getGenreDAO();

            pool.init();
            String name = "Test";

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            int sizeBefore = dao.readGenresByMovie(movieId).size();
            dao.createGenreForMovie(movieId, name);
            int sizeAfter = dao.readGenresByMovie(movieId).size();
            List<Genre> genres = dao.readGenresByMovie(movieId);
            Genre genre = null;
            for (Genre entry : genres) {
                genre = entry.getName().equals(name) ? entry : null;
            }
            dao.deleteGenreForMovie(movieId, name);
            int sizeFinal = dao.readGenresByMovie(movieId).size();

            assert genre != null;
            Assert.assertNotEquals(sizeBefore, sizeAfter);
            Assert.assertEquals(sizeBefore, sizeFinal);

        } catch (ConnectionPoolException | DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addGenreTest() {
        DaoFactory factory = null;
        ConnectionPool pool = null;
        GenreDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            pool = factory.getConnectionPool();
            dao = factory.getGenreDAO();

            pool.init();
            String name= "Test";

            int movieId = factory.getMovieDAO().getLastInsertedMovie().getId();
            dao.createGenreForMovie(movieId, name);
            List<Genre> countries = dao.readGenresByMovie(movieId);
            Genre genre = null;
            for (Genre entry : countries) {
                genre = entry.getName().equals(name) ? entry : null;
            }

            dao.deleteGenreForMovie(movieId, name);

            assert genre != null;
            Assert.assertEquals(name, genre.getName());

        } catch (ConnectionPoolException | DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert pool != null;
                pool.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }
}
