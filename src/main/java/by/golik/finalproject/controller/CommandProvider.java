package by.golik.finalproject.controller;

import by.golik.finalproject.command.Command;
import by.golik.finalproject.command.impl.admin.AddParticipant;
import by.golik.finalproject.command.impl.guest.Login;
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
        adminCommands.put(CommandList.ADD_PARTICIPANT, new AddParticipant());


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
