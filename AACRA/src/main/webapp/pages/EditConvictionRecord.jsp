<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Conviction Record</title>
</head>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<body>

    <%
        ResultSet rs = (ResultSet) request.getAttribute("resultSet");
		
        try {
	
            while (rs.next()) {
%>
		<form action="editConvictionRecord" method="get">
		<fieldset>
	        <legend>Conviction Information</legend>
	        <label for="date_of_conviction">Date of Conviction:</label>
	        <input type="date" name="date_of_conviction" id="date_of_conviction" required value="<%= rs.getDate("date_of_conviction")%>"><br>
	
	        <label for="convicted_offenses">Convicted Offenses:</label>
	        <input type="text" name="convicted_offenses" id="convicted_offenses" required value="<%= rs.getString("convicted_offenses")%>"><br>
	
	        <label for="sentencing_details">Sentencing Details:</label>
	        <input type="text" name="sentencing_details" id="sentencing_details" required value="<%= rs.getString("sentencing_details")%>"><br>
	        
	        <label for="probation_parole_status">Probation/Parole Status:</label>
	        <input type="text" name="probation_parole_status" id="probation_parole_status" required value="<%= rs.getString("probation_parole_status")%>"><br>
	    </fieldset>
	    
	    <input type="hidden" name="criminal_id" value="<%= request.getParameter("criminal_id")%>">
	    <input type="hidden" name="conviction_record_id" value="<%= rs.getInt("conviction_record_id")%>">   
	    
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