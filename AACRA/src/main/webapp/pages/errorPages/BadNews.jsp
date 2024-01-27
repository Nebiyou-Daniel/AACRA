<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BAD NEWS!</title>
</head>
<body>
<%
	String requestExists = (String) request.getAttribute("requestExists");
%>

<h1><%= requestExists == null ? "" : requestExists %></h1>
<br><br>
<a href="./MainPage.jsp">Return to Home</a>
</body>
</html>