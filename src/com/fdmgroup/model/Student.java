package com.fdmgroup.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_DATABASE")
public class Student extends User{
	private String grade;

	public Student() {
		super();
	}

	public Student(String username, String password, String firstname, String lastname, String grade) {
		super(username, password, firstname, lastname);
		this.grade = grade;
	}

	public Student(int id, String username, String password, String firstname, String lastname, String grade) {
		super(id, username, password, firstname, lastname);
		this.grade = grade;
	}

	public Student(int id, String firstname, String lastname) {
		super(id, firstname, lastname);
	}

	@Override
	public String toString() {
		return "Student [grade=" + grade + "]";
	}
	

}

