package com.aacra.request;

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

@WebServlet("/pages/viewAllRecordRequests")
public class ViewAllRecordRequests extends HttpServlet{
	
	public String searchQuery = "SELECT criminal_personal_info.criminal_id, criminal_personal_info.criminal_fname, "
			+ "criminal_personal_info.criminal_lname, record_requests.* "
			+ "FROM record_requests INNER JOIN criminal_personal_info ON "
			+ "record_requests.criminal_id=criminal_personal_info.criminal_id WHERE status = 'pending'";
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        
		try {				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(searchQuery);
						
			try (ResultSet rs = statement.executeQuery()) {
				
				request.setAttribute("resultSet", rs);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/ShowAllRecordRequests.jsp");
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
