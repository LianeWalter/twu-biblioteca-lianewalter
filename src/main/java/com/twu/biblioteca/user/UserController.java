package com.twu.biblioteca.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {


    private List<User> users;
    private User currentUser;

    public UserController() {

        this.users = new ArrayList<>();
        this.users.add(new User("123-4567", "secret"));
        this.currentUser = null;
    }


    public boolean login(String libraryNumber, String password){
        currentUser = this.users.stream().filter(user -> user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password)).findFirst().orElse(null);
        if(currentUser != null){
            return true;
        }
        return false;
    }


    public boolean isUserLoggedIn(){
        return false;
    }



}
