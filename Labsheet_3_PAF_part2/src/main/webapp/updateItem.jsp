<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@page import="com.Item"%> 
<!DOCTYPE html>


<%-- 
<%
if (request.getParameter("itemCode") != null)
{
	Item itemObj = new Item();
			
	String stsMsg = itemObj.updateItem(request.getParameter("itemCode"),
	request.getParameter("itemName"),
	request.getParameter("itemPrice"),
	request.getParameter("itemDesc"));
	
	
	session.setAttribute("statusMsg", stsMsg); 

}


%> --%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Item</title>
</head>
<body>


<%
	String itemID = request.getParameter("itemID");
	String itemCode = request.getParameter("itemCode");
	String itemName = request.getParameter("itemName");
	double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
	String itemDesc = request.getParameter("itemDesc");
%>

<h1>Item Update Management</h1>

<form method = "post" action ="item.jsp">
<!-- 	Item ID: <input name="itemId" type="text"  ><br> -->
	Item ID: <input name="itemID" type='hidden'   value = "<%= itemID%>"><br>
	Item code: <input name="itemCode" type="text"   value = "<%= itemCode %>"><br>
	Item name: <input name="itemName" type="text"   value = "<%= itemName %>" ><br> 
	Item price: <input name="itemPrice" type="text" value = "<%= itemPrice %>"><br>
    Item description: <input name="itemDesc" type="text"  value = "<%= itemDesc %>" ><br> 
    <input name="btnUpdate" type="submit" value="Update">

</form>



<%
if (request.getParameter("itemCode") != null)
{
	Item itemObj = new Item();
	
	String stsMsg = itemObj.updateItem(request.getParameter("itemID"),
	request.getParameter("itemCode"),
	request.getParameter("itemName"),
	request.getParameter("itemPrice"),
	request.getParameter("itemDesc"));
	session.setAttribute("statusMsg", stsMsg);
}

%>

<%
	out.print(session.getAttribute("statusMsg"));
%>
 
 	<%
 	Item itemObj1 = new Item(); 
 	out.print(itemObj1.readItems()); 
 	%>
 
 
 
</body>
</html>