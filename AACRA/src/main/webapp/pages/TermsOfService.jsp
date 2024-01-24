<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Terms of Service and Legal COmplaince</title>
    <script>
        function updateButtonState() {
            var agreeToTerms = document.getElementById("agreeToTerms").checked;
            var agreeToLegalCompliance = document.getElementById("agreeToLegalCompliance").checked;
            var continueButton = document.getElementById("continueButton");

            continueButton.disabled = !(agreeToTerms && agreeToLegalCompliance);
        }
    </script>
</head>
<body>
	<h1>Terms Of Service and Legal Compliance</h1>
	<p>BEFORE YOU START SIGNING UP, you are required to read and agree to our terms of service and 
	legal complaince agreements. This is simply to insure you are aware of our rules an regulations, as
	well as adhere to any privacy and security laws that exist.</p> <br>
    <h2>Terms of Service Agreement</h2>
    
    <form action="AgreementServlet" method="post">
        <textarea rows="20" cols="150" name="termsOfService" readonly>
            Welcome to AACRA (Addis Ababa Criminal Records Authority)

By accessing or using AACRA, you agree to comply with and be bound by the following terms and conditions of use. If you do not agree to these terms, please do not use the Service.

1. Access and Use
	1.1 You must be a registered user to access certain features of the Service.
	1.2 You are responsible for maintaining the confidentiality of your account credentials.
	1.3 You agree to use the Service only for lawful purposes and in compliance with all applicable laws.

2. Data Security
	2.1 We implement security measures to protect against unauthorized access to or alteration of data.
	2.2 You are responsible for promptly notifying us of any unauthorized access to or use of your account.
	
3. Privacy Policy
	Please refer to our Privacy Policy for information on how we collect, use, and disclose data.

4. Intellectual Property
	4.1 The Service and its original content, features, and functionality are owned by [Your Company] and are protected by international copyright, trademark, patent, trade secret, and other intellectual property or proprietary rights laws.
	4.2 You may not modify, reproduce, or distribute any content from the Service without our written permission.

5. Termination
	We may terminate or suspend your account immediately, without prior notice or liability, for any reason whatsoever.

6. Governing Law
	These terms are governed by and construed in accordance with the laws of [Your Jurisdiction], without regard to its conflict of law provisions.

Privacy Policy

1. Information We Collect
	1.1 We collect personal information such as name, email, and other relevant data for account registration.
	1.2 We may collect usage data, including IP addresses, browser type, and pages visited, to improve our service.

2. How We Use Your Information
	2.1 We use your information to provide and improve the Service.
	2.2 Your data may be used for legal compliance, system maintenance, and security purposes.

3. Data Sharing and Disclosure
	3.1 We do not sell or rent your personal information to third parties.
	3.2 We may share data with law enforcement agencies or as required by law.

4. Security
	4.1 We employ industry-standard security measures to protect your data.
	4.2 Despite our efforts, no system can guarantee absolute security.

5. Amendments
	We may update our Terms of Service and Privacy Policy. Please review these documents periodically.

6. Contact Us
	If you have any questions about these terms or our privacy practices, please contact us at aacrahotline@gmail.com.

By using the Service, you acknowledge that you have read and understood these terms. If you do not agree, please refrain from using the Service.
        </textarea>
        
        <br>
        
        <label>
            <input type="checkbox" name="agreeToTerms" id="agreeToTerms" onclick="updateButtonState()"> I agree to these Terms of Service
        </label>

        <br><br>

        <h2>Legal Compliance Agreement</h2>

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

        <input type="submit" id="continueButton" value="Continue" disabled>
    </form>
    
    <div style="margin-bottom:100px;"></div>

</body>
</html>
