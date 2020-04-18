package com.fdmgroup.controller;

import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.Book;
import com.fdmgroup.model.User;
import com.fdmgroup.view.UpdateDatabaseView;

public class UpdateDatabaseController {
	private UpdateDatabaseView udv;
	private IBookDao bookDao;
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public UpdateDatabaseController() {
		super();
	}

	public UpdateDatabaseController(UpdateDatabaseView udv, IBookDao bookDao, IUserDao userDao) {
		super();
		this.udv = udv;
		this.bookDao = bookDao;
		this.userDao = userDao;
	}

	public UpdateDatabaseView getUdv() {
		return udv;
	}

	public void setUdv(UpdateDatabaseView udv) {
		this.udv = udv;
	}

	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void addBook(){
		Book newBook = udv.showAddOptions();
		bookDao.create(newBook); 
		//System.out.println(newBook.getTitle() + " has been added to the database");
		return;
	}

	public void deleteBook(){
		Book deletedBook = udv.showDeleteOptions();		
		bookDao.remove(deletedBook); 
		//System.out.println(deletedBook.getTitle() + " has been removed from the database");
		return;
	}

	public void updateBook(){
		Book updatedBook = udv.showUpdateOptions();
		bookDao.update(updatedBook);
		//System.out.println(updatedBook.getTitle() + " has been updated"); 
		return;
	}

	public void deleteStudent() {
		User deletedStudent = udv.showStudentDeleteOptions();
		userDao.remove(deletedStudent);
		return;
	}
}
