package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.model.Book;
import com.fdmgroup.view.SearchView;

public class SearchController {
	private IBookDao bookDao;
	private SearchView searchView; 
	private List<Book> searchResult = new ArrayList<>(); 
	
	public void search(String title){
		searchResult = bookDao.findByTitle(title); 
		if(searchResult.size() !=0){
			searchView.showSearchResult(searchResult); 
			return;
		}
		else {
			System.out.println("No such book in database");
			return;
		}
	}
	public void showAll() {
		searchResult = bookDao.findAll();
		searchView.showSearchResult(searchResult);
		return;
	}
	
	@Override
	public String toString() {
		return "SearchController [bookDao=" + bookDao + ", searchResult=" + searchResult + "]";
	}

	public SearchController() {
		super();
	}

	public SearchController(IBookDao bookDao, List<Book> searchResult, SearchView searchView) {
		super();
		this.bookDao = bookDao;
		this.searchResult = searchResult;
		this.searchView = searchView;
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
	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

}
