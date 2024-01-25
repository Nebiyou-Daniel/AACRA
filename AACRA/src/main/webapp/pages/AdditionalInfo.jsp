<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Other Info</title>
</head>
<%@page import="com.aacra.record.model.CriminalPersonalRecord" %>

<%
// To output what the user has already inserted upon loading
	
	String fname = (String) request.getAttribute("fname");
	String lname = (String) request.getAttribute("lname");


%>
<body>
	<h2>Record related to <%= fname %> <%= lname%> has been added!</h2> <br><br>
	<a href="AddArrestRecord.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>&fname=<%= fname%>&lname=<%= lname%>">Add Arrest Record for <%= fname %> <%= lname%></a> <br><br>
	<a href="AddConvictionRecord.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>&fname=<%= fname%>&lname=<%= lname%>">Add Conviction Record for <%= fname %> <%= lname%></a> <br><br>
	
	<a href="MainPage.jsp">Home</a>
</body>
</html>