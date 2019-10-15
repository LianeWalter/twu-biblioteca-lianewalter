package com.twu.biblioteca;

import com.twu.biblioteca.library.book.BookController;
import com.twu.biblioteca.library.movie.MovieController;
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
        UserInterface userInterface = new UserInterface(null,null , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Main Menu"))
        );

        userInterface.run();




    }


    @Test
    public void shouldSignalizeInvalidInput(){
        String input = "x\nq";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), null , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
            assertThat(outContent.toString(), containsString("Please select a valid option!"))
        );

        userInterface.run();

    }


    @Test
    public void shouldShowCheckoutOptions(){
        String input = "c\nb\nKafka on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), new MovieController() , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Which item do you want to borrow? Press \"m\" for movies and \"b\" for books."))
        );

        userInterface.run();


    }
    @Test
    public void shouldShowMovieCheckoutSuccessMessage() {
        String input = "c\nm\nFrozen\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(null, new MovieController() , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Thank you! Enjoy the movie\n"))
        );


        userInterface.run();

    }

    @Test
    public void shouldShowBookCheckoutSuccessMessage() {
        String input = "c\nb\nKafka on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), null , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Thank you! Enjoy the book\n"))
        );


        userInterface.run();

    }
    @Test
    public void shouldShowBookCheckoutFailureMessageForAlreadyCheckedOutBook() {
        String input = "c\nb\nKafka on the Shore\nc\nb\nKafka on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), null, scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("This book is currently not available or does not exist. (Maybe you misspelled the title?) \n"))
        );


        userInterface.run();

    }

    @Test
    public void shouldShowBookCheckoutFailureMessageForMisspellingTitle() {
        String input = "c\nb\nKafker on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), null, scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("This book is currently not available or does not exist. (Maybe you misspelled the title?) \n"))
        );


        userInterface.run();

    }

    @Test
    public void shouldReturnBookSuccessfully() {
        String input = "c\nb\nKafka on the Shore\nr\nKafka on the Shore\nl\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), null, scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), allOf(containsString("Thank you for returning the book"), containsString("Kafka on the Shore")))
        );


        userInterface.run();

    }

    @Test
    public void shouldShowBookReturnFailureMessageForUnknownBook() {
        String input = "r\nWinnie-the-Pooh\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), null, scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("That is not a valid book to return."))
        );


        userInterface.run();

    }

    @Test
    public void shouldShowBookReturnFailureMessageForMisspellingTitle() {
        String input = "c\nb\nKafka on the Shore\nr\nKafker on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new BookController(), null , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("That is not a valid book to return."))
        );


        userInterface.run();

    }

    @Test
    public void shouldNavigateToMovieListSuccessfully(){
        String input = "m\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(null, new MovieController(), scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("List of movies"))
        );


        userInterface.run();

    }





    @Test
    public void shouldExitApplicationCorrectly(){
            String input = "q";
            Scanner scanner = new Scanner(input);
            UserInterface userInterface = new UserInterface(null, null , scanner);

            exit.expectSystemExit();
            exit.checkAssertionAfterwards(() ->
                 assertThat(outContent.toString(), containsString("Application closed."))
            );

        userInterface.run();

    }







}
