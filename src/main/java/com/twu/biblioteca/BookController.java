package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

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
        books.forEach(System.out::println);

    }

}


