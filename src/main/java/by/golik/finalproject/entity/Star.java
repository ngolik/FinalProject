package by.golik.finalproject.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author Nikita Golik
 */
public class Star {
    private int id;
    //TODO ru en
    private String name;
    private String image;
    private List<Movie> movieList;
    public Star() {
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
        Star star = (Star) o;

        if(id != star.id) return false;
        if (!Objects.equals(name, star.name)) return false;
        if (!Objects.equals(movieList, star.movieList)) return false;
        return Objects.equals(image, star.image);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (movieList != null ? movieList.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
