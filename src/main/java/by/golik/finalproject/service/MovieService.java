package by.golik.finalproject.service;

import by.golik.finalproject.entity.Movie;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface MovieService {
    List<Movie> getFullList(int offset, int recordsPerPage);
    List<Movie> getMoviesByGenre(int offset, int recordsPerPage, String genre);
    Movie getMovieByID(int offset, int recordsPerPage, String id, String lang);
    List<Movie> findMovieByTitle(String title);
    int countAllMoviesAmount();
    int countMoviesByGenre(String genre);

}
