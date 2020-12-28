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
    public void registerTest(){
        DaoFactory factory = null;
        ConnectionPool poolDAO = null;
        UserDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            poolDAO = factory.getConnectionPool();
            dao = factory.getUserDAO();

            poolDAO.init();
            String userName= "testUser";
            String userEmail= "testuser@mail.ru";
            String userPass= "testUserPass";


            dao.register(userName, userEmail, userPass);
            User user = dao.getUserByUsername(userName);


            dao.deleteUser(userName);

            Assert.assertNotEquals(user, null);
            Assert.assertEquals(userName, user.getUserName());
            Assert.assertEquals(userEmail, user.getEmail());


        } catch (ConnectionPoolException | DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert poolDAO != null;
                poolDAO.destroy();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }
}
