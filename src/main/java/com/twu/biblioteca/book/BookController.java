package com.twu.biblioteca.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookController {

    private  List<Book> books;


    public BookController(){

        books = new ArrayList<>();
        books.add(new Book("Kafka on the Shore", "Haruki Murakami", "2005"));
        books.add(new Book("Norwegian Wood", "Haruki Murakami", "1989"));
        books.add(new Book("Buddenbrooks", "Thomas Mann", "1901"));

    }


    public void showBookList(){
        System.out.println("List of books: ");
       List<Book> availableBooks = books.stream().filter(book -> !book.isCheckedOut()).collect(Collectors.toList());

       availableBooks.forEach(System.out::println);
    }


    public void checkOutBook(String title) throws NoSuchBookAvailableException {

        Book bookToCheckOut = books.stream().filter(book -> book.getTitle().equals(title) && !book.isCheckedOut()).findFirst().orElseThrow(NoSuchBookAvailableException::new);

        bookToCheckOut.setCheckedOut(true);

    }


    public void returnBook(String title) {
        Book bookToReturn = books.stream().filter(book -> book.getTitle().equals(title) && book.isCheckedOut()).findFirst().get();
        bookToReturn.setCheckedOut(false);
    }




}


