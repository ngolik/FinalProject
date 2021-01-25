package testdao;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.UserDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Nikita Golik
 */
public class UserDaoImplTest {
    @Test
    public void registerTest() throws Exception {
        DaoFactory factory = null;
        ConnectionPool pool = null;
        UserDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            pool = factory.getConnectionPool();
            dao = factory.getUserDAO();

            pool.init();
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
