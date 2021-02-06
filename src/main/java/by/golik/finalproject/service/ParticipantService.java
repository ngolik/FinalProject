package by.golik.finalproject.service;

import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.exception.ServiceException;

import java.util.List;

/**
 * This interface is used to interact with Actor beans mainly.
 * @author Nikita Golik
 */
public interface ParticipantService {

    /**
     * This method shows any participant by it's id.
     * @param participantId - id of participant
     * @return Participant bean object
     * @throws ServiceException if any error occurred while processing method.
     */
    Participant getParticipant(String participantId) throws ServiceException;

    /**
     * This method shows list of all participants.
     * @param offset - page
     * @param recordsPerPage - maximum count of records on page
     * @return list of participants
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Participant> getFullList(int offset, int recordsPerPage) throws ServiceException;

    /**
     * This method is used to get count of all participants
     * @return count of participants
     * @throws ServiceException if any error occurred while processing method.
     */
    int countAllParticipantsAmount() throws ServiceException;

}
