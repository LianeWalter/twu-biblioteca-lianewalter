package com.twu.biblioteca;


import com.twu.biblioteca.book.Book;
import com.twu.biblioteca.book.BookController;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;


public class BookControllerTest {

    private ByteArrayOutputStream outContent;
    List<Book> mockBooks;

    @Before
    public void init(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        mockBooks = new ArrayList<>();
        mockBooks.add(new Book("Kafka on the Shore", "Haruki Murakami", "2005"));
        mockBooks.add(new Book("Norwegian Wood", "Haruki Murakami", "1989"));


    }

    @Test
    public void shouldShowAvailableBooks() {
        BookController bookController = new BookController();
        bookController.showBookList();
        assertThat(outContent.toString(), allOf(containsString(mockBooks.get(0).getTitle()),containsString(mockBooks.get(0).getYear()), containsString(mockBooks.get(0).getAuthor())));


    }

    @Test
    public void shouldCheckOutBookCorrectly() {
        BookController bookController = new BookController();
        bookController.checkOutBook("Kafka on the Shore");
        bookController.showBookList();
        assertThat(outContent.toString(),not((containsString(mockBooks.get(0).getTitle()))));
    }
}
