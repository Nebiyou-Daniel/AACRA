<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<%@page import="com.aacra.auth.model.User" %>
<body>

	<% 

	// To output what the user has already inserted upon loading
	User insertedUserData = (User) request.getAttribute("userData");
	
	String email = "";
	String password = "";
	
	if (insertedUserData != null){

		String insertedEmail = insertedUserData.getEmail();
		String insertedPassword = insertedUserData.getPassword();

		email = (insertedEmail == null) ? "" : insertedEmail;
		password = (insertedPassword == null) ? "" : insertedPassword;
	}
	%>
	
	
	<h1>LOGIN</h1>
	<form action="login" method="post">
	
		<label for="email">Email</label>
		<input type="email" id="email" name="email" size="25" required value="<%= email%>"> <br><br>

		<span class="text-danger mb-1"><%=request.getAttribute("incorrectEmail")!= null ? request.getAttribute("incorrectEmail") : "" %></span><br>		
		
		<label for="password">Password</label>
		<input type="password" id="password" name="password" size="20" required value="<%=password%>"> <br>
		
		<span class="text-danger mb-1"><%=request.getAttribute("incorrectPassword")!= null ? request.getAttribute("incorrectPassword") : "" %></span><br>

		<input type="checkbox" onclick="showPassword()" >Show Password <br><br>

		
		<input type="submit" value="Login">
		<input type="reset" value="Reset">
	</form>
	
	
	<script>
		function showPassword() {
			  var x = document.getElementById("password");
			  
			  if (x.type === "password") {
			    x.type = "text";
			  } else {
			    x.type = "password";
			  }
			}

	</script>
</body>
</html>