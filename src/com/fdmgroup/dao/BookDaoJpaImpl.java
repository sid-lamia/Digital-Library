package com.fdmgroup.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.fdmgroup.model.Book;
import com.fdmgroup.model.Reservation;
import com.fdmgroup.model.User;
import com.fdmgroup.util.JpaUtility;


//methods in BookDao implemented using JPA

public class BookDaoJpaImpl implements IBookDao{
	
	//method for adding a new book to the database
	@Override
	public void create(Book t){
		EntityManager em = JpaUtility.getInstance().getEntityManager(); 
		em.getTransaction().begin();
		em.persist(t); //adding book to book_database
		em.getTransaction().commit();
		em.close();
		}

	//method for finding book from isbn
	@Override
	public Book findById(int id) { 
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		Query query = em.createNamedQuery("Book.findById"); //use get single result only when you are sure it will return a result
		query.setParameter(1, id);
		List<Book> bookList = query.getResultList();
		if(bookList.size() != 0) {
			return bookList.get(0);
		}
		return null;
	}

	//method for finding all books in the database in alphabetical order
	@Override
	public List<Book> findAll() {
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		List<Book> bookList = em.createNamedQuery("Book.findAll").getResultList();
		return bookList;
	}

	//used by librarian to manually update the availability of the book when the user returns it 
	@Override
	public Book update(Book t){
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		em.getTransaction().begin();
		Book b = em.find(Book.class, t.getIsbn());
		b.setAvailability("Available");  //set the book as available
		Query query = em.createNamedQuery("ReservedBook.findById");
		query.setParameter(1, b.getIsbn());
		Reservation res = (Reservation) query.getSingleResult();
		em.remove(res); //delete reservation details from reserved book table for that book
		em.getTransaction().commit();
		em.close();
		return b;
	}

	//for deleting books from the database
	@Override
	public void remove(Book t){
		EntityManager em = JpaUtility.getInstance().getEntityManager(); 
		em.getTransaction().begin();
		Book b = em.find(Book.class, t.getIsbn());
		if(b.getAvailability().equals("Available")){ //will not work if book is reserved by a user
			em.remove(b); //removing book from book_database
		}
		else{
			System.out.println("The book is currently not available!");
		}
		em.getTransaction().commit();
		em.close();
	}

	//method for reserving a book
	@Override
	public void reserve(Book book, User currentUser){
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		
		Date borrowDate = new Date(); //borrow date is the current system date when the user clicks on reserve
		Calendar cal = Calendar.getInstance();
		cal.setTime(borrowDate);
		cal.add(Calendar.MONTH, 2); //return date is 2 months after borrow date
		Date returnDate =  cal.getTime();
		
		em.getTransaction().begin();
		
		Book rb = em.find(Book.class, book.getIsbn());
		rb.setAvailability("Not Available"); //set availability for the book to not availble
		Reservation rs = new Reservation();
		rs.setReservationId(); //set a reservation id
		rs.setCurrentUser(currentUser); //set the user as the current logged in user
		rs.setBorrowDate(borrowDate); 
		rs.setReturnDate(returnDate);
		rs.setBook(rb);
		rb.setReserveStatus(rs);
		em.persist(rs); //insert reserved book entry into database
		em.getTransaction().commit();
		em.close();
	}


	//method for finding all books a user has reserved
	@Override
	public List<Reservation> findByUser(User user) {
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		Query query = em.createNamedQuery("ReservedBook.findByUser");
		query.setParameter(1, user);
		List<Reservation> bookList = query.getResultList();
		return bookList;
	}

	//method for finding if a book is overdue and updating it in the database
	@Override
	public Reservation updateOverdueStatus(Reservation book) {
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		double fee = 0.0;
		
		if(Reservation.isOverdueStatus(book)){ //find out if the book is overdue or not
			fee = Reservation.calculateOverdueFee(book); //find overdue fee
			em.getTransaction().begin();
			Reservation res = em.find(Reservation.class, book.getReservationId());
			res.setOverdueStatus(true);
			res.setOverdueFee(fee);
			em.getTransaction().commit();
			return res;
		}
		else{
			return book;
		}

	}

	//for showing all currently reserved books
	@Override
	public List<Book> findAllReserved() {
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		List<Book> bookList = em.createNamedQuery("Book.findAllReserved").getResultList();
		return bookList;
	}
	//for showing all currently available books
	@Override
	public List<Book> findAllAvailable() {
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		List<Book> bookList = em.createNamedQuery("Book.findAllAvailable").getResultList();
		return bookList;
	}
	
//different types of searches
	@Override
	public List<Book> findByAuthor(String author){
		String searchAuthor = author.toLowerCase();
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		Query query = em.createNamedQuery("Book.findByAuthor");
		query.setParameter(1, "%" + searchAuthor + "%");
		List<Book> bookList = query.getResultList();
		return bookList;
	}

	@Override
	public List<Book> findByGenre(String genre){
		String searchGenre = genre.toLowerCase();
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		Query query = em.createNamedQuery("Book.findByGenre");
		query.setParameter(1, "%" + searchGenre + "%");
		List<Book> bookList = query.getResultList();
		return bookList;
	}
	@Override
	public List<Book> findByTitle(String title){
		String searchTitle = title.toLowerCase();
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		Query query = em.createNamedQuery("Book.findByTitle");
		query.setParameter(1, "%" + searchTitle + "%");
		List<Book> bookList = query.getResultList();
		return bookList;
	}

	@Override
	public List<Book> findByKeyword(String keyword){
		String searchKeyword = keyword.toLowerCase();
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		Query query = em.createNamedQuery("Book.findByKeyword");
		query.setParameter(1, "%" + searchKeyword + "%");
		List<Book> bookList = query.getResultList();
		return bookList;
	}

}
