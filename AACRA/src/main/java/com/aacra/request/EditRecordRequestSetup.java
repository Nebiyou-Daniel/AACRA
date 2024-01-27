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

@WebServlet("/pages/editRecordRequestSetup")
public class EditRecordRequestSetup extends HttpServlet{
	
	public String query = "SELECT * FROM record_requests WHERE request_id = ?";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int request_id = Integer.parseInt(request.getParameter("request_id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		try {			
			Connection connection = DriverManager.getConnection(DatabaseUtility.DB_URL, DatabaseUtility.DB_USERNAME, DatabaseUtility.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, request_id);
			
			try (ResultSet rs = statement.executeQuery()) {
				
				request.setAttribute("resultSet", rs);
				request.setAttribute("fname", fname);
				request.setAttribute("lname", lname);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EditRecordRequest.jsp?request_id=" + request_id);
				rd.forward(request, response);
				
				connection.close();				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
	}
}
