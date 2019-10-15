package com.twu.biblioteca.movie;

import com.twu.biblioteca.LibraryItem;

public class Movie extends LibraryItem {

    private String year;
    private String director;
    private String rating;


    public Movie(String title, String year, String director, String rating) {
        super(title);
        this.year = year;
        this.director = director;
        this.rating = rating;

    }



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Title:" + getTitle() +
                ", Year: " + year +
                ", Director(s): " + director +
                ", Rating: " + rating;
    }
}
