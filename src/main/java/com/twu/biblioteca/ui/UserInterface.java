package com.twu.biblioteca.ui;

import com.twu.biblioteca.book.BookController;
import com.twu.biblioteca.Option;
import com.twu.biblioteca.book.NoSuchBookAvailableException;
import com.twu.biblioteca.book.NotAValidBookReturnException;
import com.twu.biblioteca.movie.MovieController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private  List<Option> options;
    private Scanner scanner;
    private boolean programRunning;

    private BookController bookController;
    private MovieController movieController;



    public UserInterface(BookController bookController, MovieController movieController, Scanner scanner){
        programRunning = true;
        this.scanner = scanner;
        options = new ArrayList<>();
        options.add(new Option("List of books", "l"));
        options.add(new Option("Check out a book", "c"));
        options.add(new Option("Return a book", "r"));
        options.add(new Option("List of movies", "m"));
        options.add(new Option("Quit the application", "q"));

        this.bookController = bookController;
        this.movieController = movieController;

    }

    public  void selectOption(String option){
        switch (option) {
            case "l" :
                bookController.showBookList();
                break;
            case "q" :
                quitApplication();
                break;
            case "c":
                checkoutBook();
                break;
            case "r":
                returnBook();
                break;
            case "m":
                movieController.showMovieList();
                break;
            default :
                showErrorMessage();
                break;

        }
    }

    public void showOptions(){
        System.out.println("Main Menu \n ");
        options.forEach(System.out::println);
    }

    public void showErrorMessage(){
        System.out.println("Please select a valid option! \n");
    }


    private String readUserInput(){
        if(scanner.hasNext()){
            return scanner.nextLine();
        } else{
            return "";
        }
    }



    public  void quitApplication(){
        System.out.println("Application closed.");
        scanner.close();
        System.exit(0);
    }


    public void run() {

        String userInput;
        showOptions();
        userInput = scanner.next();
        selectOption(userInput);

        run();

    }

    public void checkoutBook() {
        System.out.println("What is the title of the book you want to check out?");
        String input = scanner.next();

        try {
            bookController.checkOutBook(input);
            System.out.println("Thank you! Enjoy the book\n");
        } catch (NoSuchBookAvailableException e) {
            System.out.println("This book is currently not available or does not exist. (Maybe you misspelled the title?) \n");
        }

    }

    public void returnBook(){
        System.out.println("What is the title of the book you want to return?");
        String input = scanner.next();
        try {
            bookController.returnBook(input);
            System.out.println("Thank you for returning the book");
        } catch (NotAValidBookReturnException e) {
            System.out.println("That is not a valid book to return.");
        }

    }
}
