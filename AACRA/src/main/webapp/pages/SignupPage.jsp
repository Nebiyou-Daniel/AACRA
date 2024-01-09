<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@page import="com.aacra.auth.model.User" %>
<body>

	<% 

	// To output what the user has already inserted upon loading
	User insertedUserData = (User) request.getAttribute("userData");
	String insertedConfirmPassword = (String) request.getAttribute("insertedSecondPassword");
	
	String fname = "";
	String lname = "";
	String email = "";
	String password = "";
	String confirmPassword = "";
	String role = "";
	
	if (insertedUserData != null){
		
		String insertedFname = insertedUserData.getFname();
		String insertedLname = insertedUserData.getLname();
		String insertedEmail = insertedUserData.getEmail();
		String insertedPassword = insertedUserData.getPassword();
		String insertedRole = insertedUserData.getRole();
	
		fname = (insertedFname == null) ? "" : insertedFname;
		lname = (insertedLname == null) ? "" : insertedLname;
		email = (insertedEmail == null) ? "" : insertedEmail;
		password = (insertedPassword == null) ? "" : insertedPassword;
		confirmPassword = (insertedConfirmPassword == null) ? "" : insertedConfirmPassword;
		role = (insertedRole == null) ? "" : insertedRole;;
	}
	%>
	
	
	<h1>SIGN UP</h1>
	<form action="signup" method="post">
		<label for="fname">First Name</label>
		<input type="text" id="fname" name="fname" size="20" autofocus required value="<%= fname%>"> <br>
		
		<span class="text-danger mb-1"><%=request.getAttribute("invalidFname")!= null ? request.getAttribute("invalidFname") : "" %></span><br>
		
		<label for="lname">Last Name</label>
		<input type="text" id="lname" name="lname" size="20" required value="<%= lname%>"> <br>
		
		<span class="text-danger mb-1"><%=request.getAttribute("invalidLname")!= null ? request.getAttribute("invalidLname") : "" %></span><br>
		
		<label for="email">Email</label>
		<input type="email" id="email" name="email" size="25" required value="<%= email%>"> <br><br>

		<span class="text-danger mb-1"><%=request.getAttribute("invalidEmail")!= null ? request.getAttribute("invalidEmail") : "" %></span><br>		
		
		<label for="password">Password</label>
		<input type="password" id="password" name="password" size="20" required value="<%=password%>"> <br>
		
		<span class="text-danger mb-1"><%=request.getAttribute("invalidPassword")!= null ? request.getAttribute("invalidPassword") : "" %></span><br>
				
		<label for="confirmPassword">Confirm Password</label>
		<input type="password" id="confirmPassword" name="confirmPassword" size="20" required value="<%= confirmPassword%>"> <br>
		
		<input type="checkbox" onclick="showPassword()" >Show Passwords <br><br>

		<span class="text-danger mb-1"><%=request.getAttribute("invalidSecondPassword")!= null ? request.getAttribute("invalidSecondPassword") : "" %></span><br>
				
		<label for="role">What is your job?</label>
		<select id="role" name="role">
	        <option value="regular" <%= "regular".equals(role) ? "selected" : "" %>>Regular User</option>
	        <option value="officer" <%= "officer".equals(role) ? "selected" : "" %>>Law Enforcement Officer</option>
	        <option value="analyst" <%= "analyst".equals(role) ? "selected" : "" %>>Crime Analyst</option>
		</select> <br><br>

		
		<input type="submit" value="Next">
		<input type="reset" value="Reset">
	</form>
	
	
	<script>
		function showPassword() {
			  var x = document.getElementById("password");
			  var y = document.getElementById("confirmPassword");
			  
			  if (x.type === "password") {
			    x.type = "text";
			    y.type = "text";
			  } else {
			    x.type = "password";
			    y.type = "password";
			  }
			}

	</script>
</body>
</html>