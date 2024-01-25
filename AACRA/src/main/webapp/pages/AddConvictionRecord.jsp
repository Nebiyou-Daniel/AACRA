<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Conviction Record for Criminal: <%= request.getParameter("fname") %> <%= request.getParameter("lname") %></title>
</head>
<body>

	<form action="addConvictionRecord" method="post">
		<fieldset>
	        <legend>Conviction Information</legend>
	        <label for="date_of_conviction">Date of Conviction:</label>
	        <input type="date" name="date_of_conviction" id="date_of_conviction" required><br>
	
	        <label for="convicted_offenses">Convicted Offenses:</label>
	        <input type="text" name="convicted_offenses" id="convicted_offenses" required><br>
	
	        <label for="sentencing_details">Sentencing Details:</label>
	        <input type="text" name="sentencing_details" id="sentencing_details" required><br>
	        
	        <label for="probation_parole_status">Probation/Parole Status:</label>
	        <input type="text" name="probation_parole_status" id="probation_parole_status" required><br>
	    </fieldset>
	    
	    <input type="hidden" name="criminal_id" value="<%= request.getParameter("criminal_id")%>">
	    <input type="hidden" name="fname" value="<%= request.getParameter("fname")%>">
	    <input type="hidden" name="lname" value="<%= request.getParameter("lname")%>">
	    
	    <input type="submit" value="Submit">
	    
	</form>
</body>
</html>