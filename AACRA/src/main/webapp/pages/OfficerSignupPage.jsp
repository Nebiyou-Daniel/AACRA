<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Law Enforcement Officer Sign up</title>
</head>
<%@page import="com.aacra.auth.model.User" %>
<body>
 
 <%
	User insertedUserData = (User) request.getAttribute("userData");
 %>
 
    <h2>Police Officer Form</h2>
    
    <p>Please insert below which station in the city you work at. </p>
    
    <form action="officerSignup" method="post" enctype="multipart/form-data">
        <label for="workLocation">Subcity you are employed at:</label>
        <select name="workLocation" id="workLocation">
        	<option>Choose a subcity</option>
        	<option value="Addis Ketema">Addis Ketema</option>
        	<option value="Akaky Kaliti">Akaky Kaliti</option>
        	<option value="Arada">Arada</option>
            <option value="Bole">Bole</option>
            <option value="Gullele">Gullele</option>
            <option value="Kirkos">Kirkos</option>
        	<option value="Kolfe Keranio">Kolfe Keranio</option>
        	<option value="Lideta">Lideta</option>
        	<option value="Nifas Silk Lafto">Nifas Silk Lafto</option>
        	<option value="Yeka">Yeka</option>    
        </select>
        
        <br>
        
        <label for="wereda">Wereda:</label>
		<input type="number" id="wereda" name="wereda" min="1" max="16">
		
        <br><br>
        
        <label for="officerIdPhoto">Upload your police ID:</label>
        <input type="file" name="officerIdPhoto" id="officerIdPhoto" accept="image/*">
        
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