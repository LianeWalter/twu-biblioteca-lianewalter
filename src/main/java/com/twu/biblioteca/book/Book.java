package com.twu.biblioteca.book;

public class Book{

    private String title;
    private String author;
    private String year;

    private boolean isCheckedOut;


    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;

        this.isCheckedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    @Override
    public String toString() {

        String bookDescription = this.title +
                ", Author: " +
                this.author +
                ", Year: " +
                this.year;
        return bookDescription;
    }


}
