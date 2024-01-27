package com.aacra.request.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aacra.request.model.RecordRequest;
import com.aacra.utility.DatabaseUtility;

public class RequestDao {
    private Connection connection;
    
    public RecordRequest getRecordRequestByRequestId() {
		return null;
    }

    public boolean checkRecordRequest(int userId, int criminalId) {
    	
    	String selectUserQuery = "SELECT * FROM record_requests WHERE user_id = ? AND criminal_id = ?";

        try {       	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(selectUserQuery);
        	
            ps.setInt(1, userId);
            ps.setInt(2, criminalId);
            
			// Check if request has been made or not 
			ResultSet resultSet = ps.executeQuery();
			
            if (resultSet.next()) {
            	// There is a record request account made by the user for a specific criminal        	
            	return true;
                          	
            } else {
            	return false;            
            }
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public RecordRequest getRecordRequestByUserAndCriminalId() {
		return null;
    }
    
    public ArrayList<RecordRequest> getRecordRequestsByUserId() {
    	return null;
    } 
    
    public ArrayList<RecordRequest> getAllApprovedRecordRequests() {
    	return null;
    }   

    public ArrayList<RecordRequest> getAllRejectedRecordRequests() {
    	return null;
    }
    
    public ArrayList<RecordRequest> getAllPendingRecordRequests() {
    	return null;
    }
    
    public boolean addRecordRequestForBusiness(RecordRequest rr) {
    	
        String query = "INSERT INTO record_requests (criminal_id, user_id, reason, for_business,"
        		+ " business_name, business_address) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {       	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setInt(1, rr.getCriminalId());
            preparedStatement.setInt(2, rr.getUserId());
            preparedStatement.setString(3, rr.getReason());
            preparedStatement.setBoolean(4, rr.isForBusiness());
            preparedStatement.setString(5, rr.getBusinessName());
            preparedStatement.setString(6, rr.getBusinessAddress());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addRecordRequest(RecordRequest rr) {
    	
        String query = "INSERT INTO record_requests (criminal_id, user_id, reason, for_business) VALUES (?, ?, ?, ?)";
        
        try {       	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setInt(1, rr.getCriminalId());
            preparedStatement.setInt(2, rr.getUserId());
            preparedStatement.setString(3, rr.getReason());
            preparedStatement.setBoolean(4, rr.isForBusiness());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editRecordRequest(RecordRequest rr) {
        String query = "UPDATE record_requests SET reason = ?, for_business = ?, business_name = ?,"
        		+ " business_address = ? WHERE request_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setString(1, rr.getReason());
            preparedStatement.setBoolean(2, rr.isForBusiness());
            preparedStatement.setString(3, rr.getBusinessName());
            preparedStatement.setString(4, rr.getBusinessAddress());
            preparedStatement.setInt(5, rr.getRequestId());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    		
    }

    public boolean processRecordRequest(int requestId, String decision) {
        String query = "UPDATE record_requests SET status = ? WHERE request_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setString(1,decision);
            preparedStatement.setInt(2, requestId);
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }    	
    }
    
    public boolean deleteRecordRequest(int requestId) {
        String query = "DELETE FROM record_requests WHERE request_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setInt(1, requestId);
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } 
    	
    }
}
