package com.twu.biblioteca.ui;

import com.twu.biblioteca.BookController;
import com.twu.biblioteca.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private  List<Option> options;
    private Scanner scanner;
    private boolean programRunning;

    private BookController bookController;



    public UserInterface(BookController bookController, Scanner scanner){
        programRunning = true;
        this.scanner = scanner;
        options = new ArrayList<>();
        options.add(new Option("List of books", "l"));
        options.add(new Option("Check out a book", "c"));
        options.add(new Option("Quit the application", "q"));

        this.bookController = bookController;

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

        bookController.checkOutBook(input);
        System.out.println("Thank you! Enjoy the book\n");

    }
}
