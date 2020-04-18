package com.fdmgroup.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fdmgroup.util.IdGenerator;

@Entity
@Table(name = "Reserved_Book")
@NamedQueries({
	@NamedQuery(name = "ReservedBook.findById", query = "Select b from Reservation b where b.book.isbn = ?1"),
	@NamedQuery(name = "ReservedBook.findByUser", query = "Select b from Reservation b where b.currentUser = ?1")
})
public class Reservation{
	@Id
	private int reservationId;
	private Date borrowDate;
	private Date returnDate;
	private boolean overdueStatus;
	private double overdueFee;
	@OneToOne 
	Book book;
	@ManyToOne
	@JoinColumn(name = "FK_USER_ID")
	User currentUser;
	
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId() {
		this.reservationId = IdGenerator.generate();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public static double calculateOverdueFee(Reservation book){
		Date rDate = book.getReturnDate();
		Date dateNow = new Date();	

		long diff = (dateNow.getTime() - rDate.getTime());
		int noOfDaysBetween = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println(noOfDaysBetween);
		double overdueFee = 1.00 * noOfDaysBetween;
		if(overdueFee <= 0.00){
			overdueFee = 0.00;
		}
		return overdueFee;
		
	}

	public static boolean isOverdueStatus(Reservation book){
		boolean overDueStatus;
		Date dateNow = new Date();
		Date rDate = book.getReturnDate();
		if(rDate.before(dateNow)){
			overDueStatus = true;
		}
		else {
			overDueStatus = false;
		}
		return overDueStatus;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isOverdueStatus() {
		return overdueStatus;
	}

	public void setOverdueStatus(boolean overdueStatus) {
		this.overdueStatus = overdueStatus;
	}

	public double getOverdueFee() {
		return overdueFee;
	}

	public void setOverdueFee(double overdueFee) {
		this.overdueFee = overdueFee;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Reservation() {
		super();

	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", borrowDate=" + borrowDate + ", returnDate="
				+ returnDate + ", overdueStatus=" + overdueStatus + ", overdueFee=" + overdueFee + ", book=" + book
				+ ", currentUser=" + currentUser + "]";
	}

	public Reservation(int reservationId, Date borrowDate, Date returnDate, boolean overdueStatus,
			double overdueFee, Book book, User currentUser) {
		super();
		this.reservationId = reservationId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.overdueStatus = overdueStatus;
		this.overdueFee = overdueFee;
		this.book = book;
		this.currentUser = currentUser;
	}

	
}
