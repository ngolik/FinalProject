package by.golik.finalproject.controller;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.impl.admin.*;
import by.golik.finalproject.command.impl.guest.*;
import by.golik.finalproject.command.impl.user.AddRating;
import by.golik.finalproject.command.impl.user.Logout;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikita Golik
 */
public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();

    private Map<CommandList, Command> adminCommands = new HashMap<>();
    private Map<CommandList, Command> userCommands = new HashMap<>();
    private Map<CommandList, Command> guestCommands = new HashMap<>();

    private static final String GUEST = "guest";
    private static final String USER = "user";
    private static final String ADMIN = "admin";

    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {

        guestCommands.put(CommandList.LOGIN, new Login());
        guestCommands.put(CommandList.FIND_MOVIE_BY_TITLE, new ShowMovieByTitle());
        guestCommands.put(CommandList.SHOW_MOVIES_BY_GENRE, new ShowMoviesByGenre());
        guestCommands.put(CommandList.REGISTER, new Register());
        guestCommands.put(CommandList.ALL_MOVIES, new ShowAllMovies());
        guestCommands.put(CommandList.CHANGE_LANGUAGE, new ChangeLanguage());
        guestCommands.put(CommandList.VIEW_USER, new ViewUser());
        guestCommands.put(CommandList.MOVIE_BY_ID, new ShowMovieById());
        guestCommands.put(CommandList.ALL_PARTICIPANTS, new ShowAllParticipants());
        guestCommands.put(CommandList.PARTICIPANT_BY_ID, new ShowParticipantByID());

        adminCommands.put(CommandList.ADD_GENRE_FOR_MOVIE, new AddGenreForMovie());
        adminCommands.put(CommandList.ADD_MOVIE, new AddMovie());
        adminCommands.put(CommandList.ADD_PARTICIPANT, new AddParticipant());
        adminCommands.put(CommandList.ADD_PARTICIPANT_FOR_MOVIE, new AddParticipantForMovie());
        adminCommands.put(CommandList.DELETE_GENRE_FOR_MOVIE, new DeleteGenreForMovie());
        adminCommands.put(CommandList.UPDATE_MOVIE, new UpdateMovie());
        adminCommands.put(CommandList.UPDATE_PARTICIPANT, new UpdateParticipant());
        adminCommands.put(CommandList.VIEW_ALL_USERS, new ViewAllUsers());
        adminCommands.put(CommandList.ALL_MOVIES, new ShowAllMovies());
        adminCommands.put(CommandList.SHOW_MOVIES_BY_GENRE, new ShowMoviesByGenre());
        adminCommands.put(CommandList.LOG_OUT, new Logout());
        adminCommands.put(CommandList.CHANGE_LANGUAGE, new ChangeLanguage());
        adminCommands.put(CommandList.DELETE_USER, new DeleteUser());
        adminCommands.put(CommandList.VIEW_USER, new ViewUser());
        adminCommands.put(CommandList.FIND_MOVIE_BY_TITLE, new ShowMovieByTitle());
        adminCommands.put(CommandList.MOVIE_BY_ID, new ShowMovieById());
        adminCommands.put(CommandList.ALL_PARTICIPANTS, new ShowAllParticipants());
        adminCommands.put(CommandList.PARTICIPANT_BY_ID, new ShowParticipantByID());

        userCommands.put(CommandList.LOG_OUT, new Logout());
        userCommands.put(CommandList.ADD_RATING, new AddRating());
        userCommands.put(CommandList.CHANGE_LANGUAGE, new ChangeLanguage());
        userCommands.put(CommandList.ALL_MOVIES, new ShowAllMovies());
        userCommands.put(CommandList.FIND_MOVIE_BY_TITLE, new ShowMovieByTitle());
        userCommands.put(CommandList.MOVIE_BY_ID, new ShowMovieById());
        userCommands.put(CommandList.ALL_PARTICIPANTS, new ShowAllParticipants());
        userCommands.put(CommandList.PARTICIPANT_BY_ID, new ShowParticipantByID());



    }

    static CommandProvider getInstance() {
        return instance;
    }

    /**
     * This method receives String type of user and String command name,
     * looks for corresponding command for this user type if it's available
     * for him or gives default command for guest.
     * @param type of user
     * @param commandName
     * @return particular command
     */
    Command getCommandForUser(String type, String commandName) {
        String cmd = commandName.replace("-", "_").toUpperCase();
        CommandList name = null;
        Command command;
        try {
            name = CommandList.valueOf(cmd);
            switch (type) {
                case GUEST:
                    return guestCommands.get(name);
                case USER:
                    return userCommands.get(name);
                case ADMIN:
                    return adminCommands.get(name);
                default:
                    return guestCommands.get(name);
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, "No such command", e);
            command = guestCommands.get(CommandList.ALL_MOVIES);
        }
        return command;
    }
}
