package com.aacra.record;

import java.io.IOException;
import java.io.InputStream;

import com.aacra.record.dao.RecordDao;
import com.aacra.record.model.BookingRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/pages/addBookingInfo")
@MultipartConfig
public class AddBookingInfo extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
        int arrest_record_id = Integer.parseInt(request.getParameter("arrest_record_id")); 
        int criminal_id = Integer.parseInt(request.getParameter("criminal_id")); 
        Part filePart = request.getPart("mugshot");
		InputStream mugshot = filePart.getInputStream();
		String booking_number = request.getParameter("booking_number");
    	
    	BookingRecord bi = new BookingRecord();
    	bi.setArrestRecordId(arrest_record_id);
    	bi.setMugshot(mugshot);
    	bi.setBookingNumber(booking_number);
    	bi.setCriminalId(criminal_id);
    	
    	RecordDao record = new RecordDao();
    	
    	boolean success = record.addBookingInfo(bi);
    	
    	if (success) {
    		request.setAttribute("fname", request.getParameter("fname"));
    		request.setAttribute("lname", request.getParameter("lname"));
    		request.setAttribute("criminal_id", criminal_id);
    		
    		request.getRequestDispatcher("/pages/AdditionalInfo.jsp").forward(request, response); 
    	} else {
    		request.setAttribute("criminalData", bi);
    		request.getRequestDispatcher("/addRecordError").forward(request, response);
    	}
	
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
}
