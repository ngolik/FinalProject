package by.golik.finalproject.service;

import by.golik.finalproject.service.impl.*;

/**
 * @author Nikita Golik
 */
public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    private MovieService movieService = new MovieServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();
    private UserService userService = new UserServiceImpl();
    private PoolService poolService = new PoolServiceImpl();
    private ParticipantService participantService = new ParticipantServiceImpl();


    public MovieService getMovieService() {
        return movieService;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public UserService getUserService() {
        return userService;
    }

    public PoolService getPoolService() {
        return poolService;
    }

    public ParticipantService getActorService() {
        return participantService;
    }

}
