package com.fdmgroup.view;

import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserSession;

	
public class ProfileView {
	private UserSession user;
	private IBookDao bookDao;
	public void showProfileView() {	
		User currentUser = user.getLoggedInUser();
		System.out.println(currentUser.getFirstname() + " " + currentUser.getLastname());
		System.out.println("You currently have " + bookDao.findByUser(currentUser).size() + " books with you.");
		System.out.println("These are the books currently reserved by you:");
		bookDao.findByUser(currentUser).forEach(System.out::println);

	}
	public IBookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}
	public ProfileView(IBookDao bookDao) {
		super();
		this.bookDao = bookDao;
	}
	

}
