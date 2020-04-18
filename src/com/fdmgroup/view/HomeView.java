package com.fdmgroup.view;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.HomeController;
import com.fdmgroup.controller.RegistrationController;
import com.fdmgroup.model.Librarian;
import com.fdmgroup.model.Student;
//import com.fdmgroup.dao.UserDaoJdbcImpl;
import com.fdmgroup.model.User;


public class HomeView {

	private Scanner scanner;
	private HomeController homeController;
	private AuthenticationController authenticationController;
	private RegistrationController  registrationController;
	private static Logger logger = LogManager.getLogger("ProjectLogger"); 
	
	public HomeView() {
		super();
	}
	
	public HomeView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	
	public HomeView(Scanner scanner, HomeController homeController, AuthenticationController authenticationController, RegistrationController registrationController) {
		super();
		this.scanner = scanner;
		this.homeController = homeController;
		this.authenticationController = authenticationController;
		this.registrationController = registrationController;
	}

	public void showInitialOptions(boolean showLogoutMessage){
		if (showLogoutMessage) {
			System.out.println("You logged out successfully.");
		}
		
		System.out.println("Welcome to my Solo Project Version 1.0.0");
		System.out.println("Please choose one of the options below:");
		System.out.println("1) Login");
		System.out.println("2) Register");
		System.out.println("3) Exit");
		System.out.println("-------------------------------");
		String userInput = scanner.nextLine();
		
		switch (userInput) {
		case "1":
			showLoginOptions(false);
			break;
		case "2":
			System.out.println("Welcome to the registration page! Please enter the details below");
			showRegistrationOptions();
			break;
		case "3":
			System.out.println("Thanks, Goodbye!");
			System.exit(0);
			break;
		default:
			System.out.println("The input is invalid.");
			showInitialOptions(false);
		}
	}

	public void showLoginOptions(boolean showError){
		if (showError) {
			System.out.println("Username/Password is wrong.");
		}
		
		System.out.println("Login");
		System.out.println("Please enter username: ");
		String username = scanner.nextLine();
		System.out.println("Please enter password: ");
		String password = scanner.nextLine();
		System.out.println("Please enter user type (librarian or student) : ");
		String userType = scanner.nextLine();

		//TODO add validation here
		
		authenticationController.login(username, password, userType);
	}
	
	public void showRegistrationOptions(){
		System.out.println("Would you like to register as a Librarian or Student?");
		String utype = scanner.nextLine();
		if(utype.equalsIgnoreCase("student")){
			System.out.println("Please enter your first name");
			String fname = scanner.nextLine();
			System.out.println("Please enter your last name");
			String lname = scanner.nextLine();
			System.out.println("Please enter your user name");
			String uname = scanner.nextLine();
			System.out.println("Please enter your password");
			String pwd = scanner.nextLine();
			System.out.println("Please enter your grade");
			String grade = scanner.nextLine();
			User user = new Student(uname, pwd, fname, lname, grade); //student object created so it will be added to student database
			logger.debug("registering");
			registrationController.register(user);	
		}
		else if(utype.equalsIgnoreCase("librarian")){
			System.out.println("Please enter your first name");
			String fname = scanner.nextLine();
			System.out.println("Please enter your last name");
			String lname = scanner.nextLine();
			System.out.println("Please enter your user name");
			String uname = scanner.nextLine();
			System.out.println("Please enter your password");
			String pwd = scanner.nextLine();
			System.out.println("Please enter your role");
			String role = scanner.nextLine();
			User user = new Librarian(uname, pwd, fname, lname, role); //student object created so it will be added to student database
			logger.debug("registering");
			registrationController.register(user);	
		}
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public HomeController getHomeController() {
		return homeController;
	}

	public void setHomeController(HomeController homeController) {
		this.homeController = homeController;
	}

	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	public RegistrationController getRegistrationController() {
		return registrationController;
	}

	public void setRegistrationController(RegistrationController registrationController) {
		this.registrationController = registrationController;
	}

	public void setAuthenticationController(AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}
}




















