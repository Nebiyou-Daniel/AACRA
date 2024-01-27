package com.aacra.record;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aacra.utility.DatabaseUtility;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/editBookingRecord")
public class EditBookingRecordSetup extends HttpServlet{
	public String query = "SELECT * FROM booking_info WHERE booking_info_id = ?";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
		int booking_info_id = Integer.parseInt(request.getParameter("booking_info_id"));
	
		try {			
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, booking_info_id);
			
			try (ResultSet rs = statement.executeQuery()) {
				
				request.setAttribute("conviction_record_id", booking_info_id);
				request.setAttribute("resultSet", rs);
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EditConvictionRecord.jsp?criminal_id=" + criminal_id);
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
}
