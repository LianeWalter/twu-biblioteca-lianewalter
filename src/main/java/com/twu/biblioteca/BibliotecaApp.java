package com.twu.biblioteca;


import com.twu.biblioteca.library.book.BookController;
import com.twu.biblioteca.library.movie.MovieController;
import com.twu.biblioteca.ui.UserInterface;

import java.util.Scanner;

public class BibliotecaApp {


    private static String welcomeMessage;
    private static UserInterface userInterface;



    public static void init(){
        welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore! \n";
        BookController bookController = new BookController();
        MovieController movieController = new MovieController();

        userInterface = new UserInterface(new UserController(), bookController, movieController, new Scanner(System.in).useDelimiter("\n"));





    }






    public static void showWelcomeMessage(){
        System.out.println(welcomeMessage);
    }













    public static void main(String[] args) {
        init();


        showWelcomeMessage();
        userInterface.run();



    }
}
