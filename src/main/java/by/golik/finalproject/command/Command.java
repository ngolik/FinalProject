package by.golik.finalproject.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nikita Golik
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
