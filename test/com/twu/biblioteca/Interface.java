package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.awt.font.TextLayout;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;


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

        books.add(new Book("Kafka on the Shore"));

    }

    @Test
    public void welcomeMessage(){

        bibliotecaApp.main(null);

        assertThat(outContent.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void showBooks(){
        assertThat(outContent.toString(), containsString(books.get(0).getTitle()));

    }
}
