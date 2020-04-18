package com.fdmgroup.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.fdmgroup.controller.AdvancedSearchController;
import com.fdmgroup.controller.ReserveController;
import com.fdmgroup.model.Book;

public class AdvancedSearchView {
	private Scanner scanner;
	private AdvancedSearchController asc;
	private ReserveController resCont; 

	public ReserveController getResCont() {
		return resCont;
	}
	public void setResCont(ReserveController resCont) {
		this.resCont = resCont;
	}
	public AdvancedSearchView(Scanner scanner, AdvancedSearchController asc) {
		super();
		this.scanner = scanner;
		this.asc = asc;
	}
	public AdvancedSearchView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	public AdvancedSearchController getAsc() {
		return asc;
	}
	public void setAsc(AdvancedSearchController asc) {
		this.asc = asc;
	}
	public void advancedSearchOption(){
		System.out.println("Choose your search criteria");
		System.out.println("1) Search by author");
		System.out.println("2) Search by genre");
		System.out.println("3) Search by keyword");
		String option = scanner.nextLine();
 
		switch(option){
		case "1": 
			System.out.println("Enter the author's name");
			String author = scanner.nextLine(); 
			asc.advancedSearch("1", author);
			break;
		case "2":
			System.out.println("Enter the genre");
			String genre = scanner.nextLine();
			asc.advancedSearch("2", genre);
			break; 
		case "3": 
			System.out.println("Enter the keyword");
			String keyword = scanner.nextLine();
			asc.advancedSearch("3", keyword);
			break;
		default:
			System.out.println("Invalid input");
			advancedSearchOption();
		}
	}
	public void showSearchResult(List<Book> searchResult){
		for (Book book : searchResult) {
			System.out.println(book);
			System.out.println("Enter 1 to reserve a book and 2 to continue"); 
			String input = scanner.nextLine();
			switch(input){
			case "1": 
				if(book.getAvailability().equals("Available")){
				//resCont.reserveBook(book, currentUser); 
				}
				else {
					System.out.println("This book is not available to reserve");
				}
				break; 
			case "2": 
				break;
			}
		}
		return;	
	}
	
	
}
