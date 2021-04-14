package com;
import java.sql.*;

public class Item {

/**********************DB Connection****************************/
	
	public Connection connect() 
	{ 
	 	Connection con = null; 
	 	 	 
	        try  
	        { 
	         	Class.forName("com.mysql.jdbc.Driver"); 
	         	con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_2021", 
	 	 	 	 	 	 	 	 	 	"root", "");    
	 
	 	 	//For testing 
	         	System.out.print("Successfully connected");    
	        } 
	        catch(Exception e)  
	        { 
	         	e.printStackTrace();          
	        }        
	       return con; 
	} 

	
}
