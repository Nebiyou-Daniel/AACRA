package com.aacra.record;

import java.io.IOException;

import com.aacra.record.dao.RecordDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/deleteBookingRecord")
public class DeleteBookingRecord extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int booking_info_id = Integer.parseInt(request.getParameter("booking_info_id"));
    	
    	RecordDao record = new RecordDao();
    	
    	boolean success = record.deleteBookingInfo(booking_info_id);
    	
    	if (success) {
    		String deleteProcedure = "The booking record has been successfully deleted.";
    		request.setAttribute("deleteProcedure", deleteProcedure);
    		
    		request.getRequestDispatcher("/pages/other/RequestDelete.jsp").forward(request, response);
    	} else {
    		String deleteProcedure = "The booking record couldn't be deleted.";
    		request.setAttribute("deleteProcedure", deleteProcedure);
    		
    		request.getRequestDispatcher("/pages/other/RequestDelete.jsp").forward(request, response);
    	}
		
	}
}
