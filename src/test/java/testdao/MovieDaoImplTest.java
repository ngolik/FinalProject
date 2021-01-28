package testdao;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.MovieDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Movie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Nikita Golik
 */
public class MovieDaoImplTest {
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
    public void addMovieTest(){
        DaoFactory factory = null;
        MovieDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            dao = factory.getMovieDAO();
            String title= "Test2";
            int year= 1999;
            int runtime = 200;
            long budget = 1000_000;
            long gross = 10_000_000;

            dao.createMovie(title, year, runtime, budget, gross);
            Movie movie = dao.getLastInsertedMovie();
            int id = movie.getId();

            dao.deleteMovie(id);

            Assert.assertEquals(title, movie.getTitle());
            Assert.assertEquals(year, movie.getYear());
            Assert.assertEquals(runtime, movie.getRuntime());
            Assert.assertEquals(budget, movie.getBudget());
            Assert.assertEquals(gross, movie.getGross());

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateMovieTest(){
        DaoFactory factory = null;
        MovieDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            dao = factory.getMovieDAO();
            List<Movie> movieList = dao.readAllMovies();
            Movie movie = null;
            if(movieList.size()>0) {
                movie = movieList.get(0);
            }
            int id=0;

            assert movie != null;
            id = movie.getId();

            String title= "Test";
            int year= 1999;
            int runtime = 20522;
            long budget = 1000_000;
            long gross = 10_000_000;

            String titleTemp= movie.getTitle();
            int yearTemp= movie.getYear();
            int runtimeTemp= movie.getRuntime();
            long budgetTemp = movie.getBudget();
            long grossTemp = movie.getGross();

            dao.updateMovie(id, title, year, runtime, budget, gross);
            movie = dao.getMovieById(id);
            Assert.assertEquals(title, movie.getTitle());
            Assert.assertEquals(year, movie.getYear());
            Assert.assertEquals(runtime, movie.getRuntime());
            Assert.assertEquals(budget, movie.getBudget());
            Assert.assertEquals(gross, movie.getGross());

            dao.updateMovie(id, titleTemp, yearTemp, runtimeTemp, budgetTemp, grossTemp);

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
