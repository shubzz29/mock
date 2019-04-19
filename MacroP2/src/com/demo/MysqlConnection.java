package com.demo;
import java.sql.*;
public class MysqlConnection {
 static Connection con=null;
	public static Connection getConnection(){
	       Connection conn = null;

	       try {
	    	 //  System.out.println ("enter");
	           String userName = "wtl";
	           String password = "Pass@123";
	           String url = "jdbc:mysql://localhost:3306/login";
	           Class.forName ("com.mysql.jdbc.Driver");
	         //  System.out.println ("hiii");
	           conn = DriverManager.getConnection (url, userName, password);
	           System.out.println ("connected");

	       } catch (Exception e) {
	           System.out.println ("Cannot connect to database server");
	       }
	       return conn;
	}
}
