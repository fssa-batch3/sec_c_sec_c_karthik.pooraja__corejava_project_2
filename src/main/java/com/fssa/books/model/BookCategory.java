package com.fssa.books.model;

public enum BookCategory {
	FICTION, NON_FICTION, SCIENCE_FICTION, MYSTERY, ROMANCE, FANTASY, THRILLER, HORROR, HISTORY, BIOGRAPHY, SELF_HELP,
	POETRY, OTHER;

	public String getBookCategory() {
		return this.name(); 
	}
}

//Property to store the booking category as a string
