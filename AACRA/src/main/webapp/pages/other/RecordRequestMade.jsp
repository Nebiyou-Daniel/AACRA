<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String requestMade = (String) request.getAttribute("requestMade");
%>

<h1><%= requestMade == null ? "" : requestMade %></h1>
<br><br>
<a href="./MainPage.jsp">Return to Home</a>
</body>
</html>