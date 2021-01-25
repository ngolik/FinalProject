package by.golik.finalproject.command;

import by.golik.finalproject.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Golik
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException;
}
