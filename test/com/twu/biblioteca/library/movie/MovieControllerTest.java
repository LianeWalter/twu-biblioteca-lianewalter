package com.twu.biblioteca.library.movie;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class MovieControllerTest {
    private ByteArrayOutputStream outContent;

    private List<Movie> mockMovies;



    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        mockMovies = new ArrayList<>();

        mockMovies.add(new Movie("Frozen", "2013","Chris Buck, Jennifer Lee", "unrated"));
        mockMovies.add(new Movie("Tangled", "2010", "Nathan Greno, Byron Howard", "7" ));

    }


    @Test
    public void shouldShowMovieList(){
        MovieController movieController = new MovieController();

        movieController.showAvailableMovieList();
        assertThat(outContent.toString(), containsString("Frozen"));
    }


    @Test
    public void shouldCheckOutMovieCorrectly(){
       MovieController movieController = new MovieController();
       movieController.checkOutMovie(mockMovies.get(0).getTitle());

       movieController.showAvailableMovieList();
       assertThat(outContent.toString(),not((containsString(mockMovies.get(0).getTitle()))));
    }



}