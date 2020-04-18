package com.fdmgroup.controller;

import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.model.Book;
import com.fdmgroup.model.User;

public class ReserveController {

	private IBookDao bookDao; 
	
	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}


	public void reserveBook(Book book, User user){
		bookDao.reserve(book, user);
	}
	public ReserveController() {
		super();
	}

	
}
