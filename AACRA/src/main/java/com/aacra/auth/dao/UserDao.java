package com.aacra.auth.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aacra.auth.model.User;
import com.aacra.auth.utility.*;
import com.aacra.utility.DatabaseUtility;


public class UserDao {
    private Connection connection;
    
    public boolean addUser(User user) {
        String query = "INSERT INTO users (fname, lname, email, password, role) VALUES (?, ?, ?, ?, ?)";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean addOfficer(User user, InputStream fileContent, String workLocation, int officerWereda) throws IOException {
    	/*
    	 * This method will be used to create a new account for a user with a law enforcement officer's 
    	 * role. What makes it different from the method for a regular user is that it takes as input three
    	 * additional parameters: fileContent (w/c represents an officer's ID in picture format), workLocation
    	 * (w/c represents the subcity the officer works in), and officer's wereda (the wereda in that specific 
    	 * subcity)
    	 * */
        String query = "INSERT INTO users (fname, lname, email, password, role, officerIdPhoto, officerWereda, workLocation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setBlob(6, fileContent);
            preparedStatement.setInt(7, officerWereda);
            preparedStatement.setString(8, workLocation);
            
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }    	
    }
    
    
    
    
    public boolean addAnalyst(User user, InputStream fileContent, String workLocation) throws IOException {
    	/*
    	 * This method will be used to create a new account for a user with a crime analyst's 
    	 * role. What makes it different from the method for a regular user is that it takes as input two
    	 * additional parameters: fileContent (w/c represents an analyst's certification), and workLocation
    	 * (w/c represents the organization or department the analyst works at).
    	 * */
        String query = "INSERT INTO users (fname, lname, email, password, role, workLocation, analystCertification) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setString(6, workLocation);
            preparedStatement.setBlob(7, fileContent);
            
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } 
    }
    
    
    
    
    public boolean checkUserData(User user, String confirmPassword) {
    	/*
    	 * Checks if the data entered during SIGN UP fulfills all given requirements and 
    	 * that the email doesn't already exist in the database.
    	 * 
    	 * This involves using the NameValidator and PasswordValidator classes for the names and password
    	 * and the connection class to access the database and search for an account with the same email 
    	 * address.
    	 * 
    	 * */
    	String selectUserQuery = "SELECT * FROM users WHERE email = ?";

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(selectUserQuery);
        	
            ps.setString(1, user.getEmail());
            
			// Check if email has been taken or not 
			ResultSet resultSet = ps.executeQuery();
			
            if (resultSet.next()) {
            	// There is an email account similar with the inserted email         	
            	return false;
                          	
            } else {
            	// There is no account with an email similar to the one inserted
            	//
            	// We now would need to check the validity of the names and the password
            	// Additionally, we should make sure the password and the confirm passwords are the same
            	
            	NameValidator nv = new NameValidator();
            	PasswordValidator pv = new PasswordValidator();
            	
            	// If first name or last name don't have valid caharacters, password isn't valid or password 
            	// and confirm password aren't the same
            	if (!nv.isAlphabetic(user.getFname()) || !nv.isAlphabetic(user.getLname()) 
            			|| !pv.isPasswordValid(user.getPassword()) || !(user.getPassword().equals(confirmPassword))) {
            		
            		return false;
            	}
            	             
                return true;
            }
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    // For login
    public User validateUser(User user) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        User validatedUser = new User();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setString(1, user.getEmail());
	            preparedStatement.setString(2, user.getPassword());
	
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            if (resultSet.next()) {
	            	
	            	validatedUser.setFname(resultSet.getString("fname"));
	            	validatedUser.setLname(resultSet.getString("lname"));
	            	validatedUser.setEmail(resultSet.getString("email"));
	            	validatedUser.setPassword(resultSet.getString("password"));
	            	validatedUser.setRole(resultSet.getString("role"));
	            	
	            }
	            return validatedUser;
	        }
        
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
	        return null;
        }
    }
}