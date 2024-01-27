<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Record Request</title>
</head>
<body>
<%
	String deleteProcedure = (String) request.getAttribute("deleteProcedure");
%>

<h1><%= deleteProcedure == null ? "" : deleteProcedure %></h1>
<br><br>
<a href="./MainPage.jsp">Return to Home</a>
</body>
</html>