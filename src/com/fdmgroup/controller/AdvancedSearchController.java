package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;
import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.model.Book;
import com.fdmgroup.view.AdvancedSearchView;

public class AdvancedSearchController {
	private IBookDao bookDao;
	private AdvancedSearchView asv; 
	private List<Book> searchResult = new ArrayList<>(); 

	
	public void showAdvancedSearch(){
		asv.advancedSearchOption();
	}
		
	
	public void advancedSearch(String option, String criteria){
		switch(option){
		case "1": 
			searchResult = bookDao.findByAuthor(criteria);
			if(searchResult.size() !=0){
				asv.showSearchResult(searchResult); 
				return;
			}
			else {
				System.out.println("No such book in database");
				return;
			}
		case "2": 
			searchResult = bookDao.findByGenre(criteria);
			if(searchResult.size() !=0){
				asv.showSearchResult(searchResult); 
				return;
			}
			else {
				System.out.println("No such book in database");
				return;
			}
		case "3": 
			searchResult = bookDao.findByKeyword(criteria);
			if(searchResult.size() !=0){
				asv.showSearchResult(searchResult); 
				return;
			}
			else {
				System.out.println("No such book in database");
				return;
			}
		}
	}
	

	@Override
	public String toString() {
		return "SearchController [bookDao=" + bookDao + ", searchResult=" + searchResult + "]";
	}

	public AdvancedSearchController() {
		super();
	}

	public AdvancedSearchController(IBookDao bookDao, List<Book> searchResult, AdvancedSearchView asv) {
		super();
		this.bookDao = bookDao;
		this.searchResult = searchResult;
		this.asv = asv;
	}

	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Book> searchResult) {
		this.searchResult = searchResult;
	}


	public AdvancedSearchView getAsv() {
		return asv;
	}


	public void setAsv(AdvancedSearchView asv) {
		this.asv = asv;
	}


}