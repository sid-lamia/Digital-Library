package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fdmgroup.util.IdGenerator;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "LIBRARY_USER")
@NamedQueries({
	@NamedQuery(name = "findByUsername", query = "Select u from User u where u.username = ?1"),
	@NamedQuery(name = "findAllUsernames", query = "Select u.username from User u")
	
})
public class User implements IStorable {
	@Id
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public User() {
		super();
	}

	public User(String username, String password, String firstname, String lastname) {
		this(IdGenerator.generate(), username, password, firstname, lastname);
	}
	
	public User(int id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public User(int id, String username, String password, String firstname, String lastname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ",  firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
