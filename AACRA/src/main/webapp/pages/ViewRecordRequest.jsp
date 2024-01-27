<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your request for restricted information</title>
<%@ page import="java.sql.ResultSet" %>
<%@page import="com.aacra.auth.model.User" %>
<%@ page import="java.sql.SQLException" %>
</head>
<style>
        /* Styling for the dimmed background */
    .overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        justify-content: center;
        align-items: center;
        z-index: 1;
    }

    /* Styling for the alert box */
    .alert-box {
        background-color: white;
        padding: 20px;
        border-radius: 5px;
        text-align: center;
    }
</style>
<body>
<%
		User insertedUserData = (User) request.getSession().getAttribute("userData");
	
		String role = "";
		
		if (insertedUserData != null){
			String insertedRole = insertedUserData.getRole();
			role = (insertedRole == null) ? "" : insertedRole;;
		}
		
        ResultSet rs = (ResultSet) request.getAttribute("resultSet");
		int requestId = 0;
		String fname = "none";
		String lname = "none";
		String isPending = "pending";
		
        try {
            while (rs.next()) {
            	requestId = rs.getInt("request_id");
            	fname = rs.getString("criminal_fname");
            	lname = rs.getString("criminal_lname");
            	isPending = rs.getString("status");
%>
		<h2>Your request for restricted information about '<%= rs.getString("criminal_fname")%> <%= rs.getString("criminal_lname")%>'</h2>
		
<% 
				if (rs.getBoolean("for_business")){
					
%>		    
		<fieldset>
			<legend>Business </legend>
		    <label for="business_name">Business Name</label>
		    <input type="text" id="business_name" name="business_name" readonly value="<%= rs.getString("business_name")%>"> <br><br>
		    
		    <label for="business_address">Business Address</label>
		    <input type="text" id="business_address" name="business_address" readonly value="<%= rs.getString("business_address")%>"> <br><br>		
		</fieldset>
			<br><br>
		
<%
				}
%>
		    <label for="textarea">Reason for requesting for restricted information:</label> <br>
		    <textarea id="textarea" name="reason" rows="6" cols="50" readonly><%= rs.getString("reason")%></textarea> <br><br>
<%
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>	
	
	    <% if(role.equals("regular")){ 
        %>
		<a href="editRecordRequestSetup?request_id=<%= requestId%>&fname=<%= fname%>&lname=<%= lname%>">EDIT my Record Request</a> <br><br>
		<button onclick="showConfirmation()">DELETE my Record Request</button>
         <%
	    }
		 if(role.equals("admin") && isPending.equals("pending")){ 
        %>
		<a href="processRecordRequest?request_id=<%= requestId%>&status=approved">APPROVE REQUEST</a> <br>
		<a href="processRecordRequest?request_id=<%= requestId%>&status=rejected">REJECT REQUEST</a>		
         <%
       	 }
        %>
        
        
     <div class="overlay" id="overlay">
        <div class="alert-box">
            <p>Are you sure you want to delete this criminal's record?</p>
            <button onclick="confirmAction(true, <%= requestId%>)">YES</button>
            <button onclick="confirmAction(false, <%= requestId%>)">NO</button>
        </div>
    </div>
    <script>
    function showConfirmation() {
        // Show the overlay and center the alert box
        document.getElementById('overlay').style.display = 'flex';
    }
    
    function confirmAction(isConfirmed, requestId) {
        // Hide the overlay
        document.getElementById('overlay').style.display = 'none';

        if (isConfirmed) {
            // Perform the action when 'OK' is clicked
        	window.location.href = 'deleteRecordRequest?request_id=' + requestId;
        } else {
            // Handle the action when 'Cancel' is clicked
        }
    }
    </script>
</body>
</html>