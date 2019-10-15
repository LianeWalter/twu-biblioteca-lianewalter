package com.twu.biblioteca.ui;

public class Option {

    private String description;
    private String command;
    private boolean isLoggedInOnly;


    public Option(String description, String command, boolean isLoggedInOnly) {
        this.description = description;
        this.command = command;
        this.isLoggedInOnly = isLoggedInOnly;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


    public boolean isLoggedInOnly() {
        return isLoggedInOnly;
    }

    public void setLoggedInOnly(boolean loggedInOnly) {
        isLoggedInOnly = loggedInOnly;
    }


    @Override
    public String toString() {
        String option = this.description
                + " (to select enter \""

                + this.command
                + "\")";

        return option;
    }
}
