package com.aacra.record;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aacra.record.dao.RecordDao;
import com.aacra.record.model.ArrestRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/addArrestRecord")
public class AddArrestRecord extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
        int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
        String date_time_arrest = request.getParameter("date_time_arrest");
        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        	LocalDateTime dateTimeArrest = LocalDateTime.parse(date_time_arrest, formatter);
        	
        	String arresting_agency = request.getParameter("arresting_agency");
        	String arresting_officer = request.getParameter("arresting_officer");
        	String charges = request.getParameter("charges");
        	String arrest_location = request.getParameter("arrest_location");
        	
        	ArrestRecord ar = new ArrestRecord();
        	ar.setCriminalId(criminal_id);
        	ar.setDateTimeArrest(dateTimeArrest);
        	ar.setArrestingAgency(arresting_agency);
        	ar.setArrestingOfficer(arresting_officer);
        	ar.setCharges(charges);
        	ar.setArrestLocation(arrest_location);
        	
        	RecordDao record = new RecordDao();
        	
        	boolean success = record.addArrestRecord(ar);
        	
        	if (success) {
        		request.setAttribute("fname", request.getParameter("fname"));
        		request.setAttribute("lname", request.getParameter("lname"));
        		request.setAttribute("criminal_id", criminal_id);
        		
        		request.getRequestDispatcher("/fetchCriminalId").forward(request, response); 
        	} else {
        		request.setAttribute("criminalData", ar);
        		request.getRequestDispatcher("/addRecordError").forward(request, response);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
}
