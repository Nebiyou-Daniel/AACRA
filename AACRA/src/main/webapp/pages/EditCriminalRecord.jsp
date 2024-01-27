<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Criminal Record</title>
</head>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<body>

    <%
        ResultSet rs = (ResultSet) request.getAttribute("resultSet");
		
        try {
	
            while (rs.next()) {
%>
	<form action="editCriminalRecord" method="get">
	
	    <fieldset>
	        <legend>Personal Information</legend>
	        <label for="fullName">First Name:</label>
	        <input type="text" name="criminal_fname" id="criminal_fname" required value="<%= rs.getString("criminal_fname")%>"><br><br>
	        
	        <label for="fullName">Last Name:</label>
	        <input type="text" name="criminal_lname" id="criminal_lname" required value="<%= rs.getString("criminal_lname")%>"><br><br>
	        	
	        <label for="date_of_birth">Date of Birth:</label>
	        <input type="date" name="date_of_birth" id="date_of_birth" required value="<%= rs.getDate("date_of_birth")%>"><br><br>
	
	        <label for="gender">Gender:</label>
	        <input type="text" name="gender" id="gender" required value="<%= rs.getString("gender")%>"><br><br>
	
	        <label for="race_ethnicity">Race/Ethnicity:</label>
	        <input type="text" name="race_ethnicity" id="race_ethnicity" required value="<%= rs.getString("race_ethnicity")%>"><br><br>
	        
	        <label for="kebele_id">Kebele ID:</label>
	        <input type="text" name="kebele_id" id="kebele_id" required value="<%= rs.getString("kebele_id")%>"><br><br>
	        
	        <label for="address">Address:</label>
	        <input type="text" name="address" id="address" required value="<%= rs.getString("address")%>"><br><br>
	        
	        <label for="phone_number">Phone Number:</label>
	        <input type="text" name="phone_number" id="phone_number" required value="<%= rs.getString("phone_number")%>"><br><br>        
	    </fieldset>
	    
	    <input type="hidden" name="criminal_id" value="<%= rs.getInt("criminal_id")%>">
	    
	    <input type="submit" value="Submit">
	</form>

<%
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
</body>
</html>