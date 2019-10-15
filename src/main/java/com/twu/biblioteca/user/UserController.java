package com.twu.biblioteca.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {


    private List<User> users;

    public UserController() {

        this.users = new ArrayList<>();
        this.users.add(new User("123-4567", "secret"));
    }


    public boolean login(String libraryNumber, String password){
        List<User> userList = this.users.stream().filter(user -> user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password)).collect(Collectors.toList());

        return !userList.isEmpty();
    }
}
