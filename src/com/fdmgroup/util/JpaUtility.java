package com.fdmgroup.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtility {
	private static JpaUtility instance;
	private EntityManagerFactory emf; 
	
	private JpaUtility(){
		emf = Persistence.createEntityManagerFactory("ELibrary");
	}
	
	public static JpaUtility getInstance(){
		if(instance == null){
			instance = new JpaUtility();
		}
		return instance;
	}
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	public void close(){
		emf.close();
	}

}
