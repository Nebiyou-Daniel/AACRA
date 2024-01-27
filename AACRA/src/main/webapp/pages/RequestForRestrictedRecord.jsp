<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request for Restricted Records</title>
</head>
<%@page import="com.aacra.auth.model.User" %>

<body>
	<h2>Request for Restricted Records of <%= request.getParameter("fname")%> <%= request.getParameter("lname")%></h2>
	<form action="requestForRestrictedRecord" method="post">
		<fieldset>
			<legend>Business</legend>
					    
		    <p>If you have a business and you are planning to use the information you would receive for
		    the sake of your business, please fill in the form directly below it</p>
		    
		    <label for="enableCheckbox">Do you have a business?</label>
		    <input type="checkbox" id="enableCheckbox" onclick="enableTextInput()"> <br><br>
		    <input type="hidden"  id="for_business" name="for_business" value="false">
		    
		    <label for="business_name">What is your business' name?</label>
		    <input type="text" id="business_name" name="business_name" disabled> <br><br>
		    
		    <label for="business_address">What is your business' address (center of work/ headquarters)?</label>
		    <input type="text" id="business_address" name="business_address" disabled> <br><br>		
		</fieldset>
			<br><br>
		
		    <label for="textarea">What is your reason for requesting the records of this 
		    particular individual? What do you hope to accomplish with it?</label> <br>
		    <textarea id="textarea" name="reason" rows="8" cols="100"></textarea> <br><br>
		    
		    <h2>Legal Compliance Agreement</h2>
		    
		    <p>Even though you have agreed to the legal complaince document upon signing up, we wish to make 
		    sure you are aware of the legal implications and responsibility you would have to shoulder should
		    you wish to proceed with your request.</p>

        <textarea rows="20" cols="150" name="legalCompliance" readonly>
1. Overview
By using AACRA, you agree to comply with all applicable laws, regulations, and legal requirements. This Legal Compliance Agreement outlines the obligations and responsibilities of users regarding the lawful use of the System.

2. Data Protection and Privacy Laws
	2.1 Users of the System agree to comply with all relevant data protection and privacy laws, including but not limited to:
		- The General Data Protection Regulation (GDPR)
		- The Health Insurance Portability and Accountability Act (HIPAA)
		- The Family Educational Rights and Privacy Act (FERPA)
		- Any other applicable national, state, or local data protection laws.		
	2.2 Users must obtain necessary consents and permissions before accessing, using, or processing any personal information through the System.

3. Access Restrictions and Authorization
	3.1 Access to the System is limited to authorized individuals as defined by applicable laws and regulations.	
	3.2 Users must not misuse their access privileges, and any access outside the scope of their authority is strictly prohibited.

4. Confidentiality and Security
	4.1 Users acknowledge the sensitive nature of the information within the System and agree to maintain the confidentiality and security of all data.
	4.2 Users are responsible for implementing appropriate security measures to prevent unauthorized access, disclosure, or alteration of information.

5. Legal Use of Information
	5.1 Users agree to use information obtained from the System strictly for legal and authorized purposes.
	5.2 Any use of information for unlawful activities, discrimination, or any purpose inconsistent with applicable laws is strictly prohibited.

6. Reporting Obligations
	Users must promptly report any unauthorized access, data breaches, or other security incidents related to the System to the designated administrators and, if required by law, to relevant authorities.

7. Compliance with Law Enforcement Requests
	Users agree to comply with lawful requests from law enforcement agencies and other governmental authorities, including providing information from the System as required by applicable laws.

8. Amendments to Legal Compliance Agreement
	This Legal Compliance Agreement may be updated to reflect changes in laws and regulations. Users will be notified of any changes, and continued use of the System implies acceptance of the updated terms.

9. Governing Law
	This agreement is governed by and construed in accordance with the laws of [Your Jurisdiction], without regard to its conflict of law provisions.

10. Contact Information
	For questions or concerns regarding legal compliance, contact [Your Contact Information].

By using the System, users acknowledge and agree to comply with the terms of this Legal Compliance Agreement. Failure to adhere to these terms may result in the termination of access to the System and may lead to legal consequences.
        </textarea>
        
        <br>
        
        <label>
            <input type="checkbox" name="agreeToLegalCompliance" id="agreeToLegalCompliance" onclick="updateButtonState()"> I agree to these Legal Compliances
        </label>

        <br><br>



<%
	User userData = (User) request.getSession().getAttribute("userData");
	int userId = userData.getUserId();
%>
		<input type="hidden" name="user_id" value="<%= userId%>">
	    <input type="hidden" name="criminal_id" value="<%= request.getParameter("criminal_id")%>">
	    <input type="hidden" name="fname" value="<%= request.getParameter("fname")%>">	    
	    <input type="hidden" name="lname" value="<%= request.getParameter("lname")%>">
	    	    
        <input type="submit" id="continueButton" value="Continue" disabled>
	</form>

<script>
	function updateButtonState() {
	    var agreeToLegalCompliance = document.getElementById("agreeToLegalCompliance").checked;
	    var continueButton = document.getElementById("continueButton");
	
	    continueButton.disabled = !(agreeToLegalCompliance);
	}
	
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