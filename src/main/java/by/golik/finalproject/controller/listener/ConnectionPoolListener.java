package by.golik.finalproject.controller.listener;

import by.golik.finalproject.service.PoolService;
import by.golik.finalproject.service.ServiceFactory;
import by.golik.finalproject.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ConnectionPoolListener is listener for ServletContext initialization
 * and destroying. Used to initialize and destroy DataBase connection pool.
 * @author Nikita Golik
 */
public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolListener.class);

    /**
     * Public constructor is required by servlet spec
     */
    public ConnectionPoolListener() {
    }

    /**
    * ServletContextListener implementation
     * This method is called when the servlet context is
     * initialized(when the Web application is deployed).
     */

    public void contextInitialized(ServletContextEvent sce) {

        try {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PoolService poolService = serviceFactory.getPoolService();
        poolService.init();
        logger.log(Level.INFO, "Pool successfully initialized");

         } catch (ServiceException e) {
            try {
                throw new ConnectionPoolListenerException("Cannot init the pool", e);
            } catch (ConnectionPoolListenerException connectionPoolListenerException) {
                connectionPoolListenerException.printStackTrace();
            }
        }
    }

    /**
     * This method is invoked when the Servlet Context
     * (the Web application) is undeployed or
     * Application Server shuts down.
     */
    public void contextDestroyed(ServletContextEvent sce) {

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PoolService poolService = serviceFactory.getPoolService();
            poolService.destroy();
            logger.log(Level.INFO, "Pool successfully destroyed");
        } catch (ServiceException e) {
            try {
                throw new ConnectionPoolListenerException("Cannot destroy the pool", e);
            } catch (ConnectionPoolListenerException connectionPoolListenerException) {
                connectionPoolListenerException.printStackTrace();
            }
        }
    }
}
