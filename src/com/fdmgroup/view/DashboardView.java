package com.fdmgroup.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.fdmgroup.controller.AdvancedSearchController;
import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.ProfileController;
import com.fdmgroup.controller.SearchController;
import com.fdmgroup.controller.UpdateDatabaseController;
import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.model.UserSession;

public class DashboardView {

	private Scanner scanner;
	private AuthenticationController authenticationController;
	private SearchController searchController;
	private AdvancedSearchController asc;
	private UpdateDatabaseController udc;
	private ProfileController pc;

	public ProfileController getPc() {
		return pc;
	}

	public void setPc(ProfileController pc) {
		this.pc = pc;
	}

	public void showDashboardStudent(){
		System.out.println("Welcome " + UserSession.getLoggedInUser().getFirstname() + " " + UserSession.getLoggedInUser().getLastname() + "!");
		System.out.println("Please select one of the options below: ");
		System.out.println("1) Logout");
		System.out.println("2) Search Database");
		System.out.println("3) Advanced Search");
		System.out.println("4) Show all books");
		System.out.println("5) Cancel Reservation");
		System.out.println("6) Show Profile");
		String userInput = scanner.nextLine();
		
		switch (userInput) {
		case "1":
			authenticationController.logout();
			break;
		case "2":
			System.out.println("Enter book title");
			String title = scanner.nextLine();
			searchController.search(title);
			showDashboardStudent();
			break;  
		case "3": 
			System.out.println("advanced search");
			asc.showAdvancedSearch();
			showDashboardStudent();
			break; 
		case "4": 
			searchController.showAll();
			showDashboardStudent();
			break;
		case "6":
			pc.showProfile();
			showDashboardStudent();
			break;
		default:
			System.out.println("The input was invalid.");
			showDashboardStudent();
		}
	}

	public UpdateDatabaseController getUdc() {
		return udc;
	}

	public void setUdc(UpdateDatabaseController udc) {
		this.udc = udc;
	}

	public void showDashboardLibrarian(){
		System.out.println("Welcome " + UserSession.getLoggedInUser().getFirstname() + " " + UserSession.getLoggedInUser().getLastname() + "!");
		System.out.println("Please select one of the options below: ");
		System.out.println("1) Logout");
		System.out.println("2) Search Database");
		System.out.println("3) Advanced Search");
		System.out.println("4) Show all books");
		System.out.println("5) Add books to database");
		System.out.println("6) Remove books from database");
		System.out.println("7) Update existing book");
		System.out.println("8) Remove students from the database");
		System.out.println("9) Cancel reservation");
		System.out.println("10) Show Profile");

		String userInput = scanner.nextLine();
		
		switch (userInput) {
		case "1":
			authenticationController.logout();
			break;
		case "2": 
			System.out.println("Enter book title");
			String title = scanner.nextLine();
			searchController.search(title);
			showDashboardLibrarian();
			break;   
		case "3": 
			System.out.println("advanced search");
			asc.showAdvancedSearch();
			showDashboardLibrarian();
			break;  
		case "4": 
			searchController.showAll();
			showDashboardLibrarian();
			break;
		case "5": 
			udc.addBook(); 
			showDashboardLibrarian();
			break; 
		case "6":
			udc.deleteBook();
			showDashboardLibrarian();
			break;
		case "7": 
			udc.updateBook();
			showDashboardLibrarian();
			break;
		case "8":
			udc.deleteStudent();
			showDashboardLibrarian();
			break;
		case "10":
			pc.showProfile();
			showDashboardLibrarian();
			break;
		default:
			System.out.println("The input was invalid.");
			showDashboardLibrarian();
		}
	}
	
	public DashboardView() {
		super();
	}
	
	public DashboardView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public SearchController getSearchController() {
		return searchController;
	}

	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}

	public DashboardView(Scanner scanner, AuthenticationController authenticationController, IBookDao bookDao,
			SearchController searchController, AdvancedSearchController asc, UpdateDatabaseController udc, ProfileController pc) {
		super();
		this.scanner = scanner;
		this.authenticationController = authenticationController;
		this.searchController = searchController;
		this.asc = asc;
		this.udc = udc;
		this.pc = pc;
	}
	
	public AdvancedSearchController getAsc() {
		return asc;
	}

	public void setAsc(AdvancedSearchController asc) {
		this.asc = asc;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	public void setAuthenticationController(AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}

}













