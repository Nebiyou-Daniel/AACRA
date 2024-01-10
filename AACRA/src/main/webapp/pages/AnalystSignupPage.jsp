<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crime Analyst Sign up</title>
</head>
<%@page import="com.aacra.auth.model.User" %>
<body>
 
 <%
	User insertedUserData = (User) request.getAttribute("userData");
 %>
 
    <h2>Police Officer Form</h2>
    
    <p>Please insert below which station in the city you work at. </p>
    
    <form action="analystSignup" method="post" enctype="multipart/form-data">
        <label for="workLocation">What organization/department do you work for?<br>
        (If you are an independent analyst at the moment, type 'None' into the space provided)</label>
		<input type="text" id="workLocation" name="workLocation" size="30" required>
        
        <br><br>
        
        <label for="analystCertification">Do you have a form of certification as an analyst?<br>
         If so, click the upload button. </label>
        <input type="file" name="analystCertification" id="analystCertification" accept="image/*">
        
        <br><br>
        
        <input type="hidden" name="fname" value="<%= insertedUserData.getFname()%>">
        <input type="hidden" name="lname" value="<%= insertedUserData.getLname()%>">
        <input type="hidden" name="email" value="<%= insertedUserData.getEmail()%>">
        <input type="hidden" name="password" value="<%= insertedUserData.getPassword()%>">
        <input type="hidden" name="role" value="<%= insertedUserData.getRole()%>">
        
        
        <input type="submit" value="Next">
    </form>
</body>
</html>