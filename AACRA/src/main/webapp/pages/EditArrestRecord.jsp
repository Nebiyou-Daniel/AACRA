<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Arrest Record</title>
</head>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<body>
    <%
        ResultSet rs = (ResultSet) request.getAttribute("resultSet");
		
        try {
	
            while (rs.next()) {
%>
	
	<form action="editArrestRecord" method="get">
		<fieldset>
	        <legend>Arrest Information</legend>
	        <label for="date_time_arrest">Date and Time of Arrest:</label>
	        <input type="datetime-local" name="date_time_arrest" id="date_time_arrest" required value="<%= rs.getObject("date_time_arrest")%>"><br>
	
	        <label for="arresting_agency">Arresting Agency:</label>
	        <input type="text" name="arresting_agency" id="arresting_agency" required value="<%= rs.getString("arresting_agency")%>"><br>

	        <label for="arresting_officer">Arresting Officer:</label>
	        <input type="text" name="arresting_officer" id="arresting_officer" required value="<%= rs.getString("arresting_officer")%>"><br>
	       	
	        <label for="charges">Charges:</label>
	        <input type="text" name="charges" id="charges" required value="<%= rs.getString("charges")%>"><br>
	     
	     	<label for="arrest_location">Arrest Location:</label>
	        <input type="text" name="arrest_location" id="arrest_location" required value="<%= rs.getString("arrest_location")%>"><br>
	    </fieldset>
	    
	    <input type="hidden" name="criminal_id" value="<%= rs.getInt("criminal_id")%>">   
	    <input type="hidden" name="arrest_record_id" value="<%= rs.getInt("arrest_record_id")%>">   
	    
	    <input type="submit" value="EDIT">

	</form>
<%
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
</body>
</html>