package com.aacra.record;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aacra.record.dao.RecordDao;
import com.aacra.record.model.ArrestRecord;
import com.aacra.record.model.ConvictionRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/editConvictionRecord")
public class EditConvictionRecord extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        int conviction_record_id = Integer.parseInt(request.getParameter("conviction_record_id"));
        int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
        Date date_of_conviction = Date.valueOf(request.getParameter("date_of_conviction"));
    	String convicted_offenses = request.getParameter("convicted_offenses");
    	String sentencing_details = request.getParameter("sentencing_details");
    	String probation_parole_status = request.getParameter("probation_parole_status");
    	
    	ConvictionRecord cr = new ConvictionRecord();
    	cr.setCriminalId(criminal_id);
    	cr.setConvictionRecordId(conviction_record_id);
    	cr.setDateOfConviction(date_of_conviction);
    	cr.setCriminalOffenses(convicted_offenses);
    	cr.setSentencingDetails(sentencing_details);
    	cr.setParoleAndProbationStatus(probation_parole_status);
    	
    	RecordDao record = new RecordDao();
        	
    	boolean success = record.editConvictionRecord(cr);
    	
    	if (success) {
    		request.getRequestDispatcher("/pages/showCriminalRecord?criminal_id=" + criminal_id).forward(request, response);
    	} else {
    		
    		request.getRequestDispatcher("/addRecordError").forward(request, response);
    	}

		
	}
}
