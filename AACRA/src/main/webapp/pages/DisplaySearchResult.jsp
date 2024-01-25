<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search results for: <%= request.getParameter("criminal_fname") %> <%= request.getParameter("criminal_lname") %></title>
	<style>
	table, th, td {
	  border: 3px solid black;
	  border-collapse: separate;
	  text-align: center;	
	}
	
	tr {
	  border-collapse: collapse;
	}
	
	</style>
</head>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<body>
			<table style="width:100%">
    <%
        ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");
		Boolean isThereData = false;
		String noData = "THERE IS NO CRIMINAL WITH THE GIVEN DATA!";
		
        try {
	
            while (resultSet.next()) {
            	isThereData= true;
%>
			  <tr>
			    <td style="background-color: burlywood; font-weight: 900; font-family: verdana"><a href="showCriminalRecord?criminal_id=<%= resultSet.getInt("criminal_id")%>"> <%= resultSet.getString("criminal_fname").toUpperCase() %> <%= resultSet.getString("criminal_lname").toUpperCase() %></a></td>
			    <td style="font-family: verdana; font-weight: 900;"><%= resultSet.getString("gender") %></td>
			    <td style="font-family: verdana; font-weight: 900;"><%= resultSet.getString("race_ethnicity") %></td>
			    <td style="font-family: verdana; font-weight: 600; ">Date of Birth: <%= resultSet.getDate("date_of_birth") %></td>

			  </tr>

<%
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>	
			</table>

		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereData ? "" : noData %></h1>
</body>
</html>