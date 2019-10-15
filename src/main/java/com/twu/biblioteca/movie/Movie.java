package com.twu.biblioteca.movie;

public class Movie {
    /*
    Movies have a name, year, director and movie rating (from 1-10 or unrated).
     */
    private String name;
    private String year;
    private String director;
    private String rating;

    private boolean isCheckedOut;


    public Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;

        this.isCheckedOut = false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    @Override
    public String toString() {
        return "Name:" + name +
                ", Year: " + year +
                ", Director(s): " + director +
                ", Rating: " + rating;
    }
}
