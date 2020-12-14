package by.golik.finalproject.dao;

import by.golik.finalproject.dao.exception.DAOException;
import by.golik.finalproject.entity.Vote;
import java.util.List;

/**
 * @author Nikita Golik
 */
public interface VoteDAO {
    /**
     * This method is used to get Rating for a movie.
     *
     * @param id id of movie
     * @return list of filled rating beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Vote> getVotesForMovie(int id) throws DAOException;

    /**
     * This method is used to get rating user gave to any movies.
     *
     * @param nickname of user
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Vote> getVotesOfUser(String nickname) throws DAOException;

    /**
     * This method is used to check if there is an entry of this user for this movie.
     *
     * @param intMovieID   movie id
     * @param userNickname user nickname
     * @return filled rating bean
     * @throws DAOException if some error occurred while processing data.
     */
    Vote checkVotes(int intMovieID, String userNickname) throws DAOException;

    /**
     * This method is used to add rating of some user for some mvoie.
     *
     * @param intMovieID   id of movie
     * @param userNickname nickname of user
     * @param rating       user gave
     * @throws DAOException if some error occurred while processing data.
     */
    void addVotes(int intMovieID, String userNickname, int rating) throws DAOException;

    /**
     * This method is used to update rating some user gave to some movie in data source.
     *
     * @param intMovieID   id of movie
     * @param userNickname user nickname
     * @param intRating    rating user gave
     * @throws DAOException if some error occurred while processing data.
     */
    void updateVotes(int intMovieID, String userNickname, int intRating) throws DAOException;

    /**
     * This method is used to remove rating some user gave for some movie and used only for tests!!!
     *
     * @param movieID      id of movie
     * @param userNickname nickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteVotes(int movieID, String userNickname) throws DAOException;
}
