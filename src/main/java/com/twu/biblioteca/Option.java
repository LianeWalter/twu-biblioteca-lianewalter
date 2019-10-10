package com.twu.biblioteca;

public class Option {

    private String description;
    private String command;

    public Option(String description, String command) {
        this.description = description;
        this.command = command;
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


    @Override
    public String toString() {
        String option = this.description
                + " (to select enter \""

                + this.command
                + "\")";

        return option;
    }
}
