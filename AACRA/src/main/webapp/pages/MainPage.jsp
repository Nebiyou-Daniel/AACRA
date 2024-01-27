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
	String role = "";
	
	if (insertedUserData != null){
		
		String insertedFname = insertedUserData.getFname();
		String insertedLname = insertedUserData.getLname();
		String insertedRole = insertedUserData.getRole();
	
		fname = (insertedFname == null) ? "" : insertedFname;
		lname = (insertedLname == null) ? "" : insertedLname;
		role = (insertedRole == null) ? "" : insertedRole;;
	}
	%>
	    <header>
        <h1>Criminal Records Management System</h1>
    </header>
    
    <nav>
        <ul>
            <li><a href="Search.jsp">Search Records</a></li>

        <% if(role.equals("admin")){ 
        %>
            <li><a href="AddRecord.jsp">Add New Record</a></li>
           	<li><a href="viewAllRecordRequests">View Record Requests from Users</a></li> 
         <%
        } else {
        	%>
            <li><a href="viewYourRequests?userId=<%= insertedUserData.getUserId()%>">View Record Requests</a></li>        	
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