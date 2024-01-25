<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search For Criminal Records</title>
</head>
<%@page import="com.aacra.auth.model.User" %>

<body>

<%
	User insertedUserData = (User) request.getSession().getAttribute("userData");
	String role = "";
	
	if (insertedUserData != null){
		String insertedRole = insertedUserData.getRole();
		role = (insertedRole == null) ? "" : insertedRole;;
	}
%>
	<form action="searchRecords" method="get">
	
	    <fieldset>
	        <legend>Personal Information</legend>
	        <label for="fullName">First Name:</label>
	        <input type="text" name="criminal_fname" id="criminal_fname" required><br><br>
	        
	        <label for="fullName">Last Name:</label>
	        <input type="text" name="criminal_lname" id="criminal_lname" required><br><br>
	        	
	        <label for="date_of_birth">Date of Birth:</label>
	        <input type="date" name="date_of_birth" id="date_of_birth"><br><br>
	
	        <label for="gender">Gender:</label>
	        <input type="text" name="gender" id="gender"><br><br>
	
	        <label for="race_ethnicity">Race/Ethnicity:</label>
	        <input type="text" name="race_ethnicity" id="race_ethnicity"><br><br>
	        
	        <%
	        if (!(role.equals("regular"))){
	        %>
	        
	        <label for="kebele_id">Kebele ID:</label>
	        <input type="text" name="kebele_id" id="kebele_id"><br><br>
	        
	        <label for="address">Address:</label>
	        <input type="text" name="address" id="address"><br><br>
	        
	        <label for="phone_number">Phone Number:</label>
	        <input type="text" name="phone_number" id="phone_number"><br><br>        
	        <%
	        }
	        %>
	        
	    </fieldset>
	    
	    <input type="submit" value="Search">
	</form>
</body>
</html>