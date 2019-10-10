package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class BibliotecaAppTest {

    private BibliotecaApp bibliotecaApp;
    private ByteArrayOutputStream outContent;


    @Before
    public void init(){
        bibliotecaApp = new BibliotecaApp();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void welcomeMessage(){
       String quit  = "q";
        System.setIn(new ByteArrayInputStream(quit.getBytes()));
        BibliotecaApp.main(null);

        assertThat(outContent.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }
}
