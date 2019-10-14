package com.twu.biblioteca;

import com.twu.biblioteca.ui.UserInterface;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private BibliotecaApp bibliotecaApp;
    private ByteArrayOutputStream outContent;


    @Before
    public void init(){
        bibliotecaApp = new BibliotecaApp();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldShowWelcomeMessage(){
       String quit  = "q";
        System.setIn(new ByteArrayInputStream(quit.getBytes()));

        exit.expectSystemExit();
        exit.checkAssertionAfterwards(() ->
                assertThat(outContent.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"))
        );

        BibliotecaApp.main(null);

    }
}
