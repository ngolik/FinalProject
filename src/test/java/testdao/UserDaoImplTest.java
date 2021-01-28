package testdao;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.UserDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Nikita Golik
 */
public class UserDaoImplTest {
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
    public void registerTest() throws Exception {
        DaoFactory factory = null;
        UserDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            dao = factory.getUserDAO();

            String userName= "testUser";
            String userEmail= "testuser@mail.ru";
            String userPass= "testUserPass";
            dao.register(userName, userEmail, userPass);
            User user = dao.getUserByUsername(userName);

            dao.deleteUser(userName);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(userName, user.getUsername());
            Assert.assertEquals(userEmail, user.getEmail());


        } catch (ConnectionPoolException | DAOException e) {
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
