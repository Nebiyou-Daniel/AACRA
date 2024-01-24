<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<%@page import="com.aacra.auth.model.User" %>
<body>
	<% 

	// To output what the user has already inserted upon loading
	User insertedUserData = (User) request.getSession().getAttribute("userData");
	
	String fname = "";
	String lname = "";
	String email = "";
	String password = "";
	String role = "";
	
	if (insertedUserData != null){
		
		String insertedFname = insertedUserData.getFname();
		String insertedLname = insertedUserData.getLname();
		String insertedEmail = insertedUserData.getEmail();
		String insertedPassword = insertedUserData.getPassword();
		String insertedRole = insertedUserData.getRole();
	
		fname = (insertedFname == null) ? "" : insertedFname;
		lname = (insertedLname == null) ? "" : insertedLname;
		email = (insertedEmail == null) ? "" : insertedEmail;
		password = (insertedPassword == null) ? "" : insertedPassword;
		role = (insertedRole == null) ? "" : insertedRole;;
	}
	%>
	    <header>
        <h1>Criminal Records Management System</h1>
    </header>
    
    <nav>
        <ul>
            <li><a href="/pages/Search.jsp">Search Records</a></li>
        <% if(role.equals("admin")){
   
        %>
            <li><a href="AddRecord.jsp">Add New Record</a></li>
            <li><a href="ViewAllRecords">View All Records</a></li>
           	<li><a href="viewRequests">View Requests from Users</a></li> 
         <%
        }
         %>
        </ul>
    </nav>

    <main>
        <section>
            <h2>Welcome to the Criminal Records Management System</h2>
            <p>This system allows you to manage and maintain criminal records efficiently.</p>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Criminal Records Management System. All rights reserved.</p>
    </footer>
</body>
</html>