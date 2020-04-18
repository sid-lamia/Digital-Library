/*package com.fdmgroup.dao;

import java.sql.*;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.model.Librarian;
import com.fdmgroup.model.Student;
import com.fdmgroup.model.User;
import com.fdmgroup.util.DataSource;

public class UserDaoJdbcImpl implements IUserDao {
	//jdbc implememtation goes here

	@Override
	//Method called when a user registers to the site, the user is created and added to the database
	public void create(User t) throws SQLException {
		String query1 = "INSERT INTO STUDENT_DATABASE(ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME) VALUES (?,?,?,?,?)";

		try(Connection con = DataSource.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(query1); 
				){
			stmt.setInt(1, t.getId());
			stmt.setString(2, t.getUsername());
			stmt.setString(3, t.getPassword());
			stmt.setString(4, t.getFirstname());
			stmt.setString(5, t.getLastname());
			
			int rows = stmt.executeUpdate();
			System.out.println(rows + "row/s inserted successfully");
			}
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User t) {
		return null;
	}

	@Override
	public void remove(User t) {
		// TODO Auto-generated method stub
	}

	@Override
	//called when user wants to log in, finds user from student or librarian database and returns details
	public User findByUsername(String userName, String userType) throws SQLException {
		String query1 = "SELECT * FROM STUDENT_DATABASE WHERE USERNAME = ?";
		String query2 = "SELECT * FROM LIBRARIAN_DATABASE WHERE USERNAME = ?";
		User u = null;
		
		if(userType.equalsIgnoreCase("student")){
		try(Connection con = DataSource.getInstance().getConnection(); 
			PreparedStatement stmt = con.prepareStatement(query1);){
				stmt.setString(1, userName);
				ResultSet rs = stmt.executeQuery();	
				
				while(rs.next()) { 
					int id = rs.getInt(1);
					String username = rs.getString(2);
					String pwd = rs.getString(3);
					String firstname = rs.getString(4);
					String lastname = rs.getString(5);
				u = new Student(id, username, pwd, firstname, lastname);
				}
				return u; 
			}		
		}
		else if (userType.equalsIgnoreCase("librarian")){
			try(Connection con = DataSource.getInstance().getConnection(); 
					PreparedStatement stmt = con.prepareStatement(query2);){
						stmt.setString(1, userName);
						ResultSet rs = stmt.executeQuery();	
						
						while(rs.next()) {
							int id = rs.getInt(1);
							String username = rs.getString(2);
							String pwd = rs.getString(3);
							String firstname = rs.getString(4);
							String lastname = rs.getString(5);
						u = new Librarian(id, username, pwd, firstname, lastname);
						}
						return Optional.of(u); 
					}		
				}
		return Optional.empty();
		}

	@Override
	public List<User> findByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/