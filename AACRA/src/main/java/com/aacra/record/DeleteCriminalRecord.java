package com.aacra.record;

import java.io.IOException;

import com.aacra.record.dao.RecordDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/deleteCriminaRecord")
public class DeleteCriminalRecord extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
    	
    	RecordDao record = new RecordDao();
    	
    	boolean success = record.deleteCriminalRecord(criminal_id);
    	
    	if (success) {
    		String deleteProcedure = "The record has been successfully deleted.";
    		request.setAttribute("deleteProcedure", deleteProcedure);
    		
    		request.getRequestDispatcher("/pages/other/RequestDelete.jsp").forward(request, response);
    	} else {
    		String deleteProcedure = "The record couldn't be deleted.";
    		request.setAttribute("deleteProcedure", deleteProcedure);
    		
    		request.getRequestDispatcher("/pages/other/RequestDelete.jsp").forward(request, response);
    	}
		
	}
}
