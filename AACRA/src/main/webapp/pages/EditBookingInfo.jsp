<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Booking Information</title>
</head>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<body>

    <%
        ResultSet rs = (ResultSet) request.getAttribute("resultSet");
		
        try {
	
            while (rs.next()) {
%>
	<form action="addBookingInfo" method="post" enctype="multipart/form-data">
		<fieldset>
	        <legend>Booking Information</legend>
	        <label for="booking_number">Booking Number:</label>
	        <input type="text" name="booking_number" id="booking_number" required value="<%= rs.getString("booking_number")%>"><br>
	
	        <label for="mugshot">Mugshot:</label>
	        <input type="file" name="mugshot" id="mugshot" accept="image/*"><br>
	    </fieldset>
	    
	    <input type="hidden" name="arrest_record_id" value="<%= rs.getInt("arrest_record_id")%>">
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