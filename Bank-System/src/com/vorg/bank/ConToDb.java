package com.vorg.bank;

import java.sql.*;
import java.util.logging.Logger;

public  class ConToDb {

	private static final Logger log = Logger.getLogger(ConToDb.class.getName());

	String URL = "jdbc:mysql://localhost:3306/bank_db";
	String username = "root";
	String password = "root";
	String driver = "com.mysql.cj.jdbc.Driver";
	
	Connection c=null;

	public void connect() {

		log.info("-------connecting to sql----------");

		try {
			Class.forName(driver);
			c=(Connection)DriverManager.getConnection(URL, username, password);
			
			
		} catch (Exception e) {

			log.info(e.toString());

		}

	}

}
