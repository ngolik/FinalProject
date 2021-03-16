package by.golik.finalproject.service.impl;

import by.golik.finalproject.dao.DaoFactory;
import by.golik.finalproject.dao.exception.ConnectionPoolException;
import by.golik.finalproject.dao.pool.ConnectionPool;
import by.golik.finalproject.service.PoolService;
import by.golik.finalproject.service.exception.ServiceException;

import java.io.IOException;

/**
 * This class is an implementation of PoolService interface.
 * @author Nikita Golik
 */
public class PoolServiceImpl implements PoolService {

    /**
     * This method is used to initialize connection pool.
     *
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void init() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ConnectionPool pool = daoFactory.getConnectionPool();
            pool.init();
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Cannot init the pool");
        }
    }

    /**
     * This method is used to destroy connection pool.
     *
     * @throws ServiceException if any error occurred while processing method.
     */
    @Override
    public void destroy() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ConnectionPool pool = daoFactory.getConnectionPool();
            pool.destroy();
        } catch (ConnectionPoolException e) {
            throw new ServiceException("Cannot destroy the pool", e);
        }
    }
}
