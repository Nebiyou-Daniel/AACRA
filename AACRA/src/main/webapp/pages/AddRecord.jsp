<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a New Criminal Record</title>
    <style>
        fieldset {
            margin: 20px 50px;
            padding: 20px;
        }
    </style>
</head>
<body>

	<form action="addRecord" method="get">
	
	    <fieldset>
	        <legend>Personal Information</legend>
	        <label for="fullName">First Name:</label>
	        <input type="text" name="criminal_fname" id="criminal_fname" required><br><br>
	        
	        <label for="fullName">Last Name:</label>
	        <input type="text" name="criminal_lname" id="criminal_lname" required><br><br>
	        	
	        <label for="date_of_birth">Date of Birth:</label>
	        <input type="date" name="date_of_birth" id="date_of_birth" required><br><br>
	
	        <label for="gender">Gender:</label>
	        <input type="text" name="gender" id="gender" required><br><br>
	
	        <label for="race_ethnicity">Race/Ethnicity:</label>
	        <input type="text" name="race_ethnicity" id="race_ethnicity" required><br><br>
	        
	        <label for="kebele_id">Kebele ID:</label>
	        <input type="text" name="kebele_id" id="kebele_id" required><br><br>
	        
	        <label for="address">Address:</label>
	        <input type="text" name="address" id="address" required><br><br>
	        
	        <label for="phone_number">Phone Number:</label>
	        <input type="text" name="phone_number" id="phone_number" required><br><br>        
	    </fieldset>
	    
	    <input type="submit" value="Submit">
	</form>
</body>
</html>