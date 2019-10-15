package com.twu.biblioteca.book;

import com.twu.biblioteca.LibraryItem;

public class Book extends LibraryItem {

    private String author;
    private String year;



    public Book(String title, String author, String year) {
        super(title);

        this.author = author;
        this.year = year;

    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {

        String bookDescription = getTitle() +
                ", Author: " +
                this.author +
                ", Year: " +
                this.year;
        return bookDescription;
    }


}
