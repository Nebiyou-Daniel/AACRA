package com.aacra.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aacra.auth.model.User;
import com.aacra.utility.DatabaseUtility;
import com.aacra.auth.utility.NameValidator;
import com.aacra.auth.utility.PasswordValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signupError")
public class SignupErrorServlet extends HttpServlet{
	
	String invalidEmailErrorMessage = "This email has already been taken. Please try another one.";
	String invalidFnameErrorMessage = "Your first name contains invalid charater(s). Please try again.";
	String invalidLnameErrorMessage = "Your last name contains invalid charater(s). Please try again.";
	String invalidPasswordErrorMessage = "Your password should include at least one small letter, one capital "
			+ "letter, one special charater and needs to have a minimum of 8 characters. Please try again.";
	String differentPasswordErrorMessage = "The two passwords you have entered are not the same. Please try again.";


	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getAttribute("userData");
		String confirmPassword = (String) request.getAttribute("confirmPassword");
        
    	String selectUserQuery = "SELECT * FROM users WHERE email = ?";

        Connection connection;
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(selectUserQuery);
        	
            ps.setString(1, user.getEmail());
            
			// Check if email has been taken or not 
			ResultSet resultSet = ps.executeQuery();
			
            if (resultSet.next()) {
            	// There is an email account similar with the inserted email  
            	
            	request.setAttribute("userData", user);
            	request.setAttribute("insertedSecondPassword", confirmPassword);            	
            	request.setAttribute("invalidEmail", invalidEmailErrorMessage);
            	request.getRequestDispatcher("pages/SignupPage.jsp").forward(request, response);
                          	
            } else {
            	// There is no account with an email similar to the one inserted
            	
            	NameValidator nv = new NameValidator();
            	PasswordValidator pv = new PasswordValidator();
            	
            	// First Name is invalid
            	if (!nv.isAlphabetic(user.getFname())) {
                	request.setAttribute("userData", user);
                	request.setAttribute("insertedSecondPassword", confirmPassword);            	
                	request.setAttribute("invalidFname", invalidFnameErrorMessage);
                	request.getRequestDispatcher("pages/SignupPage.jsp").forward(request, response); 
                
                // Last Name is invalid 
            	} else if (!nv.isAlphabetic(user.getLname()) ) {
                	request.setAttribute("userData", user);
                	request.setAttribute("insertedSecondPassword", confirmPassword);            	
                	request.setAttribute("invalidLname", invalidLnameErrorMessage);
                	request.getRequestDispatcher("pages/SignupPage.jsp").forward(request, response);
            		
                // Password is invalid
            	} else if (!pv.isPasswordValid(user.getPassword()) ){
                	request.setAttribute("userData", user);
                	request.setAttribute("insertedSecondPassword", confirmPassword);            	
                	request.setAttribute("invalidPassword", invalidPasswordErrorMessage);
                	request.getRequestDispatcher("pages/SignupPage.jsp").forward(request, response);
            		
                // Confirm Password is invalid
            	} else if (!(user.getPassword().equals(confirmPassword))) {
                	request.setAttribute("userData", user);
                	request.setAttribute("insertedSecondPassword", confirmPassword);            	
                	request.setAttribute("invalidSecondPassword", differentPasswordErrorMessage);
                	request.getRequestDispatcher("pages/SignupPage.jsp").forward(request, response); 
                	
            	} else {
            		PrintWriter pw = response.getWriter();
            		pw.print("<h1>WTF</h1>");
            	}
            	
            }
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
}
