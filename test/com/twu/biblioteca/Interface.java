package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.awt.font.TextLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class Interface {

    private ByteArrayOutputStream outContent;
    BibliotecaApp bibliotecaApp;
    List<Book> books;

    @Before
    public void init(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        bibliotecaApp = new BibliotecaApp();
        books = new ArrayList<>();
        books.add(new Book("Kafka on the Shore", "Haruki Murakami", "2005"));
    }

    @Test
    public void welcomeMessage(){
        String input  = "l";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        bibliotecaApp.main(null);

        assertThat(outContent.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void showBooks(){
        String input  = "l";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        bibliotecaApp.main(null);
        assertThat(outContent.toString(), allOf(containsString(books.get(0).getTitle()),containsString(books.get(0).getYear()), containsString(books.get(0).getAuthor())));

    }
    @Test
    public void mainMenu(){
        String input  = "l";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        bibliotecaApp.main(null);
        assertThat(outContent.toString(), containsString("Main Menu"));

    }


    @Test
    public void invalidInput(){
        String input  = "z";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        bibliotecaApp.main(null);
        assertThat(outContent.toString(), containsString("Please select a valid option!"));

    }


}
