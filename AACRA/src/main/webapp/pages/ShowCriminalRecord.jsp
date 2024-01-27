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
	  	<li><a href="RequestForRestrictedRecord.jsp?criminal_id=<%= request.getAttribute("criminal_id")%>&fname=<%= cpr.getFname()%>&lname=<%= cpr.getLname()%>">Make Request for Restricted Record</a></li>
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
	    <% 		
		if(!(role.equals("regular"))){  
        %>
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
	        <td colspan="2" style="background-color:yellow;"><a href="editCriminalRecordSetup?criminal_id=<%= cpr.getCriminalId()%>">EDIT RECORD</a></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="confirmCriminalRecordDelete()">DELETE RECORD</button></td>
	    </tr>	    
         <%
        }
         %>
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
	
	if (!(role.equals("regular"))){		
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
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="confirmArrestRecordDelete()">DELETE RECORD</button></td>
	    </tr>
	</table>
	
  		
  		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereArrestData ? "" : noArrestData %></h1>
  		<%
  		arrestCount++;
	  	}
  	} else {
  		%>
  		<h1 style="color: orange; font-weight: 900; font-size: 24px; text-align: center;">You aren't given access to the arrest records. If you want, you will have to request for it using the link given above.</h1>
  		<%
  	}
  %>
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
	
	if (!(role.equals("regular"))){
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
	        <td colspan="2" style="background-color:yellow;"><a href="editBookingRecordSetup?booking_info_id=<%= bookingInfo.getBookingInfoId()%>">EDIT RECORD</a></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="confirmBookingRecordDelete()">DELETE RECORD</button></td>
	    </tr>
	</table>
  		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereBookingData ? "" : noBookingData %></h1>
  		<%
  		bookingCount++;
	  	}
  	} else {
  		%>
  		<h1 style="color: orange; font-weight: 900; font-size: 24px; text-align: center;">You aren't given access to the booking records. If you want, you will have to request for it using the link given above.</h1>
  		<%
  	}
  %>
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
	
	if (!(role.equals("regular"))){
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
	        <td colspan="2" style="background-color:yellow;"><a href="editConvictionRecordSetup?conviction_record_id=<%= convictionRecord.getConvictionRecordId()%>&criminal_id=<%= cpr.getCriminalId()%>">EDIT RECORD</a></td>
	    </tr>
	    <tr>
	        <td colspan="2" style="background-color:red;" ><button style="background-color:red; color:white; border: 0;" onclick="confirmConvictionRecordDelete()">DELETE RECORD</button></td>
	    </tr>	    
	</table>
  		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereConvictionData ? "" : noConvictionData %></h1>
  		<%
  		convictionCount++;
	  	}
  	} else {
  		%>
  		<h1 style="color: orange; font-weight: 900; font-size: 24px; text-align: center;">You aren't given access to the conviction records. If you want, you will have to request for it using the link given above.</h1>
  		<%
  	}
  %>
  	
  </div>
        
    <script>

	function confirmArrestRecordDelete(arrestRecordId) {
	    var confirmation = confirm("Are you sure you want to delete the record?");
	
	    if (confirmation) {
	    	window.location.href = 'deleteArrestRecord?arrest_record_id=' + arrestRecordId;
	    }
	}
	function confirmCriminalRecordDelete(criminalId) {
	    var confirmation = confirm("Are you sure you want to delete the record?");
	
	    if (confirmation) {
	    	window.location.href = 'deleteCriminalRecord?criminal_id=' + criminalId;
	    }
	}      
	function confirmBookingRecordDelete(booking_info_id) {
	    var confirmation = confirm("Are you sure you want to delete the record?");
	
	    if (confirmation) {
	    	window.location.href = 'deleteBookingRecord?booking_info_id=' + booking_info_id;
	    }
	}        
	function confirmConvictionRecordDelete(conviction_record_id) {
	    var confirmation = confirm("Are you sure you want to delete the record?");
	
	    if (confirmation) {
	    	window.location.href = 'deleteConvictionRecord?conviction_record_id=' + conviction_record_id;
	    }
	}  
        
    </script>
</body>
</html>