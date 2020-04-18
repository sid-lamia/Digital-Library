package com.fdmgroup.controller;

import com.fdmgroup.view.ProfileView;

public class ProfileController {
	private ProfileView pv;
	
	public void showProfile(){
		pv.showProfileView();
	}

	public ProfileController() {
		super();
	}

	public ProfileController(ProfileView pv) {
		super();
		this.pv = pv;
	}

	public ProfileView getPv() {
		return pv;
	}

	public void setPv(ProfileView pv) {
		this.pv = pv;
	}
}
