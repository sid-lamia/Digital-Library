package com.fdmgroup.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_DATABASE")
@NamedQueries({
	@NamedQuery(name = "Book.findById", query = "Select b from Book b where b.isbn = ?1"),
	@NamedQuery(name = "Book.findAll", query = "Select b from Book b order by b.title"),
	@NamedQuery(name = "Book.findByTitle", query = "Select b from Book b where lower(b.title) like ?1"),
	@NamedQuery(name = "Book.findByAuthor", query = "Select b from Book b where lower(b.author) like ?1"), 
	@NamedQuery(name = "Book.findByGenre", query = "Select b from Book b where lower(b.genre) like ?1"),
	@NamedQuery(name = "Book.findByKeyword", query = "Select b from Book b where lower(b.title) LIKE ?1 or lower(b.author) like ?1 or lower(b.genre) like ?1"), 
	@NamedQuery(name = "Book.findAllReserved", query = "Select b from Book b where b.availability = 'Not Available' order by b.title "),
	@NamedQuery(name = "Book.findAllAvailable", query = "Select b from Book b where b.availability = 'Available' order by b.title "),
})
public class Book implements IStorable {
	@Id
	@Column(name = "ISBN", nullable = false, length = 10, unique = true)
	private int isbn; 
	private String title; 
	private String author; 
	private String genre;
	private int numberOfPages;
	private String availability; 
	private String publishedYear;
	private String summary;
	@OneToOne(mappedBy = "book")
	private Reservation reserveStatus;
	
	
	public Book(int isbn, String title, String author, String genre, int numberOfPages, String availability,
			String publishedYear, String summary, Reservation reserveStatus, User currentUser) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.numberOfPages = numberOfPages;
		this.availability = availability;
		this.publishedYear = publishedYear;
		this.summary = summary;
		this.reserveStatus = reserveStatus;
	}
	
	
	public Reservation getReserveStatus() {
		return reserveStatus;
	}
	public void setReserveStatus(Reservation reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", genre=" + genre
				+ ", numberOfPages=" + numberOfPages + ", availability=" + availability + ", publishedYear="
				+ publishedYear + ", summary=" + summary + "]";
	}


	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Book(int isbn, String title, String author, String genre, int numberOfPages, String availability,
			String publishedYear, String summary, Reservation reserveStatus) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.numberOfPages = numberOfPages;
		this.availability = availability;
		this.publishedYear = publishedYear;
		this.summary = summary;
		this.reserveStatus = reserveStatus;
	}


	public Book(int isbn, String title, String author, String genre, int numberOfPages, String publishedYear,
			String summary) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.numberOfPages = numberOfPages;
		this.publishedYear = publishedYear;
		this.summary = summary;
		this.availability = "Available";
		this.reserveStatus = null;
	}

	
}

	

