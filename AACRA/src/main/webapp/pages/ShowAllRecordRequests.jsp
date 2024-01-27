<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All the record requests</title>
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
<%@page import="com.aacra.auth.model.User" %>

<body>
<%
		User insertedUserData = (User) request.getSession().getAttribute("userData");
	
		String role = "";
		
		if (insertedUserData != null){
			String insertedRole = insertedUserData.getRole();
			role = (insertedRole == null) ? "" : insertedRole;;
		}
	    if(!(role.equals("admin"))){ 
        %>
		<h2>Here are all the requests you have made for restricted records. Click the name of the criminal to see details.</h2>
		
         <%
	    } else {
	    %>
		<h2>Here are all the requests made by users for restricted records. Click the name of the criminal to see details.</h2>	    
	    <%	
	    }

        %>
	
			<table style="width:100%">
    <%
        ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");

    	Boolean isThereData = false;
		String noData = "THERE ARE NO REQUESTS MADE TO ACCESS RESTRICTED RECORDS OF A CRIMINAL!";
		
        try {
	
            while (resultSet.next()) {
            	isThereData= true;
%>
			  <tr>
			    <td style="background-color: burlywood; font-weight: 600; font-family: verdana">Criminal: 
			    	<a href="viewRecordRequest?user_id=<%= resultSet.getInt("user_id")%>&criminal_id=<%= resultSet.getInt("criminal_id")%>">
			    		<%= resultSet.getString("criminal_fname")%> <%= resultSet.getString("criminal_lname")%></a></td>
			    
			    <td style="font-family: verdana; font-weight: 600;">Status: <%= resultSet.getString("status") %></td>
			    <td style="font-family: verdana; font-weight: 600;">Reason: <%= resultSet.getString("reason") %></td>

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