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

	
	
/****************************Insert Item**********************************/
	
	public String insertItem(String code, String name, String price, String desc) { 
	 	
		String output = ""; 
	 
	 	try 
	 	{ 
	 	 	Connection con = connect(); 
	 
	 	 	if (con == null) 
	 	 	{ 
	 	 	 	return "Error while connecting to the database"; 
	 	 	} 
	 
	 	 	// create a prepared statement 
	 	 	String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)" 
	 	 			+ " values (?, ?, ?, ?, ?)"; 
	 
	 	 	PreparedStatement preparedStmt = con.prepareStatement(query);  
	 	 	// binding values  	 	
	 	 	preparedStmt.setInt(1, 0);  	 	
	 	 	preparedStmt.setString(2, code);  	 	
	 	 	preparedStmt.setString(3, name); 
	 	 	preparedStmt.setDouble(4, Double.parseDouble(price));  	 	
	 	 	preparedStmt.setString(5, desc); 
	 

	 	//execute the statement 
	 	 	
	 	 	preparedStmt.execute(); 
	 	 	con.close(); 
	 	 	output = "Inserted successfully"; 
	 
	 
	} catch (Exception e) 
	{ 
	 	output = "Error while inserting";  	
	 	System.err.println(e.getMessage()); 
	} 
	 	return output; 

	
	}
	
	
	/****************************Read Item**********************************/
	public String readItems(){
		String output = ""; 
		
		try{ 
			Connection con = connect();
			
			if (con == null) {
				return"Error while connecting to the database for reading."; 
				} 
			// Prepare the html table to be displayed
			
			output = "<table border='1'><tr><th>Item Code</th>" 
			+"<th>Item Name</th>"
			+ "<th>Item Price</th>"
			+ "<th>Item Description</th>" 
			+ "<th>Update</th>"
			+ "<th>Remove</th></tr>"; 
			
			String query = "select * from items"; 
			
			Statement stmt = con.createStatement(); 
			
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			
			while (rs.next()) 
			{
				String itemID = Integer.toString(rs.getInt("itemID")); 
				String itemCode = rs.getString("itemCode"); 
				String itemName = rs.getString("itemName"); 
				String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
				String itemDesc = rs.getString("itemDesc"); 
				
				// Add a row into the html table
				output += "<tr><td>" + itemCode + "</td>"; 
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output+= "<td>"+ itemDesc+ "</td>";
				
				// buttons
						
				output += "<td><form method='post' action='updateItem.jsp'>"
						+ "<input name='btnUpdate' type='submit' value='Update'>"
						+ "<input name='itemID' type='hidden' value='" + itemID + "'>"
						+ "<input name='itemCode' type='hidden' value='" + itemCode + "'>"
						+ "<input name='itemName' type='hidden' value='" + itemName + "'>"
						+ "<input name='itemPrice' type='hidden' value='" + itemPrice + "'>"
						+ "<input name='itemDesc' type='hidden' value='" + itemDesc + "'>"
						+ "</form></td>";	
				
				
				output += "<td><form method='post' action='item.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'>"
						+ "<input name='itemID' type='hidden' value='" + itemID + "'>" 
						+ "</form></td></tr>"; 
				
			} con.close();
			
			// Complete the html table
			output += "</table>"; 
		} catch (Exception e)
		{ 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		}
		return output;
	}
	
	
	/****************************Delete Item**********************************/
	public String deleteItem(String itemID) {
		String output ="";
		
		try {
			
			Connection con = connect();
			if(con ==null)
			{
				return "Error while connecting to the database for deleting";
			}
			
			//create prepared statement
			String query = "DELETE FROM items WHERE itemID =?";
			PreparedStatement prepraredstmt = con.prepareStatement(query);
			
			//Binding values
			prepraredstmt.setInt(1, Integer.parseInt(itemID));
			
			//Execeute the statement
			prepraredstmt.execute();
			con.close();
			
			output = "Deleted Successfully";
			
		}catch(Exception e){
			
			output = "Error while deleteing the item";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	/****************************Update Item**********************************/
	
	/*
	 * public String updateItem(String itemCode, String itemName, String itemPrice,
	 * String itemDesc) {
	 * 
	 * String output="";
	 * 
	 * try {
	 * 
	 * Connection con = connect();
	 * 
	 * if(con == null) { return
	 * "Error while connecting to the database for updating"; }
	 * 
	 * //create prepared statement String query =
	 * "UPDATE items SET itemName=?,itemPrice=?,itemDesc=? " +
	 * "WHERE itemCode="+itemCode;
	 * 
	 * PreparedStatement preparedStmt = con.prepareStatement(query); // binding
	 * values preparedStmt.setString(1, itemName); preparedStmt.setDouble(2,
	 * Double.parseDouble(itemPrice)); preparedStmt.setString(3, itemDesc);
	 * 
	 * //execute the statement
	 * 
	 * preparedStmt.execute(); con.close(); output = "Update successfully";
	 * 
	 * }catch(Exception e) { output = "Error while updating";
	 * System.err.println(e.getMessage()); } return output;
	 * 
	 * }
	 */
	
	//public String updateItem(String itemCode, String itemName, String itemPrice, String itemDesc)
	public String updateItem(String itemID,String itemCode, String itemName, String itemPrice, String itemDesc) 
	{

		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}


						//String sql = "update items set itemName=?, itemPrice=?, itemDesc=?" + "where itemCode=? ";
						String sql = "UPDATE items SET itemCode=?, itemName=?, itemPrice=?, itemDesc=?" + "where itemID=?";

						PreparedStatement preparedStmt = con.prepareStatement(sql);


						//preparedStmt.setString(3, itemName);
						//preparedStmt.setDouble(4, Double.parseDouble(itemPrice));
						//preparedStmt.setString(5, itemDesc);
						preparedStmt.setString(1, itemCode);
						preparedStmt.setString(2, itemName);
						preparedStmt.setDouble(3, Double.parseDouble(itemPrice));
						preparedStmt.setString(4, itemDesc);
						preparedStmt.setInt(5, Integer.parseInt(itemID));
						


						preparedStmt.execute();
						con.close();
						
						output = "Updated successfully";
			
		}catch(Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return output;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
