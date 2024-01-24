package com.aacra.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aacra.auth.model.User;
import com.aacra.utility.DatabaseUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginError")
public class LoginErrorServlet extends HttpServlet{
	String incorrectEmailErrorMessage = "This email doesn't exist on the system. Please try a different one";
	String incorrectPasswordErrorMessage = "The password is incorrect. Please use the correct one.";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getAttribute("userData");
        
    	String selectUserQuery = "SELECT * FROM users WHERE email = ?";

        Connection connection;
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(selectUserQuery);
        	
            ps.setString(1, user.getEmail());
            
			// Check if email exists or not 
			ResultSet resultSet = ps.executeQuery();
			
            if (resultSet.next()) {
            	// There is an email account similar with the inserted email, now check if the password for that email is the same 
            	String insertedPassword = user.getPassword();
            	String existingPassword = resultSet.getString("password");
            	
            	if (!(insertedPassword.equals(existingPassword))) {
                	request.setAttribute("userData", user);
                	request.setAttribute("incorrectPassword", incorrectPasswordErrorMessage);
                	request.getRequestDispatcher("pages/LoginPage.jsp").forward(request, response);  
                	
            	} else {
            		response.getWriter().println("<h1>What in the FUCK!<h1>");
            	}
                          	
            } else {
            	// There is no account with an email similar to the one inserted
            	
            	request.setAttribute("userData", user);
            	request.setAttribute("incorrectEmail", incorrectEmailErrorMessage);
            	request.getRequestDispatcher("pages/LoginPage.jsp").forward(request, response);
            }
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
}
