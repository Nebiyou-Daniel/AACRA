package com.aacra.record.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        String query = "INSERT INTO booking_info (arrest_record_id, mugshot, booking_number, criminal_id) VALUES (?, ?, ?, ?)";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setInt(1, bi.getArrestRecordId());
            preparedStatement.setBlob(2, bi.getMugshot());
            preparedStatement.setString(3, bi.getBookingNumber());
            preparedStatement.setInt(4, bi.getCriminalId());
                
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

    public CriminalPersonalRecord getCriminalPersonalRecordById(int criminalId) {
    	
    	String query = "SELECT * FROM criminal_personal_info WHERE criminal_id = ?";
    	CriminalPersonalRecord cpr = new CriminalPersonalRecord();
        try {	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(query);
        	
            ps.setInt(1, criminalId);
            
			// Check if the account is in there
			ResultSet resultSet = ps.executeQuery();
			
            if (resultSet.next()) {
            	
            	cpr.setCriminalId(criminalId);
            	cpr.setAddress(resultSet.getString("address"));
            	cpr.setDateOfBirth(resultSet.getDate("date_of_birth"));
            	cpr.setFname(resultSet.getString("criminal_fname"));
            	cpr.setGender(resultSet.getString("gender"));
            	cpr.setKebeleId(resultSet.getString("kebele_id"));
            	cpr.setLname(resultSet.getString("criminal_lname"));
            	cpr.setPhoneNumber(resultSet.getString("phone_number"));
            	cpr.setRaceAndEthnicity(resultSet.getString("race_ethnicity"));
            	
            }
       
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cpr;
    	
    }
    
    public ArrayList<ArrestRecord> getArrestRecordById(int criminalId) {
    	
    	String query = "SELECT * FROM arrest_records WHERE criminal_id = ?";
    	ArrayList<ArrestRecord> recordList = new ArrayList<ArrestRecord>();
        try {	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(query);
        	
            ps.setInt(1, criminalId);
            
			// Check if the account is in there
			ResultSet resultSet = ps.executeQuery();
			
            while (resultSet.next()) {
            	ArrestRecord ar = new ArrestRecord();
            	
            	ar.setDateTimeArrest((LocalDateTime) resultSet.getObject("date_time_arrest"));
            	ar.setCriminalId(criminalId);
            	ar.setArrestRecordId(resultSet.getInt("arrest_record_id"));
            	ar.setArrestingAgency(resultSet.getString("arresting_agency"));
            	ar.setArrestingOfficer(resultSet.getString("arresting_officer"));
            	ar.setCharges(resultSet.getString("charges"));
            	ar.setArrestLocation(resultSet.getString("arrest_location"));
            	
            	recordList.add(ar);            	
            }
       
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return recordList;
        
    }
//    public ArrayList<BookingRecord> getBookingRecordById(int criminalId, OutputStream outputStream) throws IOException {

    public ArrayList<BookingRecord> getBookingRecordById(int criminalId) throws IOException {
    	
    	String query = "SELECT * FROM booking_info WHERE criminal_id = ?";
    	ArrayList<BookingRecord> recordList = new ArrayList<BookingRecord>();
        try {	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(query);
        	
            ps.setInt(1, criminalId);
            
			// Check if the account is in there
			ResultSet resultSet = ps.executeQuery();
			
            while (resultSet.next()) {
            	BookingRecord bi = new BookingRecord();
            	
            	bi.setCriminalId(criminalId);
            	bi.setBookingInfoId(resultSet.getInt("booking_info_id"));
            	bi.setArrestRecordId(resultSet.getInt("arrest_record_id"));
            	bi.setMugshot(BookingRecord.blobToInputStream(resultSet.getBlob("mugshot")));
            	bi.setBookingNumber(resultSet.getString("booking_number"));
            	
//            	bi.copyInputStreamToOutputStream(bi.getMugshot(), outputStream);
            	
            	recordList.add(bi);            	
            }
       
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return recordList;
    }
    
    public ArrayList<ConvictionRecord> getConvictionRecordById(int criminalId) {
    	
    	String query = "SELECT * FROM conviction_records WHERE criminal_id = ?";
    	ArrayList<ConvictionRecord> recordList = new ArrayList<ConvictionRecord>();
        try {	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(query);
        	
            ps.setInt(1, criminalId);
            
			// Check if the account is in there
			ResultSet resultSet = ps.executeQuery();
			
            while (resultSet.next()) {
            	ConvictionRecord cr = new ConvictionRecord();
            	
            	cr.setCriminalId(criminalId);
            	cr.setConvictionRecordId(resultSet.getInt("conviction_record_id"));
            	cr.setDateOfConviction(resultSet.getDate("date_of_conviction"));
            	cr.setCriminalOffenses(resultSet.getString("convicted_offenses"));
            	cr.setSentencingDetails(resultSet.getString("sentencing_details"));
            	cr.setParoleAndProbationStatus(resultSet.getString("probation_parole_status"));
            	
            	recordList.add(cr);            	
            }
       
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return recordList;
    }

 
    public boolean editCriminalRecord(CriminalPersonalRecord cpr) {
        String query = "UPDATE criminal_personal_info SET criminal_fname = ?, criminal_lname = ?, date_of_birth = ?, "
        		+ "gender = ?, race_ethnicity = ?, kebele_id = ?, address = ?, phone_number = ? WHERE criminal_id = ?";
        
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
            preparedStatement.setInt(9, cpr.getCriminalId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    	
    }

    public boolean editArrestRecord(ArrestRecord ar) {
        String query = "UPDATE arrest_records SET date_time_arrest = ?, arresting_agency = ?, arresting_officer = ?,"
        		+ " charges = ?, arrest_location = ? WHERE arrest_record_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setObject(1, ar.getDateTimeArrest());
            preparedStatement.setString(2, ar.getArrestingAgency());
            preparedStatement.setString(3, ar.getArrestingOfficer());
            preparedStatement.setString(4, ar.getCharges());
            preparedStatement.setString(5, ar.getArrestLocation());
            preparedStatement.setInt(6, ar.getArrestRecordId());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    	
    }

    public boolean editBookingInfo(BookingRecord bi) {
        String query = "UPDATE booking_info SET booking_number = ?, mugshot = ? WHERE booking_info_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	

            preparedStatement.setString(1, bi.getBookingNumber());
            preparedStatement.setBlob(2, bi.getMugshot());
            preparedStatement.setInt(3, bi.getBookingInfoId());

                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    	
    }
    
    public boolean editConvictionRecord(ConvictionRecord cr) {
        String query = "UPDATE conviction_records SET date_of_conviction = ?, convicted_offenses = ?,"
        		+ " sentencing_details = ?, probation_parole_status = ? WHERE conviction_record_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	
        	          	
            preparedStatement.setDate(1, cr.getDateOfConviction());
            preparedStatement.setString(2, cr.getCriminalOffenses());
            preparedStatement.setString(3, cr.getSentencingDetails());
            preparedStatement.setString(4, cr.getParoleAndProbationStatus());
            preparedStatement.setInt(5, cr.getConvictionRecordId());
                
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    	
    }
    
    public boolean deleteCriminalRecord(int criminal_id) {
    	
        String query = "DELETE FROM criminal_personal_info WHERE criminal_id = ?";
    	String arrestQuery = "DELETE FROM arrest_records WHERE criminal_id = ?";
        String bookingQuery = "DELETE FROM booking_info WHERE criminal_id = ?";
        String convictionQuery = "DELETE FROM conviction_records WHERE criminal_id = ?";
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	
        	PreparedStatement ps2 = connection.prepareStatement(arrestQuery); 
        	PreparedStatement ps3 = connection.prepareStatement(bookingQuery); 
        	PreparedStatement ps4 = connection.prepareStatement(convictionQuery); 
        	
            preparedStatement.setInt(1, criminal_id);
            ps2.setInt(1, criminal_id);
            ps3.setInt(1, criminal_id);          
            ps4.setInt(1, criminal_id);   
            
            ps4.executeUpdate();
            ps3.executeUpdate();
            ps2.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } 
    	
    }

    public boolean deleteArrestRecord(int arrest_record_id) {
    	
        String query = "DELETE FROM arrest_records WHERE arrest_record_id = ?";
        String bookingQuery = "DELETE FROM booking_info WHERE arrest_record_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement preparedStatement = connection.prepareStatement(query);        	
        	PreparedStatement ps2 = connection.prepareStatement(bookingQuery); 
        	
            preparedStatement.setInt(1, arrest_record_id);
            ps2.setInt(1, arrest_record_id);
              
            ps2.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } 
    }

    public boolean deleteBookingInfo(int booking_info_id) {
    	
        String bookingQuery = "DELETE FROM booking_info WHERE booking_info_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(bookingQuery); 
        	
            ps.setInt(1, booking_info_id);

            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
  
    public boolean deleteConvictionRecord(int conviction_record_id) {
    	
        String bookingQuery = "DELETE FROM conviction_records WHERE conviction_record_id = ?";
        
        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
        
        	PreparedStatement ps = connection.prepareStatement(bookingQuery); 
        	
            ps.setInt(1, conviction_record_id);

            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;
            
                 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }    	
    }
}
