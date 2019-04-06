package comf.example.user.movieexpert;

import android.support.annotation.NonNull;

import java.util.Date;

public class Movie implements Comparable<Movie>{
    int ID;
    double vote_average;
    String title;
    double popularity;
    String poster_path;
    String overview;
    Date release_date;

    public Movie(int ID, double vote_average, String title, double popularity, String poster_path, String overview, Date release_date) {
        this.ID = ID;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String backdrop_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String toString(){
        return title;
    }

    @Override
    public int compareTo(@NonNull Movie o){
        return this.title.compareTo(o.title);
    }
}
