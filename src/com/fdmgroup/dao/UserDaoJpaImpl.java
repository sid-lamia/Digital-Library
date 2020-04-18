package com.fdmgroup.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fdmgroup.model.Book;
import com.fdmgroup.model.Student;
import com.fdmgroup.model.User;
import com.fdmgroup.util.JpaUtility;

//methods in UserDaoJdbcImpl implemented using JPA


public class UserDaoJpaImpl implements IUserDao{

	//method for adding new user to database when user registers
	@Override
	public void create(User t){
		EntityManager em = JpaUtility.getInstance().getEntityManager(); 
		em.getTransaction().begin();
		em.persist(t); //adding user to student_database
		em.getTransaction().commit();
		em.close();		
	}

	
	@Override
	public User findById(int id) {
		return null;
	}

	// find a list of all usernames in the database
	@Override
	public List<String> findAllUsernames() {
		EntityManager em = JpaUtility.getInstance().getEntityManager(); 
		Query query = em.createNamedQuery("findAllUsernames");
		List<String> usernames = query.getResultList();
		return usernames;
	}

	@Override
	public User update(User t){
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void remove(User t){
		/*EntityManager em = JpaUtility.getInstance().getEntityManager(); 
		em.getTransaction().begin();
		User s = em.find(Student.class, t.getId());
		em.remove(s); //removing Student from user_database
		em.getTransaction().commit();
		em.close();*/
	}

	//find user from username
	@Override
	public User findByUsername(String username){
		EntityManager em = JpaUtility.getInstance().getEntityManager();
		Query query = em.createNamedQuery("findByUsername");
		query.setParameter(1, username);
		List<User> u = query.getResultList();
		if(u.size() != 0) {
			return u.get(0);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}
	
}
