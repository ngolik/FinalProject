package by.golik.finalproject.service;

import by.golik.finalproject.entity.Participant;
import by.golik.finalproject.entity.User;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface AdministratorService {
    void banUser(String userNickname);
    void unbanUser(String userNickname);
    List<User> getAllUsers();
    void addMovie(String title, int year, int runtime, int budget, int gross);
    void updateMovie(String title, int year, int runtime, int budget, int gross);
    void addGenreForMovie(int movieID, String name);
    void deleteGenreForMovie(int movieID, String name);
    void addParticipant(String name, String surname, String secondName);
    void updateParticipant(int ID, String name, String surname, String secondName);
    void addParticipantForMovie(String participantID, String movieID);
    void deleteParticipantForMovie(String participantID, String movieID);
    List<Participant> showAllParticipants();
    void updateImage(String entity, String filename, String path);

}
