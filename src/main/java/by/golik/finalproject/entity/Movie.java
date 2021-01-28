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
    private int ratingVotes;

    public int getRatingVotes() {
        return ratingVotes;
    }

    public void setRatingVotes(int ratingVotes) {
        this.ratingVotes = ratingVotes;
    }

    List<Vote> ratings;
    List<Participant> participants;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Vote> getRatings() {
        return ratings;
    }

    public void setRatings(List<Vote> ratings) {
        this.ratings = ratings;
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
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        if (id != movie.id) return false;
        if (year != movie.year) return false;
        if (runtime != movie.runtime) return false;
        if (!Objects.equals(imageUrl, movie.imageUrl)) return false;
        if (budget != movie.budget) return false;
        if (gross != movie.gross) return false;
        if (Double.compare(movie.rating, rating) != 0) return false;
        return  (!Objects.equals(title, movie.title));
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + runtime;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (int) (budget ^ (budget >>> 32));
        result = 31 * result + (int) (gross ^ (gross >>> 32));
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", imageUrl='" + imageUrl + '\'' +
                ", budget=" + budget +
                ", gross=" + gross +
                ", rating=" + rating +
                ", avgRating=" + avgRating +
                '}';
    }
}
