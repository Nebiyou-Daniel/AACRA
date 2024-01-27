package com.aacra.request;

import java.io.IOException;

import com.aacra.request.dao.RequestDao;
import com.aacra.request.model.RecordRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/processRecordRequest")
public class ProcessRecordRequest extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int request_id = Integer.parseInt(request.getParameter("request_id"));
		String status = request.getParameter("status");
    	
    	RequestDao record = new RequestDao();
    	
    	boolean success = record.processRecordRequest(request_id, status);
    	
    	if (success) {
    		request.getRequestDispatcher("/pages/MainPage.jsp").forward(request, response);
    	} else {
    		
    		request.getRequestDispatcher("/addRecordError").forward(request, response);
    	}
		
	}
}
