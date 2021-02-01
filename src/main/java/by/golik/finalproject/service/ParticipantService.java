package by.golik.finalproject.service;

import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.exception.ServiceException;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface ParticipantService {
    Participant getParticipant(String id) throws ServiceException;
    List<Participant> getFullList(int offset, int recordsPerPage) throws ServiceException;
    int countAllParticipantsAmount() throws ServiceException;

}
