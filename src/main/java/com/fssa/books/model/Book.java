/**
 * 
 */
package com.fssa.books.model;

import java.time.LocalDate;

/**
 * @author KarthikRajaPooraja
 *
 */
public class Book {
	private String title;
	private int edition;
	private String author;
	private LocalDate publisheddate;
	private String publishername;
	private BookCategory categoryname;
	private int id;
	private String bookimageurl;
	private int stock;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getPublisheddate() {
		return publisheddate;
	}

	public void setPublisheddate(LocalDate publisheddate) {
		this.publisheddate = publisheddate;
	}

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

	public BookCategory getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(BookCategory categoryname) {
		this.categoryname = categoryname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookimageurl() {
		return bookimageurl;
	}

	public void setBookimageurl(String bookimageurl) {
		this.bookimageurl = bookimageurl;
	}

	/**
     * Constructs a new `Book` object with default values for all attributes.
     */
    public Book() {

    }

    /**
     * Constructs a new `Book` object with provided values for all attributes.
     *
     * @param id           The unique identifier of the book.
     * @param title        The title of the book.
     * @param edition      The edition of the book.
     * @param author       The author of the book.
     * @param publisheddate The published date of the book.
     * @param publishername The name of the publisher.
     * @param categoryname The category of the book.
     * @param bookimageurl The URL of the book's image.
     */
    public Book(int id, String title, int edition, String author, LocalDate publisheddate,
                String publishername, BookCategory categoryname, String bookimageurl,int stock) {
        // Constructor implementation...
    }

    /**
     * Constructs a new `Book` object with provided values for all attributes.
     * Note: This constructor throws an `UnsupportedOperationException` as its implementation is missing.
     *
     * @param id           The unique identifier of the book.
     * @param title        The title of the book.
     * @param author       The author of the book.
     * @param publishedDate The published date of the book.
     * @param publisher    The name of the publisher.
     * @param bookImageUrl The URL of the book's image.
     * @param edition      The edition of the book.
     * @param category     The category of the book.
     * @throws UnsupportedOperationException Thrown when the constructor implementation is missing.
     */
    public Book(int id, String title, String author, LocalDate publishedDate, String publisher,
                String bookImageUrl, int edition, BookCategory category) {
        throw new UnsupportedOperationException("Constructor implementation is missing.");
    }

    @Override
	public String toString() {
		return "Book [title=" + title + ", edition=" + edition + ", author=" + author + ", publisheddate="
				+ publisheddate + ", publishername=" + publishername + ", categoryname=" + categoryname + ", id=" + id
				+ ", bookimageurl=" + bookimageurl + ", stock=" + stock + "]";
	}

	public int getStock1() {
		// TODO Auto-generated method stub
		return 0;
	}
}
