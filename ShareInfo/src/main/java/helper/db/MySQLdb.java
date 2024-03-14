package helper.db;

import java.util.*;
import java.sql.*;

import helper.info.*;

public class MySQLdb {
	private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root"; 
    private String passwd = "root";
	private String driver = "com.mysql.jdbc.Driver";
	
    private Connection conn;
	
	public MySQLdb() {
		try {
        	Class.forName(this.driver).newInstance();
        	this.conn = DriverManager.getConnection(this.url,this.user,this.passwd);
           	} catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
	}

	public void setUserInfo(String email, String password, String username) {
		String query = "INSERT INTO shareinfo.users VALUES ('" + email + "', '" + password + "', '" + username + "');";
		System.out.println("     DB query: " + query);
    	try {
	    	Statement st = this.conn.createStatement();
	    	st.executeUpdate(query);  	
        } catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
	}
	
	public String getUsername(String email, String password) {
		String query = "SELECT username FROM shareinfo.users WHERE email='" + email + "' AND password='" + password + "';";
		System.out.println("     DB query: " + query);
		String username = null;
    	try {
	    	Statement st = this.conn.createStatement();
	    	ResultSet res = st.executeQuery(query); 
	    	while(res.next()) {
	    		username = res.getString("username");
        	}
        } catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
    	return username;
	}
	
	public void setMessageInfo(String message, String username) {
		String query = "INSERT INTO shareinfo.messages VALUES ('0', '" + message + "', '" + username + "');";
		System.out.println("     DB query: " + query);
    	try {
	    	Statement st = this.conn.createStatement();
	    	st.executeUpdate(query);  	
        } catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
	}
	
	public ArrayList<MessageInfo> getAllMessages() {
		String query = "SELECT * FROM shareinfo.messages;";
		System.out.println("     DB query: " + query);
		ArrayList<MessageInfo> messageInfoList = new ArrayList<MessageInfo>(); 
    	try {
	    	Statement st = this.conn.createStatement();
        	ResultSet res = st.executeQuery(query); 
        	while(res.next()) {
        		messageInfoList.add(new MessageInfo(res.getString("message"), res.getString("username")));
        	} 	
        } catch(Exception e) {
        	System.out.println("Exception: " + e.getMessage());
    	}
    	return messageInfoList;
	}
	
}