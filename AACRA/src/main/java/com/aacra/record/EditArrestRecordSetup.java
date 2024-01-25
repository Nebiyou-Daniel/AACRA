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

@WebServlet("/pages/editArrestRecordSetup")
public class EditArrestRecordSetup extends HttpServlet{
	public String query = "SELECT * FROM arrest_records WHERE arrest_record_id = ?";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int arrest_record_id = Integer.parseInt(request.getParameter("arrest_record_id"));
		
		try {			
			Connection connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, arrest_record_id);
			
			try (ResultSet rs = statement.executeQuery()) {
				
				request.setAttribute("arrest_record_id", arrest_record_id);
				request.setAttribute("resultSet", rs);
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EditArrestRecord.jsp?criminal_id=" + arrest_record_id);
				rd.forward(request, response);
				
				connection.close();				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
	}
}
