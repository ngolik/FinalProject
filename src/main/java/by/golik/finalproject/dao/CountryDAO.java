package by.golik.finalproject.dao;

import by.golik.finalproject.entity.Country;

import java.util.List;

/**
 * @author Nikita Golik
 */
public interface CountryDAO {
    List<Country> getCountriesForMovie(int countryId);
    void addCountryForMovie(int movieId, String name);
    void deleteCountryForMovie(int movieId, String name);
}
