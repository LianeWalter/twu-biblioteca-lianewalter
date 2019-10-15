package com.twu.biblioteca.ui;

import com.twu.biblioteca.library.book.BookController;
import com.twu.biblioteca.library.book.NoSuchBookAvailableException;
import com.twu.biblioteca.library.book.NotAValidBookReturnException;
import com.twu.biblioteca.library.movie.MovieController;
import com.twu.biblioteca.user.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private  List<Option> options;
    private Scanner scanner;
    private boolean programRunning;

    private UserController userController;
    private BookController bookController;
    private MovieController movieController;



    public UserInterface(UserController userController, BookController bookController, MovieController movieController, Scanner scanner){
        programRunning = true;
        this.scanner = scanner;
        options = new ArrayList<>();
        options.add(new Option("List of books", "b"));
        options.add(new Option("List of movies", "m"));
        options.add(new Option("Check out an item", "c"));
        options.add(new Option("Return an item", "r"));
        options.add(new Option("Login", "l"));
        options.add(new Option("Quit the application", "q"));

        this.userController = userController;
        this.bookController = bookController;
        this.movieController = movieController;

    }

    public  void selectOption(String option){
        switch (option) {
            case "b" :
                bookController.showBookList();
                break;
            case "q" :
                quitApplication();
                break;
            case "c":
                checkoutItem();
                break;
            case "r":
                returnBook();
                break;
            case "m":
                movieController.showAvailableMovieList();
                break;
            case "l":
                login();
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


    public void login(){
        System.out.println("Please enter your library number. The format is \"xxx-xxxx\": ");
        String libraryNumber = scanner.next();

        System.out.println("Please enter your password: ");
        String password = scanner.next();

        boolean isLoginSuccessful = userController.login(libraryNumber, password);

        if(isLoginSuccessful){
            System.out.println("You're successfully logged in.");
        } else{
            System.out.println("Please try again.");
        }

    }

    public void checkoutItem(){
        System.out.println("Which item do you want to borrow? Press \"m\" for movies and \"b\" for books.");
        String input = scanner.next();
        switch(input){
            case "m" :
                checkoutMovie();
                break;
            case "b" :
                checkoutBook();
                break;
        }
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


    public void checkoutMovie(){
        System.out.println("What is the title of the movie you want to check out?");
        String input = scanner.next();
        movieController.checkOutMovie(input);
        System.out.println("Thank you! Enjoy the movie\n");


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
