<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Booking Information for Criminal: <%= request.getParameter("fname") %> <%= request.getParameter("lname") %></title>
</head>
<body>

	<form action="addBookingInfo" method="post">
		<fieldset>
	        <legend>Booking Information</legend>
	        <label for="booking_number">Booking Number:</label>
	        <input type="text" name="booking_number" id="booking_number" required><br>
	
	        <label for="mugshot">Mugshot:</label>
	        <input type="file" name="mugshot" id="mugshot" accept="image/*" required><br>
	    </fieldset>
	    <input type="submit" value="Submit">

	</form>
</body>
</html>