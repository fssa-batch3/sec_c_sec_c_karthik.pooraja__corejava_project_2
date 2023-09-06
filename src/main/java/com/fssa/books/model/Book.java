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

	public Book() {

	}

	public Book(int id, String title, int edition, String author, LocalDate publisheddate, String publishername,
			BookCategory categoryname, String bookimageurl) {
		super();
		this.title = title;
		this.edition = edition;
		this.author = author;
		this.publisheddate = publisheddate;
		this.publishername = publishername;
		this.categoryname = categoryname;
		this.id = id;
		this.bookimageurl = bookimageurl;

	}

	public Book(int id, String title, String author, LocalDate publishedDate, String publisher, String bookImageUrl,
			int edition, BookCategory category) {

		this.id = id;
		this.title = title;
		this.author = author;
		this.publisheddate = publishedDate;
		this.publishername = publisher;
		this.bookimageurl = bookImageUrl;
		this.edition = edition;
		this.categoryname = category;

		throw new UnsupportedOperationException("Constructor implementation is missing.");
	}
	 

	@Override
	    public String toString() {
	        return "Book{" +
	                "id=" + id +
	                ", title='" + title + '\'' +
	                ", edition=" + edition +
	                ", author='" + author + '\'' +
	                ", publishedDate=" + publisheddate +
	                ", publisherName='" + publishername + '\'' +
	                ", category=" + categoryname+
	                ", bookImageUrl='" + bookimageurl + '\'' +
	                '}';
	    }
}
