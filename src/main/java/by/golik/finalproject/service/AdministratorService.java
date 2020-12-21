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
    List<User> getAllUsers() throws ServiceException;
    void addMovie(String title, String year, String runtime, String budget, String gross) throws ServiceException;
    void updateMovie(String id, String title, String year, String runtime, String budget, String gross) throws ServiceException;
    void addGenreForMovie(int movieID, String name);
    void deleteGenreForMovie(int movieID, String name);
    void addParticipant(String name, String surname, String secondName);
    void updateParticipant(int ID, String name, String surname, String secondName);
    void addParticipantForMovie(String participantID, String movieID);
    void deleteParticipantForMovie(String participantID, String movieID);
    List<Participant> showAllParticipants();
    void updateImage(String entity, String filename, String path);

}
