package com.fdmgroup.controller;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.User;
import com.fdmgroup.view.HomeView;
import com.fdmgroup.view.RegistrationView;

public class RegistrationController {
	private RegistrationView registrationView;
	private HomeView homeView;
	private IUserDao userDao;
	private static Logger logger = LogManager.getLogger("ProjectLogger"); 

	public void register(User user){
		logger.debug("creating user");
		List<String> usernames = userDao.findAllUsernames();
		if(usernames.contains(user.getUsername())){
			System.out.println("This username already exists! Please select a different username");
			homeView.showRegistrationOptions();
		}
		else{
			userDao.create(user);
			logger.debug("created");
			registrationView.showRegistrationView();
		}
	}
	
	public RegistrationView getRegistrationView() {
		return registrationView;
	}
	public void setRegistrationView(RegistrationView registrationView) {
		this.registrationView = registrationView;
	}
	public HomeView getHomeView() {
		return homeView;
	}
	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public RegistrationController() {
		super();
	}
	public RegistrationController(RegistrationView registrationView, HomeView homeView) {
		super();
		this.registrationView = registrationView;
		this.homeView = homeView;
	}
}
