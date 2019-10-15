package com.twu.biblioteca;

public class LibraryItem {
    private String title;
    private boolean isCheckedOut;

    public LibraryItem(String title) {
        this.title = title;

        this.isCheckedOut = false;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

}
