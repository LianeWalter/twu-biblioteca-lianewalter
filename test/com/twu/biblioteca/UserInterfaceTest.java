package com.twu.biblioteca;

import com.twu.biblioteca.ui.UserInterface;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class UserInterfaceTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private ByteArrayOutputStream outContent;
    List<Book> mockBooks;

    @Before
    public void init(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        mockBooks = new ArrayList<>();
        mockBooks.add(new Book("Kafka on the Shore", "Haruki Murakami", "2005"));
    }



    @Test
    public void showBooks(){
        String input = "l q";
        Scanner scanner = new Scanner(input);

        UserInterface userInterface = new UserInterface(new BookController(), scanner);


        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
            assertThat(outContent.toString(), allOf(containsString(mockBooks.get(0).getTitle()),containsString(mockBooks.get(0).getYear()), containsString(mockBooks.get(0).getAuthor())));
        });
        userInterface.run();
    }
    @Test
    public void mainMenu(){
        String input = "q";
        Scanner scanner = new Scanner(input);
        UserInterface userInterface = new UserInterface(new BookController(), scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Main Menu"))
        );

        userInterface.run();




    }


    @Test
    public void invalidInput(){
        String input = "x q";
        Scanner scanner = new Scanner(input);
        UserInterface userInterface = new UserInterface(new BookController(), scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
            assertThat(outContent.toString(), containsString("Please select a valid option!"))
        );

        userInterface.run();

    }


    @Test
    public void exitApplication(){
            String input = "q";
            Scanner scanner = new Scanner(input);
            UserInterface userInterface = new UserInterface(new BookController(), scanner);

            exit.expectSystemExit();
            exit.checkAssertionAfterwards(() ->
                 assertThat(outContent.toString(), containsString("Application closed."))
            );

        userInterface.run();

    }





}
