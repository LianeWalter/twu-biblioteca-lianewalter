package com.twu.biblioteca.movie;

import java.util.ArrayList;
import java.util.List;

public class MovieController {

    private List<Movie> movies;

    public MovieController() {
        this.movies = new ArrayList<>();

        this.movies.add(new Movie("Frozen", "2013","Chris Buck, Jennifer Lee", "unrated"));
        this.movies.add(new Movie("Tangled", "2010", "Nathan Greno, Byron Howard", "7" ));
        this.movies.add(new Movie("Moana", "2016","Ron Clements, John Musker", "unrated"));
    }

    public void showMovieList(){

        this.movies.forEach(System.out::println);
    }


}
