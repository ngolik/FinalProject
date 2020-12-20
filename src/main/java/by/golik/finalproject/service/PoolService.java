package by.golik.finalproject.service;

import by.golik.finalproject.service.exception.ServiceException;

/**
 * @author Nikita Golik
 */
public interface PoolService {
    void init() throws ServiceException;
    void destroy() throws ServiceException;
}
