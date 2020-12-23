package by.golik.finalproject.entity;

import java.util.List;
import java.util.Objects;

/**
 * Entity represents movie.
 * @author Nikita Golik
 */
public class Movie {

    /**
     * unique identifier
     */
    private int id;
    /**
     * movie title
     */
    private String title;
    /**
     * year 4 digits
     */
    private int year;
    /**
     * path to movie icon
     */
    private String imageUrl;
    /**
     * movie runtime
     */
    private int runtime;
    /**
     * movie budget
     */
    private long budget;
    /**
     * movie gross
     */
    private long gross;
    /**
     * movie rating
     */
    private float rating;
    /**
     * movie average rating
     */
    private double avgRating;
    /**
     * list of marks from users
     */
    List<Vote> votes;
    private Participant actor;
    private Participant director;
    private Participant producer;
    List<Participant> participants;
    private String description;
    private List<Genre> genreList;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Participant getActor() {
        return actor;
    }

    public void setActor(Participant actor) {
        this.actor = actor;
    }

    public Participant getDirector() {
        return director;
    }

    public void setDirector(Participant director) {
        this.director = director;
    }

    public Participant getProducer() {
        return producer;
    }

    public void setProducer(Participant producer) {
        this.producer = producer;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        if (id != movie.id) return false;
        if (year != movie.year) return false;
        if (!Objects.equals(imageUrl, movie.imageUrl)) return false;
        if (budget != movie.budget) return false;
        if (gross != movie.gross) return false;
        if (Double.compare(movie.rating, rating) != 0) return false;
        if (!Objects.equals(title, movie.title)) return false;
        return  (!Objects.equals(genreList, movie.genreList));

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (int) (budget ^ (budget >>> 32));
        result = 31 * result + (int) (gross ^ (gross >>> 32));
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (genreList != null ? genreList.hashCode() : 0);

        return result;
    }
}
