<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Arrest Record for Criminal: <%= request.getParameter("fname") %> <%= request.getParameter("lname") %></title>
</head>
<body>

	<form action="addArrestRecord" method="get">
		<fieldset>
	        <legend>Arrest Information</legend>
	        <label for="date_time_arrest">Date and Time of Arrest:</label>
	        <input type="datetime-local" name="date_time_arrest" id="date_time_arrest" required><br>
	
	        <label for="arresting_agency">Arresting Agency:</label>
	        <input type="text" name="arresting_agency" id="arresting_agency" required><br>

	        <label for="arresting_officer">Arresting Officer:</label>
	        <input type="text" name="arresting_officer" id="arresting_officer" required><br>
	       	
	        <label for="charges">Charges:</label>
	        <input type="text" name="charges" id="charges" required><br>
	     
	     	<label for="arrest_location">Arrest Location:</label>
	        <input type="text" name="arrest_location" id="arrest_location" required><br>
	    </fieldset>
	    
	    <input type="hidden" name="criminal_id" value="<%= request.getParameter("criminal_id")%>">
	    <input type="hidden" name="fname" value="<%= request.getParameter("fname")%>">
	    <input type="hidden" name="lname" value="<%= request.getParameter("lname")%>">	    
	    
	    <input type="submit" value="Next">

	</form>
</body>
</html>