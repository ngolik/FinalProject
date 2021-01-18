package testdao;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.ParticipantDAO;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.entity.Participant;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Nikita Golik
 */
public class ParticipantDaoImplTest {
    @Test
    public void addParticipantTest() {
        DaoFactory factory = null;
        ConnectionPool pool = null;
        ParticipantDAO dao = null;
        try {
            factory = DaoFactory.getInstance();
            pool = factory.getConnectionPool();
            dao = factory.getParticipantDAO();

            pool.init();
            String name = "Test";
            String surName = "TestSur";
            String secondName = "TestSecond";
            String position = "TestPosition";

            dao.createParticipant(name, surName, secondName, position);
            Participant participant = dao.getAllParticipants().get(15);

            Assert.assertEquals(name, participant.getName());
            Assert.assertEquals(surName, participant.getSurname());
            Assert.assertEquals(secondName, participant.getSecondName());
            Assert.assertEquals(position, participant.getPosition());

        } catch (ConnectionPoolException | DAOException | SQLException e) {
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
    public void updateActorTest(){
        DaoFactory factory = null;
        ConnectionPool pool = null;
        ParticipantDAO dao = null;
        try{
            factory = DaoFactory.getInstance();
            pool = factory.getConnectionPool();
            dao = factory.getParticipantDAO();

            pool.init();

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
            String position = "TestPosition";

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

        } catch (ConnectionPoolException | DAOException | SQLException e) {
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
