package com.twu.biblioteca.movie;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class MovieControllerTest {
    private ByteArrayOutputStream outContent;
    private MovieController movieController;





    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        movieController = new MovieController();
    }


    @Test
    public void shouldShowMovieList(){
        movieController.showMovieList();
        assertThat(outContent.toString(), containsString("Frozen"));
    }

}