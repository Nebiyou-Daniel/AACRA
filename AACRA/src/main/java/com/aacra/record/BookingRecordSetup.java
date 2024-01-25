package com.aacra.record;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aacra.utility.DatabaseUtility;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookingInfoSetup")
public class BookingRecordSetup extends HttpServlet{
	private static final String query = "select * from arrest_records where criminal_id = ? and date_time_arrest = ?";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {			
			int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
	        String date_time_arrest = request.getParameter("date_time_arrest");
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        	LocalDateTime dateTimeArrest = LocalDateTime.parse(date_time_arrest, formatter);
			
			Connection connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, criminal_id);
			statement.setObject(2, dateTimeArrest);
			
			
			try (ResultSet rs = statement.executeQuery()) {
				rs.next();
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/AddBookingInfo.jsp?arrest_record_id=" + rs.getInt("arrest_record_id"));
				rd.forward(request, response);
				
				connection.close();				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
	
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
}
