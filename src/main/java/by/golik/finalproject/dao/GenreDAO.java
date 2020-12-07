package by.golik.finalproject.dao;

import by.golik.finalproject.entity.Genre;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface GenreDAO {
    List<Genre> getGenresForMovie();
    void addGenreForMovie(int movieId, String name);
    void deleteGenreForMovie(int movieId, String name);
}
