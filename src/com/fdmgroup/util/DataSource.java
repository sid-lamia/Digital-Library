package com.fdmgroup.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {
//singleton class because we need only one object for connection pool 
	
	private static DataSource ds; 
	private ComboPooledDataSource cpds; 
	private DataSource(){
		cpds = new ComboPooledDataSource(); 
		FileReader reader = null;
		Properties properties = null;
		try {
			reader = new FileReader("DbConfig.properties");
			properties = new Properties(); 
			properties.load(reader); 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		//connection set up 

		cpds.setJdbcUrl(properties.getProperty("db.connection.url"));
		cpds.setUser(properties.getProperty("db.user"));
		cpds.setPassword(properties.getProperty("db.password"));
		
		//connection pool settings 
		cpds.setMinPoolSize(5); //default 3 
		cpds.setMaxPoolSize(20); //defautl 15 
		cpds.setAcquireIncrement(5); //default 3
		cpds.setMaxStatements(10); // size of prepared statement cache, default = 0
		
		
	}
	
	public static DataSource getInstance(){ 
		if(ds == null){
		ds = new DataSource();
		}
		return ds; 
	}
	public Connection getConnection() throws SQLException { 
		return cpds.getConnection(); 
	}
}
