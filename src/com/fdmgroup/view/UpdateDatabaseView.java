package com.fdmgroup.view;

import java.util.Scanner;
import com.fdmgroup.controller.UpdateDatabaseController;
import com.fdmgroup.model.Book;
import com.fdmgroup.model.Student;
import com.fdmgroup.model.User;

public class UpdateDatabaseView {
	private UpdateDatabaseController udc;
	private Scanner scanner;
	

	public UpdateDatabaseController getUdc() {
		return udc;
	}

	public void setUdc(UpdateDatabaseController udc) {
		this.udc = udc;
	}

	public UpdateDatabaseView() {
		super();
	}

	public UpdateDatabaseView(Scanner scanner) {
		super();
		this.scanner = scanner;
	} 
		
	public Book showAddOptions(){ 
		System.out.println("Please enter the ISBN");
		int isbn = Integer.parseInt(scanner.nextLine());
		System.out.println("Please enter book title");
		String title = scanner.nextLine();
		System.out.println("Please enter the author's name");
		String author = scanner.nextLine();
		System.out.println("Please enter the genre");
		String genre = scanner.nextLine();
		System.out.println("Please enter the number of pages");
		int pages = Integer.parseInt(scanner.nextLine());
		System.out.println("Please enter the availability");
		String availability = scanner.nextLine();
		System.out.println("Please enter the publishing year");
		String year = scanner.nextLine();
		Book book = new Book(isbn, title, author, genre, pages, availability, year);
		return book;
	}
	
	public Book showDeleteOptions(){
		System.out.println("Please enter the ISBN");
		int isbn = Integer.parseInt(scanner.nextLine());
		System.out.println("Please enter book title");
		String title = scanner.nextLine();
		Book book = new Book(); //error, fix
		return book;
	}
	
	public Book showUpdateOptions(){
		System.out.println("Please enter the ISBN");
		int isbn = Integer.parseInt(scanner.nextLine());
		System.out.println("Please enter book title");
		String title = scanner.nextLine();
		Book book = new Book(); //error, fix
		return book;
		
	}

	public User showStudentDeleteOptions() {
		System.out.println("Please enter the student ID");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Please enter the student's first name");
		String fname = scanner.nextLine();
		System.out.println("Please enter the student's last name");
		String lname = scanner.nextLine();

		User student = new Student(id, fname, lname);
		return student;
	}
	
	
	
}
