package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {


    private static String welcomeMessage;
    private static List<Option> options;
    private static List<Book> books;
    private static Scanner scanner;

    private static boolean programRunning;

    private static void init(){
        programRunning = true;
        welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore! \n";

        scanner = new Scanner(System.in);

        options = new ArrayList<>();
        options.add(new Option("List of books", "l"));

        books = new ArrayList<>();
        books.add(new Book("Kafka on the Shore", "Haruki Murakami", "2005"));
        books.add(new Book("Norwegian Wood", "Haruki Murakami", "1989"));
        books.add(new Book("Buddenbrooks", "Thomas Mann", "1901"));



    }






    private static void showWelcomeMessage(){
        System.out.println(welcomeMessage);
    }

    private static void showBookList(){
        System.out.println("List of books: ");
        books.forEach(System.out::println);

    }

    public static void showOptions(){
        System.out.println("Main Menu \n ");
        options.forEach(System.out::println);
    }


    public static void selectOption(String option){
        switch (option) {
            case "l" :
                showBookList();
        }
    }

    private static String readUserInput(){
        return scanner.nextLine();
    }



    public static void main(String[] args) {
        init();
        showWelcomeMessage();
        showOptions();


        selectOption(readUserInput());


    }
}
