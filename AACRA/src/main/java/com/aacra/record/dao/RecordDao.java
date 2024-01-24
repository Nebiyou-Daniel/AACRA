package com.aacra.record.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aacra.record.model.ArrestRecord;
import com.aacra.record.model.BookingRecord;
import com.aacra.record.model.ConvictionRecord;
import com.aacra.record.model.CriminalPersonalRecord;
import com.aacra.utility.DatabaseUtility;

public class RecordDao {
    private Connection connection;

    public boolean addCriminalInfo(CriminalPersonalRecord cpr) {
        String query = "INSERT INTO criminal_personal_info (criminal_fname, criminal_lname, date_of_birth, gender,"
        		+ " race_ethnicity, kebele_id, address, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setString(1, cpr.getFname());
            preparedStatement.setString(2, cpr.getLname());
            preparedStatement.setDate(3, cpr.getDateOfBirth());
            preparedStatement.setString(4, cpr.getGender());
            preparedStatement.setString(5, cpr.getRaceAndEthnicity());
            preparedStatement.setString(6, cpr.getKebeleId());
            preparedStatement.setString(7, cpr.getAddress());
            preparedStatement.setString(8, cpr.getPhoneNumber());
            
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }	
    }
    
    public boolean addArrestRecord(ArrestRecord ar) {
        String query = "INSERT INTO arrest_records (criminal_id, date_time_arrest, arresting_agency, arresting_officer,"
        		+ " charges, arrest_location) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setInt(1, ar.getCriminalId());
            preparedStatement.setObject(2, ar.getDateTimeArrest());
            preparedStatement.setString(3, ar.getArrestingAgency());
            preparedStatement.setString(4, ar.getArrestingOfficer());
            preparedStatement.setString(5, ar.getCharges());
            preparedStatement.setString(6, ar.getArrestLocation());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addBookingInfo(BookingRecord bi) {
        String query = "INSERT INTO booking_info (arrest_record_id, mugshot, booking_number) VALUES (?, ?, ?)";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setInt(1, bi.getArrestRecordId());
            preparedStatement.setBlob(2, bi.getMugshot());
            preparedStatement.setString(3, bi.getBookingNumber());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addConvictionRecord(ConvictionRecord cr) {
        String query = "INSERT INTO conviction_records (criminal_id, date_of_conviction, convicted_offenses, sentencing_details,"
        		+ " probation_parole_status) VALUES (?, ?, ?, ?, ?)";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setInt(1, cr.getCriminalId());
            preparedStatement.setDate(2, cr.getDateOfConviction());
            preparedStatement.setString(3, cr.getCriminalOffenses());
            preparedStatement.setString(4, cr.getSentencingDetails());
            preparedStatement.setString(5, cr.getParoleAndProbationStatus());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getCriminalId(String kebeleId, String address, String phoneNumber, String fname, String lname) {
    	
    	String selectUserQuery = "SELECT * FROM criminal_personal_info WHERE criminal_fname = ? AND criminal_lname = ?"
    			+ " AND kebele_id = ? AND address = ? AND phone_number = ?";
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(selectUserQuery);
        	
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, kebeleId);
            ps.setString(4, address);
            ps.setString(5, phoneNumber);
            
			// Check if the account is in there
			ResultSet resultSet = ps.executeQuery();
			
            if (resultSet.next()) {
            	return resultSet.getInt("criminal_id");                	
            }
            	             
            return 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
