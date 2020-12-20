package by.golik.finalproject.service;

import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.service.exception.ServiceException;

/**
 * @author Nikita Golik
 */
public interface ParticipantService {
    Participant getParticipant(String id) throws ServiceException;
}
