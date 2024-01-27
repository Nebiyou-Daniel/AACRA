package com.aacra.request;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

@WebServlet("/pages/viewYourRequests")
public class ViewYourRequests extends HttpServlet{
	
	public String searchQuery = "SELECT criminal_personal_info.criminal_id, criminal_personal_info.criminal_fname, "
			+ "criminal_personal_info.criminal_lname, record_requests.* "
			+ "FROM record_requests INNER JOIN criminal_personal_info ON "
			+ "record_requests.criminal_id=criminal_personal_info.criminal_id WHERE user_id = ?";
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_id = Integer.parseInt(request.getParameter("userId"));
        
		try {				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(searchQuery);
			
			statement.setInt(1, user_id);
			
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
