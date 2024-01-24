<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Other Info</title>
</head>
<%@page import="com.aacra.record.model.CriminalPersonalRecord" %>
<%@page import="java.sql.Date" %>

<%
// To output what the user has already inserted upon loading
	CriminalPersonalRecord cpr = (CriminalPersonalRecord) request.getAttribute("criminalData");
	
	String fname = cpr.getFname();
	String lname = cpr.getLname();
	Date email = cpr.getDateOfBirth();
	String gender = cpr.getGender();
	String raceAndEthnicity = cpr.getRaceAndEthnicity();
	String kebeleId = cpr.getKebeleId();
	String address = cpr.getAddress();
	String phoneNumber = cpr.getPhoneNumber();

%>
<body>
	<h2>Record related to <%= fname %> <%= lname%> has been added!</h2> <br><br>
	<a href="AddArrestRecord.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>&fname=<%= fname%>&lname=<%= lname%>">Add Arrest Record for <%= fname %> <%= lname%></a> <br><br>
	<a href="AddBookingInfo.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>&fname=<%= fname%>&lname=<%= lname%>">Add Booking Information for <%= fname %> <%= lname%></a> <br><br>
	<a href="AddConvictionRecord.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>&fname=<%= fname%>&lname=<%= lname%>">Add Conviction Record for <%= fname %> <%= lname%></a> <br><br>
	
	<a href="MainPage.jsp">Home</a>
</body>
</html>