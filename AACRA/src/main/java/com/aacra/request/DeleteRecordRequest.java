package com.aacra.request;

import java.io.IOException;

import com.aacra.request.dao.RequestDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/deleteRecordRequest")
public class DeleteRecordRequest extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int request_id = Integer.parseInt(request.getParameter("request_id"));
    	
    	RequestDao record = new RequestDao();
    	
    	boolean success = record.deleteRecordRequest(request_id);
    	
    	if (success) {
    		String deleteProcedure = "Your request for restricted record has been successfully deleted.";
    		request.setAttribute("deleteProcedure", deleteProcedure);
    		
    		request.getRequestDispatcher("/pages/other/RequestDelete.jsp").forward(request, response);
    	} else {
    		String deleteProcedure = "Your request for restricted record couldn't be deleted.";
    		request.setAttribute("deleteProcedure", deleteProcedure);
    		
    		request.getRequestDispatcher("/pages/other/RequestDelete.jsp").forward(request, response);
    	}
		
	}
}
