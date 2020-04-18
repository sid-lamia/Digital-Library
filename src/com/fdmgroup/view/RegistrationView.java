package com.fdmgroup.view;

import java.util.Scanner;
import com.fdmgroup.controller.HomeController;
import com.fdmgroup.controller.RegistrationController;

public class RegistrationView {
	private HomeController homeController = new HomeController(); 
	private RegistrationController registrationController;
	private Scanner scanner;

	
	public void showRegistrationView() {
		System.out.println("Congratulations! You are now a registered user of this E-Library.");
		System.out.println("To continue, please select one of the following options:");
		System.out.println("1) Home ");
		System.out.println("2) Exit ");
		
		String userInput = scanner.nextLine();
		
		switch (userInput) {
		case "1":
			homeController.showHome();
			break;
		case "2":
			System.out.println("Thanks, Goodbye!");
			System.exit(0);
			break;
		default:
			System.out.println("The input is invalid.");
			showRegistrationView();
		}
	}

	public RegistrationController getRegistrationController() {
		return registrationController;
	}

	public void setRegistrationController(RegistrationController registrationController) {
		this.registrationController = registrationController;
	}

	public RegistrationView(Scanner scanner) {
		super();
		this.scanner = scanner;
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
	
}
