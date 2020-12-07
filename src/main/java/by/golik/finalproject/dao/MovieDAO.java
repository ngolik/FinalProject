package by.golik.finalproject.dao;

import by.golik.finalproject.entity.Movie;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface MovieDAO {
    List<Movie> getAllMovies();
    List<Movie> getMoviesByCountry(String country, int offset, int recordsNumber);
    List<Movie> getMoviesByGenre(String genre, int offset, int recordsNumber);
    List<Movie> searchMovieByTitle(String title);
    List<Movie> getMoviesByActor(String actorId);
    Movie getMovieById(int id);
    void addMovie(String title, int year, long budget, long gross);
    void updateMovie(String title, int year, long budget, long gross);
    void deleteMovie(int id);
    void updateImage(int id, String path);


}
