<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.Item"%> 


<% 
//Insert item---------------------------------- 
if (request.getParameter("itemCode") != null) {
	Item itemObj = new Item(); 
	String stsMsg = itemObj.insertItem(request.getParameter("itemCode"), 
			request.getParameter("itemName"), 
			request.getParameter("itemPrice"),      
			request.getParameter("itemDesc")); 
	
	session.setAttribute("statusMsg", stsMsg); } 	 
%> 

<%
//Delete item----------------------------------
if(request.getParameter("itemID") != null){
	Item itemObj = new Item();
	String stsMsg = itemObj.deleteItem(request.getParameter("itemID"));
	session.setAttribute("statusMsg", stsMsg);
}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Management</title>
</head>
<body>

<%
if (request.getParameter("itemCode") == null)  
{ 	 
 	Item itemObj = new Item(); 
 	itemObj.connect();//For testing the connect method 
} 

%>


<!-- Form to capture data -->
	<form method="post" action="item.jsp"> 
 	 	Item code: <input name="itemCode" type="text"><br>  	 	
 	 	Item name: <input name="itemName" type="text"><br>  
 	 	Item price: <input name="itemPrice" type="text"><br>  
 	 	Item description: <input name="itemDesc" type="text"><br>  
 	 	<input name="btnSubmit" type="submit" value="Save"> 
 	</form> 
	<% 
 	 	out.print(session.getAttribute("statusMsg")); 
 	%> 
 	<br>
 	
<!-- Java code to call readItems() --> 	
 	<%
 	Item itemObj = new Item(); 
 	out.print(itemObj.readItems()); 
 	%>




</body>
</html>