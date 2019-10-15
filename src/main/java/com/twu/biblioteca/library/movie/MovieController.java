package com.twu.biblioteca.library.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieController {

    private List<Movie> movies;

    public MovieController() {
        this.movies = new ArrayList<>();

        this.movies.add(new Movie("Frozen", "2013","Chris Buck, Jennifer Lee", "unrated"));
        this.movies.add(new Movie("Tangled", "2010", "Nathan Greno, Byron Howard", "7" ));
        this.movies.add(new Movie("Moana", "2016","Ron Clements, John Musker", "unrated"));
    }

    public void showAvailableMovieList(){
        System.out.println("List of movies: ");

        List<Movie> availableMovies = movies.stream().filter(movie -> !movie.isCheckedOut()).collect(Collectors.toList());

        availableMovies.forEach(System.out::println);
    }


    public void checkOutMovie(String name) {

        Movie movieToCheckOut = movies.stream().filter(movie -> movie.getTitle().equals(name) && !movie.isCheckedOut()).findFirst().get();

        movieToCheckOut.setCheckedOut(true);

    }
}
