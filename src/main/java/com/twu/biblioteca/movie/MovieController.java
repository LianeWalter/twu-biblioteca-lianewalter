package com.twu.biblioteca.movie;

import com.twu.biblioteca.book.Book;
import com.twu.biblioteca.book.NoSuchBookAvailableException;

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
        System.out.println("List of movies: ");
        this.movies.forEach(System.out::println);
    }


    public void checkOutMovie(String name) {

        Movie movieToCheckOut = movies.stream().filter(movie -> movie.getName().equals(name) && !movie.isCheckedOut()).findFirst().get();

        movieToCheckOut.setCheckedOut(true);

    }
}
