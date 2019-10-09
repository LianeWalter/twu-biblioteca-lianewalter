package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.awt.font.TextLayout;
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

        bibliotecaApp.main(null);

        assertThat(outContent.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void showBooks(){
        bibliotecaApp.main(null);

        assertThat(outContent.toString(), allOf(containsString(books.get(0).getTitle()),containsString(books.get(0).getYear()), containsString(books.get(0).getAuthor())));



    }
}
