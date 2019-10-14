package com.twu.biblioteca;

import com.twu.biblioteca.book.BookController;
import com.twu.biblioteca.ui.UserInterface;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class UserInterfaceTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private ByteArrayOutputStream outContent;

    @Before
    public void init(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    }



    @Test
    public void shouldShowMainMenu(){
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
    public void shouldSignalizeInvalidInput(){
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
    public void shouldShowBookCheckoutSuccessMessage() {
        String input = "c\nKafka on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Thank you! Enjoy the book\n"))
        );


        userInterface.run();

    }
    @Test
    public void shouldShowBookCheckoutFailureMessageForAlreadyCheckedOutBook() {
        String input = "c\nKafka on the Shore\nc\nKafka on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("This book is currently not available or does not exist. (Maybe you misspelled the title?) \n"))
        );


        userInterface.run();

    }

    @Test
    public void shouldShowBookCheckoutFailureMessageForMisspellingTitle() {
        String input = "c\nKafker on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("This book is currently not available or does not exist. (Maybe you misspelled the title?) \n"))
        );


        userInterface.run();

    }

    @Test
    public void shouldReturnBookSuccessfully() {
        String input = "c\nKafka on the Shore\nr\nKafka on the Shore\nl\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), allOf(containsString("Thank you for returning the book"), containsString("Kafka on the Shore")))
        );


        userInterface.run();

    }



    @Test
    public void shouldExitApplicationCorrectly(){
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
