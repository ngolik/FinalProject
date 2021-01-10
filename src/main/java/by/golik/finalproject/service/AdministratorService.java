package by.golik.finalproject.service;

import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.entity.User;
import by.golik.finalproject.service.exception.ServiceException;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface AdministratorService {
    void banUser(String userNickname) throws ServiceException;
    void unbanUser(String userNickname) throws ServiceException;
    List<User> readAllUsers() throws ServiceException;
    void addMovie(String title, String year, String runtime, String budget, String gross) throws ServiceException;
    void updateMovie(String id, String title, String year, String runtime, String budget, String gross) throws ServiceException;
    void addGenreForMovie(String movieID, String name) throws ServiceException;
    void deleteGenreForMovie(String movieID, String name) throws ServiceException;
    void addParticipant(String name, String surname, String secondName) throws ServiceException;
    void updateParticipant(String ID, String name, String surname, String secondName) throws ServiceException;
    void addParticipantForMovie(String participantID, String movieID) throws ServiceException;
    void deleteParticipantForMovie(String participantID, String movieID);
    List<Participant> readAllParticipants() throws ServiceException;
    void updateImage(String entity, String filename, String path) throws ServiceException;

}
