package testdao;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.ParticipantDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Participant;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class ParticipantDaoImplTest {
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
    public void addParticipantTest() {
        DaoFactory factory = null;
        ParticipantDAO dao = null;
        try {
            factory = DaoFactory.getInstance();
            dao = factory.getParticipantDAO();

            String name = "Test3";
            String surName = "TestSur3";
            String secondName = "TestSecond3";
            String position = "TestPos3";

            dao.createParticipant(name, surName, secondName, position);
            Participant participant = dao.getLastInsertedParticipant();
            int id = participant.getId();
            dao.deleteParticipant(id);

            Assert.assertEquals(name, participant.getName());
            Assert.assertEquals(surName, participant.getSurname());
            Assert.assertEquals(secondName, participant.getSecondName());
            Assert.assertEquals(position, participant.getPosition());

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateParticipantTest(){
        DaoFactory factory = null;
        ParticipantDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            dao = factory.getParticipantDAO();

            List<Participant> participants = dao.getAllParticipants();
            Participant participant = null;
            if(participants.size()>0) {
                participant = participants.get(0);
            }
            int id=0;

            assert participant != null;
            id = participant.getId();

            String name = "Test";
            String surName = "TestSur";
            String secondName = "TestSecond";
            String position = "TestPos";

            String nameTemp= participant.getName();
            String surNameTemp= participant.getSurname();
            String secondNameTemp= participant.getSecondName();
            String positionTemp= participant.getPosition();

            dao.updateParticipant(id, name, surName, secondName, position);
            participant = dao.readParticipant(id);
            Assert.assertEquals(name, participant.getName());
            Assert.assertEquals(surName, participant.getSurname());
            Assert.assertEquals(secondName, participant.getSecondName());
            Assert.assertEquals(position, participant.getPosition());

            dao.updateParticipant(id, nameTemp, surNameTemp, secondNameTemp, positionTemp);

        } catch (DAOException | SQLException e) {
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
