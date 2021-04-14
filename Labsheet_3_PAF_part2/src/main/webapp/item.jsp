<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.Item"%> 

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
<form method="post" action="item.jsp"> 
 	 	Item code: <input name="itemCode" type="text"><br>  	 	
 	 	Item name: <input name="itemName" type="text"><br>  
 	 	Item price: <input name="itemPrice" type="text"><br>  
 	 	Item description: <input name="itemDesc" type="text"><br>  
 	 	<input name="btnSubmit" type="submit" value="Save"> 
 	</form> 


</body>
</html>