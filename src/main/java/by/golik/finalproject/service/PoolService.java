package by.golik.finalproject.service;

import by.golik.finalproject.service.exception.ServiceException;

/**
 * PoolService is and interface used to initialize and destroy connection pool
 * with database or some other data source.
 * @author Nikita Golik
 */
public interface PoolService {

    /**
     * This method is used to initialize connection pool.
     * @throws ServiceException if any error occurred while processing method.
     */
    void init() throws ServiceException;

    /**
     * This method is used to destroy connection pool.
     * @throws ServiceException if any error occurred while processing method.
     */
    void destroy() throws ServiceException;
}
