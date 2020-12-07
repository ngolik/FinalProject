package by.golik.finalproject.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author Nikita Golik
 */
public class Actor {
    private int id;
    //TODO ru en
    private String name;
    private String surname;
    private String image;
    private List<Movie> movieList;
    public Actor() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;

        if(id != actor.id) return false;
        if (!Objects.equals(name, actor.name)) return false;
        if (!Objects.equals(surname, actor.surname)) return false;
        if (!Objects.equals(movieList, actor.movieList)) return false;
        return Objects.equals(image, actor.image);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (movieList != null ? movieList.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
