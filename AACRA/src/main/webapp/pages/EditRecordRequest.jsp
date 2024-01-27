<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit your Request for a Restricted Record</title>
</head>
<%@page import="com.aacra.auth.model.User" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<body>
	<h2>Request for Restricted Records of <%= request.getAttribute("fname")%> <%= request.getAttribute("lname")%></h2>
    <%
		User userData = (User) request.getSession().getAttribute("userData");
		int userId = userData.getUserId();
			
        ResultSet rs = (ResultSet) request.getAttribute("resultSet");
        try {
	
            while (rs.next()) {
%>
	<form action="editRecordRequest?request_id=<%= rs.getInt("request_id")%>" method="get">
		<fieldset>
			<legend>Business</legend>
					    
		    <p>If you have a business and you are planning to use the information you would receive for
		    the sake of your business, please fill in the form directly below it</p>
		    
		    <label for="enableCheckbox">Do you have a business?</label>
		    <input type="checkbox" id="enableCheckbox" onclick="enableTextInput()"> <br><br>
		    <input type="hidden"  id="for_business" name="for_business" value="<%= rs.getBoolean("for_business")%>">
		    
		    <label for="business_name">What is your business' name?</label>
		    <input type="text" id="business_name" name="business_name" value="<%= rs.getString("business_name")%>" <%= rs.getBoolean("for_business") ? "" : "disabled" %>> <br><br>
		    
		    <label for="business_address">What is your business' address (center of work/ headquarters)?</label>
		    <input type="text" id="business_address" name="business_address" value="<%= rs.getString("business_address")%>" <%= rs.getBoolean("for_business") ? "" : "disabled" %>> <br><br>		
		</fieldset>
			<br><br>
		
		    <label for="textarea">What is your reason for requesting the records of this 
		    particular individual? What do you hope to accomplish with it?</label> <br>
		    <textarea id="textarea" name="reason" rows="8" cols="100"><%= rs.getString("reason")%></textarea> <br><br>
		    <input type="hidden" name="request_id" value="<%= rs.getInt("request_id")%>">
	    	<input type="hidden" name="criminal_id" value="<%= rs.getInt("criminal_id")%>">
<%
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>		    

		<input type="hidden" name="user_id" value="<%= userId%>">
	    <input type="hidden" name="fname" value="<%= request.getAttribute("fname")%>">	    
	    <input type="hidden" name="lname" value="<%= request.getAttribute("lname")%>">
	    	    
        <input type="submit" value="Edit">
	</form>
	
	<script>	
    function enableTextInput() {
        var businessName = document.getElementById("business_name");
        var forBusiness = document.getElementById("for_business");
		var businessAddress = document.getElementById("business_address");
        var enableCheckbox = document.getElementById("enableCheckbox");

        businessName.disabled = !enableCheckbox.checked;
        businessAddress.disabled = !enableCheckbox.checked;
        forBusiness.value = enableCheckbox.checked;
    }
</script>
</body>
</html>