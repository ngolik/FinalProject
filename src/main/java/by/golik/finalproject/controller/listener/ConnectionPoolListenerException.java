package by.golik.finalproject.controller.listener;

/**
 * ConnectionPoolListenerException is thrown when some error happens
 * during processing of the ConnectionPoolListener methods.
 * @author Nikita Golik
 */
public class ConnectionPoolListenerException extends Exception {

    public ConnectionPoolListenerException() {
    }

    public ConnectionPoolListenerException(String message, Throwable cause) {
        super(message, cause);
    }
}
