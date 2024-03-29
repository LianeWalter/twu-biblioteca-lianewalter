package com.twu.biblioteca;

import com.twu.biblioteca.library.book.BookController;
import com.twu.biblioteca.library.movie.MovieController;
import com.twu.biblioteca.ui.UserInterface;
import com.twu.biblioteca.user.User;
import com.twu.biblioteca.user.UserController;
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
    private User mockUser;

    @Before
    public void init(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        mockUser = new User("123-4567", "secret");

    }



    @Test
    public void shouldShowMainMenu(){
        String input = "q";
        Scanner scanner = new Scanner(input);
        UserInterface userInterface = new UserInterface(new UserController(), null,null , scanner);

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
        UserInterface userInterface = new UserInterface(new UserController(), new BookController(), null , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
            assertThat(outContent.toString(), containsString("Please select a valid option!"))
        );

        userInterface.run();

    }


    @Test
    public void shouldShowCheckoutOptionsIfLoggedIn(){
        String input = "c\nb\nKafka on the Shore\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());
        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Which item do you want to borrow? Press \"m\" for movies and \"b\" for books."))
        );

        userInterface.run();


    }

    @Test
    public void shouldNotShowCheckOutOptionIfNotLoggedIn(){
        String input = "q\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new UserController(), new BookController(), new MovieController() , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
                    assertThat(outContent.toString(), not(containsString("Check out an item")));
                }
        );


        userInterface.run();
    }
    @Test
    public void shouldNotAllowToCheckOutIfNotLoggedIn(){
        String input = "c\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new UserController(), new BookController(), new MovieController() , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("You must be logged in for this action."))

        );


        userInterface.run();
    }
    @Test
    public void shouldNotShowReturnOptionIfNotLoggedIn(){
        String input = "q\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new UserController(), new BookController(), new MovieController() , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() -> {
                    assertThat(outContent.toString(), not(containsString("Return an item")));
                }
        );


        userInterface.run();
    }
    @Test
    public void shouldNotAllowToReturnIfNotLoggedIn(){
        String input = "r\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new UserController(), new BookController(), new MovieController() , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("You must be logged in for this action."))

        );


        userInterface.run();
    }



    @Test
    public void shouldShowLoginSuccessMessage() {
        String input = "l\n123-4567\nsecret\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        UserInterface userInterface = new UserInterface(new UserController(), null, new MovieController() , scanner);

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("You're successfully logged in."))
        );


        userInterface.run();

    }

    @Test
    public void shouldShowMovieCheckoutSuccessMessage() {
        String input = "c\nm\nFrozen\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());

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

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());

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

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());

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

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("This book is currently not available or does not exist. (Maybe you misspelled the title?) \n"))
        );


        userInterface.run();

    }

    @Test
    public void shouldReturnBookSuccessfully() {
        String input = "c\nb\nKafka on the Shore\nr\nKafka on the Shore\nb\nq\n";
        Scanner scanner = new Scanner(input).useDelimiter("\n");

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());

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

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());

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

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);
        userController.login(mockUser.getLibraryNumber(), mockUser.getPassword());

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

        UserController userController = new UserController();
        UserInterface userInterface = new UserInterface(userController, new BookController(), new MovieController() , scanner);

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
            UserInterface userInterface = new UserInterface(new UserController(), null, null , scanner);

            exit.expectSystemExit();
            exit.checkAssertionAfterwards(() ->
                 assertThat(outContent.toString(), containsString("Application closed."))
            );

        userInterface.run();

    }







}
