package by.golik.finalproject.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author Nikita Golik
 */
public class Movie {
    private int id;
    private String title;
    private int year;
    private long budget;
    private long gross;
    private String image;
    private Actor producer;
    private List<Country> countryList;
    private List<Rating> ratingList;
    private List<Genre> genreList;
    private List<Actor> actorList;
    private double avgRating;

    public Movie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Actor getProducer() {
        return producer;
    }

    public void setProducer(Actor producer) {
        this.producer = producer;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        if (id != movie.id) return false;
        if (year != movie.year) return false;
        if (budget != movie.budget) return false;
        if (gross != movie.gross) return false;
        if (Double.compare(movie.avgRating, avgRating) != 0) return false;
        if (!Objects.equals(title, movie.title)) return false;
        if (!Objects.equals(image, movie.image)) return false;
        if (!Objects.equals(producer, movie.producer)) return false;
        if (!Objects.equals(countryList, movie.countryList)) return false;
        if (!Objects.equals(ratingList, movie.ratingList)) return false;
        if (!Objects.equals(genreList, movie.genreList)) return false;
        return Objects.equals(actorList, movie.actorList);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (int) (budget ^ (budget >>> 32));
        result = 31 * result + (int) (gross ^ (gross >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (countryList != null ? countryList.hashCode() : 0);
        result = 31 * result + (ratingList != null ? ratingList.hashCode() : 0);
        result = 31 * result + (genreList != null ? genreList.hashCode() : 0);
        result = 31 * result + (actorList != null ? actorList.hashCode() : 0);
        temp = Double.doubleToLongBits(avgRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
