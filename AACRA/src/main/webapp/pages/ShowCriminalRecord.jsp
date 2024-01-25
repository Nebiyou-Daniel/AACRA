<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <%@ page import="java.util.ArrayList" %>
  <%@page import="com.aacra.auth.model.User" %>
  <%@ page import="com.aacra.record.model.ArrestRecord" %>
  <%@ page import="com.aacra.record.model.BookingRecord" %>  
  <%@ page import="com.aacra.record.model.ConvictionRecord" %>
  <%@ page import="com.aacra.record.model.CriminalPersonalRecord" %>
<% 
	CriminalPersonalRecord cpr = (CriminalPersonalRecord) request.getAttribute("criminalPersonalRecord");
	ArrayList<ArrestRecord> ar = (ArrayList<ArrestRecord>) request.getAttribute("arrestRecords");
	ArrayList<BookingRecord> bi = (ArrayList<BookingRecord>) request.getAttribute("bookingRecords");
	ArrayList<ConvictionRecord> cr = (ArrayList<ConvictionRecord>) request.getAttribute("convictionRecords");
	
	User insertedUserData = (User) request.getSession().getAttribute("userData");
	String fname = "";
	String lname = "";
	String role = "";
	if (insertedUserData != null){
		
		String insertedFname = insertedUserData.getFname();
		String insertedLname = insertedUserData.getLname();
		String insertedRole = insertedUserData.getRole();
	
		fname = (insertedFname == null) ? "" : insertedFname;
		lname = (insertedLname == null) ? "" : insertedLname;
		role = (insertedRole == null) ? "" : insertedRole;;
	}
%>
<title>Records of <%= cpr.getFname()%> <%= cpr.getLname()%></title>
  <style>
    .content {
      display: none;
    }

    .active {
      display: block;
    }

    .button-container {
      text-align: center;
    }

    .button {
      padding: 10px 20px;
      margin: 5px;
      cursor: pointer;
    }
    table {
        border-collapse: collapse;
        width: 50%;
        margin-top: 20px;
    }
    th, td {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }
    th {
        background-color: #f2f2f2;
    }
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
  <script>
    document.addEventListener('DOMContentLoaded', function() {
      // Get all buttons and content elements
      var buttons = document.querySelectorAll('.button');
      var contents = document.querySelectorAll('.content');

      // Add click event listener to each button
      buttons.forEach(function(button, index) {
        button.addEventListener('click', function() {
          // Hide all contents
          contents.forEach(function(content) {
            content.classList.remove('active');
          });

          // Show the corresponding content
          contents[index].classList.add('active');
        });
      });
    });
  </script>

</head>
<body>
  <div class="nav">
	  <ul>
	  	<li><a href="Search.jsp">Search for another Criminal Record</a></li>
		<% 		
		if(role.equals("regular")){  
        %>
	  	<li><a href="RequestForRestrictedRecord.jsp">Make Request for Restricted Record</a></li>
         <%
        }
         %>
	  </ul>
  </div>
  <hr>
  
  <div class="button-container">
    <button class="button">Background Information</button>
    <button class="button">Arrest Records</button>
    <button class="button">Booking Information</button>
    <button class="button">Conviction Records</button>
  </div>

  <div class="content active">
	  <table>
	    <tr>
	        <th>Category</th>
	        <th>Data</th>
	    </tr>
	
	    <!-- Personal Information -->
	    <tr>
	        <td>Full Name:</td>
	        <td><%= cpr.getFname()%> <%= cpr.getLname()%></td>
	    </tr>
	    <tr>
	        <td>Date of Birth:</td>
	        <td><%= cpr.getDateOfBirth()%></td>
	    </tr>
	    <tr>
	        <td>Gender:</td>
	        <td><%= cpr.getGender()%></td>
	    </tr>
	    <tr>
	        <td>Race/Ethnicity:</td>
	        <td><%= cpr.getRaceAndEthnicity()%></td>
	    </tr>
	    <tr>
	        <td>Kebele ID:</td>
	        <td><%= cpr.getKebeleId()%></td>
	    </tr>
	    <tr>
	        <td>Address:</td>
	        <td><%= cpr.getAddress()%></td>
	    </tr>	    
	    <tr>
	        <td>Phone Number:</td>
	        <td><%= cpr.getPhoneNumber()%></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:yellow;"><a href="">EDIT RECORD</a></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="showPersonalConfirmation()">DELETE RECORD</button></td>
	    </tr>	    
	</table>
	
  </div>
  
  <div class="content">
  		<% 		
		if(role.equals("admin")){  
        %>
	  	<a href="editArrestRecordSetup?criminal_id=<%= request.getAttribute("criminal_id")%>&fname=<%= cpr.getFname()%>&lname=<%= cpr.getLname()%>">Add New Arrest Record for <%= cpr.getFname()%> <%= cpr.getLname()%></a>
         <%
        }
         %>
  <%
	Boolean isThereArrestData = false;
	String noArrestData = cpr.getFname() + " " + cpr.getLname() + " HAS NO KNOWN ARREST RECORD";
	
  	int arrestCount = 1;
  	for (ArrestRecord arrestRecord: ar){
  		isThereArrestData = true;
  		%>
  		<h2>Arrest Record <%= arrestCount%></h2>
  	  <table>
	    <tr>
	        <th>Category</th>
	        <th>Data</th>
	    </tr>
	
	    <!-- Arrest Records -->
	    <tr>
	        <td>Date and Time of Arrest:</td>
	        <td><%= arrestRecord.getDateTimeArrest()%></td>
	    </tr>
	    <tr>
	        <td>Arresting Agency:</td>
	        <td><%= arrestRecord.getArrestingAgency()%></td>
	    </tr>
	    <tr>
	        <td>Arresting Officer/s:</td>
	        <td><%= arrestRecord.getArrestingOfficer()%></td>
	    </tr>
	    <tr>
	        <td>Charges:</td>
	        <td><%= arrestRecord.getCharges()%></td>
	    </tr>
	    <tr>
	        <td>Arrest Location:</td>
	        <td><%= arrestRecord.getArrestLocation()%></td>
	    </tr>	    
	    <tr>
	        <td colspan="2" style="background-color:yellow;"><a href="editArrestRecordSetup?arrest_record_id=<%= arrestRecord.getArrestRecordId()%>">EDIT RECORD</a></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="showArrestConfirmation()">DELETE RECORD</button></td>
	    </tr>
	</table>
	
  		
  		<%
  		arrestCount++;
  	}
  %>
  		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereArrestData ? "" : noArrestData %></h1>
  </div>
  <div class="content">
    	<% 		
		if(role.equals("admin")){  
        %>
	  	<p>If you wish to add a new booking record for <%= cpr.getFname()%> <%= cpr.getLname()%>, you will first need set an arrest record. <a href="AddArrestRecord.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>">CLICK HERE</a></p>
         <%
        }
         %>
    <%
	Boolean isThereBookingData = false;
	String noBookingData = cpr.getFname() + " " + cpr.getLname() + " HAS NO KNOWN BOOKING RECORD";
	
  	int bookingCount = 1;
  	for (BookingRecord bookingInfo: bi){
  		isThereBookingData = true;

  		%>
  		<h2>Booking Record/Information <%= bookingCount%></h2>
  	  <table>
	    <tr>
	        <th>Category</th>
	        <th>Data</th>
	    </tr>
	
	    <!-- Booking Information -->
	    <tr>
	        <td>Booking Number:</td>
	        <td><%= bookingInfo.getBookingNumber()%></td>
	    </tr>
	    <tr>
	        <td>Mugshot:</td>
	        <td><img alt="Criminal's Photo"></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:yellow;"><a href="">EDIT RECORD</a></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="showBookingConfirmation()">DELETE RECORD</button></td>
	    </tr>
	</table>
  		<%
  		bookingCount++;
  	}
  %>
  		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereBookingData ? "" : noBookingData %></h1>
  </div>
  
  <div class="content">
   		<% 		
		if(role.equals("admin")){  
        %>
	  	<a href="AddConvictionRecord.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>">Add New Conviction Record for <%= cpr.getFname()%> <%= cpr.getLname()%></a>
         <%
        }
         %> 
     <%
	Boolean isThereConvictionData = false;
	String noConvictionData = cpr.getFname() + " " + cpr.getLname() + " HAS NO KNOWN CONVICTION RECORD";
	
  	int convictionCount = 1;
  	for (ConvictionRecord convictionRecord: cr){
  		isThereConvictionData = true;
  		%>
  		<h2>Conviction Record <%= convictionCount%></h2>
  	  <table>
	    <tr>
	        <th>Category</th>
	        <th>Data</th>
	    </tr>
	
	    <!-- Conviction Records -->
	    <tr>
	        <td>Date of Conviction:</td>
	        <td><%= convictionRecord.getDateOfConviction()%></td>
	    </tr>
	    <tr>
	        <td>Conviction Offenses:</td>
	        <td><%= convictionRecord.getCriminalOffenses()%></td>
	    </tr>
	    <tr>
	        <td>Sentencing Details:</td>
	        <td><%= convictionRecord.getSentencingDetails()%></td>
	    </tr>
	    <tr>
	        <td>Probation and Parole Status:</td>
	        <td><%= convictionRecord.getParoleAndProbationStatus()%></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:yellow;"><a href="">EDIT RECORD</a></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="showConvictionConfirmation()">DELETE RECORD</button></td>
	    </tr>	    
	</table>
  		<%
  		convictionCount++;
  	}
  %>
  		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereConvictionData ? "" : noConvictionData %></h1>
  	
  </div>
  
  
  	<!-- This is the "Are you sure" message for when deleting a certain thing -->
     <div class="overlay" id="overlayPersonal">
        <div class="alert-box">
            <p>Are you sure you want to delete this criminal's record?</p>
            <button onclick="confirmAction(true, 'personal')">YES</button>
            <button onclick="confirmAction(false, 'personal')">NO</button>
        </div>
    </div>
     <div class="overlay" id="overlayArrest">
        <div class="alert-box">
            <p>Are you sure you want to delete this arrest record? Note that its corresponding booking information will be deleted as well.</p>
            <button onclick="confirmAction(true, 'arrest')">YES</button>
            <button onclick="confirmAction(false, 'arrest')">NO</button>
        </div>
    </div>   
     <div class="overlay" id="overlayBooking">
        <div class="alert-box">
            <p>Are you sure you want to delete this booking record? Note that its corresponding arrest record will be deleted as well.</p>
            <button onclick="confirmAction(true, 'booking')">YES</button>
            <button onclick="confirmAction(false, 'booking')">NO</button>
        </div>
    </div> 
     <div class="overlay" id="overlayConviction">
        <div class="alert-box">
            <p>Are you sure you want to delete this conviction record?</p>
            <button onclick="confirmAction(true, 'conviction')">YES</button>
            <button onclick="confirmAction(false, 'conviction')">NO</button>
        </div>
    </div>       
    <script>

        function showArrestConfirmation() {
            // Show the overlay and center the alert box
            document.getElementById('overlayArrest').style.display = 'flex';
        }
        function showPersonalConfirmation() {
            // Show the overlay and center the alert box
            document.getElementById('overlayPersonal').style.display = 'flex';
        }        
        function showBookingConfirmation() {
            // Show the overlay and center the alert box
            document.getElementById('overlayBooking').style.display = 'flex';
        }        
        function showConvictionConfirmation() {
            // Show the overlay and center the alert box
            document.getElementById('overlayConviction').style.display = 'flex';
        }
        
        function confirmAction(isConfirmed, recordType) {
            // Hide the overlay
            document.getElementById('overlayArrest').style.display = 'none';
            document.getElementById('overlayPersonal').style.display = 'none';
            document.getElementById('overlayBooking').style.display = 'none';
            document.getElementById('overlayConviction').style.display = 'none';

            if (isConfirmed) {
                // Perform the action when 'OK' is clicked
                if (recordType == "personal"){
                	alert('You clicked personal!');
                	
                } else if (recordType == "arrest"){
                	alert('You clicked arrest!');

                } else if (recordType == "booking"){
                	alert('You clicked booking!');

                } else if (recordType == "conviction"){
                	alert('You clicked conviction!');                	
                }
            } else {
                // Handle the action when 'Cancel' is clicked
            }
        }
    </script>
</body>
</html>