/*package com.fdmgroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.model.Book;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserSession;
import com.fdmgroup.util.DataSource;


public class BookDao implements IBookDao{
	private static Logger logger = LogManager.getLogger("ProjectLogger"); 	
	//test
	@Override
	public void create(Book book) throws SQLException {
		String query = "INSERT INTO BOOK_DATABASE(TITLE, AUTHOR, GENRE, ISBN, NUMBER_OF_PAGES, AVAILABILITY) VALUES (?,?,?,?,?,?)";

		try(Connection con = DataSource.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(query); 
			){
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getGenre());
			stmt.setString(4, book.getIsbn());
			stmt.setInt(5, book.getNumberOfPages());
			stmt.setString(6, book.getAvailability());
								
			int rows = stmt.executeUpdate();
			System.out.println(rows + "row/s inserted successfully");
		}
	}

	@Override
	//method called when deleting a book from the database
	public void remove(Book t) throws SQLException {
	String query = "DELETE FROM BOOK_DATABASE WHERE TITLE =?";
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(query);){
			stmt.setString(1, t.getTitle());
			//stmt.setString(2, t.getIsbn());
			
			int count = stmt.executeUpdate();
			System.out.println(count + " row/s updated. " + t.getTitle() + " with ISBN " + t.getIsbn() + " has been removed from the database.");
			} 
	}

	@Override
	//matches input string and returns all books matching that title in a list form 
	public List<Book> findByTitle(String title) throws SQLException {
		List<Book> searchResult = new ArrayList<>();
		String searchTitle = title.toLowerCase();
		String query = "SELECT * FROM BOOK_DATABASE WHERE LOWER(TITLE) LIKE ?";
		
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(query);){
			stmt.setString(1, "%" + searchTitle + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String bookTitle = rs.getString(1);
				String author = rs.getString(2);
				String genre = rs.getString(3);
				String isbn = rs.getString(4);
				int pages = rs.getInt(5);
				String availability = rs.getString(6);
				Book book = new Book(bookTitle, author, genre, isbn, pages, availability);
				searchResult.add(book);
			}
			return searchResult; 
		} 
	}
	
	@Override
	public List<Book> findByAuthor(String author) throws SQLException {
		List<Book> searchResult = new ArrayList<>();
		String searchAuthor = author.toLowerCase();
		String query = "SELECT * FROM BOOK_DATABASE WHERE LOWER(AUTHOR) LIKE ?";
		
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(query);){
			stmt.setString(1, "%" + searchAuthor + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String bookTitle = rs.getString(1);
				String auth = rs.getString(2);
				String genre = rs.getString(3);
				String isbn = rs.getString(4);
				int pages = rs.getInt(5);
				String availability = rs.getString(6);
				Book book = new Book(bookTitle, auth, genre, isbn, pages, availability);
				searchResult.add(book);
			}
			return searchResult; 
		} 
	}

	@Override
	public List<Book> findByGenre(String genre) throws SQLException {
		List<Book> searchResult = new ArrayList<>();
		String searchGenre = genre.toLowerCase();
		String query = "SELECT * FROM BOOK_DATABASE WHERE LOWER(GENRE) LIKE ?";
		
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(query);){
			stmt.setString(1, "%" + searchGenre + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String bookTitle = rs.getString(1);
				String auth = rs.getString(2);
				String gnr = rs.getString(3);
				String isbn = rs.getString(4);
				int pages = rs.getInt(5);
				String availability = rs.getString(6);
				Book book = new Book(bookTitle, auth, gnr, isbn, pages, availability);
				searchResult.add(book);
			}
			return searchResult; 
		} 
	}

	@Override
	public List<Book> findByKeyword(String keyword) throws SQLException {
		List<Book> searchResult = new ArrayList<>();
		String searchKeyword = keyword.toLowerCase();
		String query = "SELECT * FROM BOOK_DATABASE WHERE LOWER(TITLE) LIKE ? OR LOWER(AUTHOR) LIKE ? OR LOWER(GENRE) LIKE ?";
		
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(query);){
			stmt.setString(1, "%" + searchKeyword + "%");
			stmt.setString(2, "%" + searchKeyword + "%");
			stmt.setString(3, "%" + searchKeyword + "%");

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String bookTitle = rs.getString(1);
				String auth = rs.getString(2);
				String gnr = rs.getString(3);
				String isbn = rs.getString(4);
				int pages = rs.getInt(5);
				String availability = rs.getString(6);
				Book book = new Book(bookTitle, auth, gnr, isbn, pages, availability);
				searchResult.add(book);
			}
		return searchResult;
		}

	}
	@Override
	public void reserve(Book book) throws SQLException {
		String query = "UPDATE BOOK_DATABASE SET AVAILABILITY = 'Not Available', DATE_BORROWED = ?, RETURN_DATE = ? WHERE ISBN = ?" ;
		String query2 = "INSERT INTO RESERVED_BOOKS (ISBN, USER_ID) VALUES (?, ?)";
		User currentUser = UserSession.getLoggedInUser();
		LocalDate borrowDate = LocalDate.now();
		LocalDate returnDate = borrowDate.plusDays(90); 	//the return date is 90 days after the borrowDate

		logger.debug(borrowDate);
		logger.debug(returnDate);
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement ps1 = con.prepareStatement(query);
				PreparedStatement ps2 = con.prepareStatement(query2)){
			
			ps1.setString(1, borrowDate.toString());
			ps1.setString(2, returnDate.toString() );  
			ps1.setString(3, book.getIsbn());
			ps2.setString(1, book.getIsbn());
			ps2.setInt(2, currentUser.getId());
			
			logger.debug(query);
			int count1 = ps1.executeUpdate();
			int count2 = ps2.executeUpdate();
			logger.trace(count1 + " row/s updated in book_database");
			logger.trace(count2 + " row/s updated in reserved_books");
 
		}
	}
	@Override
	//automatically updates when user cancels a reservation
	public void cancelReservation(Book book) throws SQLException{
		String query1 = "UPDATE BOOK_DATABASE SET AVAILABILITY = 'Available', DATE_BORROWED = NULL, RETURN_DATE = NULL WHERE ISBN = ?" ;
		String query2 = "DELETE FROM RESERVED_BOOKS WHERE USER_ID = ? AND ISBN = ?";
		User currentUser = UserSession.getLoggedInUser();
		
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement ps1 = con.prepareStatement(query1);
				PreparedStatement ps2 = con.prepareStatement(query2)){
			ps1.setString(1, book.getIsbn());
			ps2.setInt(1, currentUser.getId());
			ps2.setString(2, book.getIsbn());
			int count1 = ps1.executeUpdate();
			int count2 = ps2.executeUpdate();
			logger.trace(count1 + " row/s updated in book_database");
			logger.trace(count2 + " row/s updated in reserved_books");
		}
		
	}
	
	@Override
	public Book findById(int id) {
		return null;
	}

	@Override
	public List<Book> findAll() {
		return null;
	}

	@Override
	// manually updates books and sets availablity to available and dates to null
	public Book update(Book book) throws SQLException {
		String query1 = "UPDATE BOOK_DATABASE SET AVAILABILITY = 'Available', DATE_BORROWED = NULL, RETURN_DATE = NULL WHERE ISBN = ?" ;
		String query2 = "DELETE FROM RESERVED_BOOKS WHERE ISBN = ?";		
		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement ps1 = con.prepareStatement(query1);
				PreparedStatement ps2 = con.prepareStatement(query2)){
			ps1.setString(1, book.getIsbn());
			ps2.setString(1, book.getIsbn());
			int count1 = ps1.executeUpdate();
			int count2 = ps2.executeUpdate();
			logger.trace(count1 + " row/s updated in book_database");
			logger.trace(count2 + " row/s updated in reserved_books");
		}
		return book;
	}	
		
}*/
