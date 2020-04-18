package com.fdmgroup.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARIAN_DATABASE")
public class Librarian extends User{
	@Override
	public String toString() {
		return "Librarian [role=" + role + "]";
	}
	private String role;
	
	public Librarian() {
		super();
	}
	public Librarian(String username, String password, String firstname, String lastname, String role) {
		super(username, password, firstname, lastname);
		this.role = role;
	}
	public Librarian(int id, String username, String password, String firstname, String lastname, String role) {
		super(id, username, password, firstname, lastname);
		this.role = role;
	}
		
}