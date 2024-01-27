package com.aacra.record;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.text.similarity.LevenshteinDistance;

import com.aacra.record.dao.RecordDao;
import com.aacra.record.model.CriminalPersonalRecord;
import com.aacra.utility.DatabaseUtility;

@WebServlet("/pages/searchRecords")
public class SearchRecordsServlet extends HttpServlet {
	public String searchQuery = "SELECT * FROM criminal_personal_info WHERE criminal_fname = ? AND criminal_lname = ?";
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Criminal Personal Info
        String criminal_fname = request.getParameter("criminal_fname");
        String criminal_lname = request.getParameter("criminal_lname");
        String gender = request.getParameter("gender");
        String race_ethnicity = request.getParameter("race_ethnicity");
        String kebele_id = request.getParameter("kebele_id");
        String address = request.getParameter("address");
        String phone_number = request.getParameter("phone_number");

        Date date_of_birth;
        if (request.getParameter("date_of_birth") != "" && request.getParameter("date_of_birth") != null) {        	
        	date_of_birth = Date.valueOf(request.getParameter("date_of_birth"));
        } else {
        	date_of_birth = null;
        }

        // Check if they are not null and add to the query depending on the result of the if statements
        if (date_of_birth != null) {
        	searchQuery = searchQuery + " AND date_of_birth = '" + date_of_birth + "'";
        }
        if (gender != "" && gender != null) {
        	searchQuery = searchQuery + " AND gender = '" + gender + "'";
        }        
        if (race_ethnicity != "" && race_ethnicity != null) {
        	searchQuery = searchQuery + " AND race_ethnicity = '" + race_ethnicity + "'";
        }        
        if (kebele_id != "" && kebele_id != null) {
        	searchQuery = searchQuery + " AND kebele_id = '" + kebele_id + "'";
        }        
        if (address != ""&& address != null) {
        	searchQuery = searchQuery + " AND address = '" + address + "'";
        }        
        if (phone_number != "" && phone_number != null) {
        	searchQuery = searchQuery + " AND phone_number = '" + phone_number + "'";
        }    
        
		try {				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(searchQuery);
			
			statement.setString(1, criminal_fname);
			statement.setString(2, criminal_lname);
			
			try (ResultSet rs = statement.executeQuery()) {
				
				request.setAttribute("resultSet", rs);
				request.setAttribute("fname", criminal_fname);
				request.setAttribute("lname", criminal_lname);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/DisplaySearchResult.jsp");
				rd.forward(request, response);
				
				connection.close();				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    
    
    private ArrayList<Record> performSearch(String fname,String lname, String dob, String crimeType) {
        // Implement your search logic here based on the provided parameters
        // This could involve querying a database or another data source
        // Return a ArrayList of matching records
        // ...

        // For the purpose of this example, let's return an empty ArrayList
        return null;
    }

    private ArrayList<Record> findSimilarResults(String name, String dob, String crimeType) {
//        // Implement partial matching and fuzzy matching logic here
//        // For example, use Levenshtein distance for fuzzy matching
//
//        ArrayList<Record> allRecords = getAllRecords(); // Implement this method to retrieve all records
//
        ArrayList<Record> similarResults = new ArrayList<>();
//
//        for (Record record : allRecords) {
//            // Implement your matching criteria based on name, dob, and crimeType
//            // For simplicity, let's use Levenshtein distance for name matching
//            int nameDistance = LevenshteinDistance.getDefaultInstance().apply(record.getName(), name);
//            
//            if (nameDistance < 3) { // Adjust the threshold based on your requirements
//                similarResults.add(record);
//            }
//            // Add similar matching logic for dob and crimeType
//        }
//
        return similarResults;
    }
}

