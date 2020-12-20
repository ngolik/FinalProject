package by.golik.finalproject.entity;

import java.util.List;
import java.util.Objects;

/**
 * entity represents participant
 * @author Nikita Golik
 */
public class Participant {
    /**
     * unique identifier
     */
    private int id;
    /**
     * participant name
     */
    private String name;
    /**
     * participant surname
     */
    private String surname;
    /**
     * participant second name
     */
    private String secondName;
    /**
     * list of movies, where participant takes part
     */
    private List<Movie> movies;

    public Participant() {

    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                secondName.equals(that.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, secondName);
    }

    @Override
    public String toString() {
        return "Participants{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
