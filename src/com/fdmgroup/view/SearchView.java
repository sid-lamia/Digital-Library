package com.fdmgroup.view;

import java.util.List;
import java.util.Scanner;

import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.ReserveController;
import com.fdmgroup.controller.SearchController;
import com.fdmgroup.model.Book;

public class SearchView {
	private Scanner scanner;
	private SearchController searchController; 
	private ReserveController resCont;
	
	public ReserveController getResCont() {
		return resCont;
	}
	public void setRescont(ReserveController resCont) {
		this.resCont = resCont;
	}
	public SearchView() {
		super();
	}
	public SearchController getSearchController() {
		return searchController;
	}
	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}
	public SearchView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
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
