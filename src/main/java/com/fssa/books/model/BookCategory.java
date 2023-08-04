package com.fssa.books.model;



public enum BookCategory {
    FICTION, NON_FICTION, SCIENCE_FICTION, MYSTERY, ROMANCE, FANTASY, THRILLER, HORROR, HISTORY, BIOGRAPHY,
    SELF_HELP, POETRY, OTHER;

    // No need for the constructor, as enum constants are automatically created

    // No need for the bookCategory field

    // Getter method to retrieve the booking category as a string
    public String getBookCategory() {
        return this.name(); // Return the name of the enum constant as a string
    }
}

//Property to store the booking category as a string
