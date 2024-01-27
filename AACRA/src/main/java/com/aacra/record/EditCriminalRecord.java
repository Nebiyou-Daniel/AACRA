package com.aacra.record;

import java.io.IOException;
import java.sql.Date;

import com.aacra.record.dao.RecordDao;
import com.aacra.record.model.CriminalPersonalRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/editCriminalRecord")
public class EditCriminalRecord extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// Criminal Personal Info
		int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
        String criminal_fname = request.getParameter("criminal_fname");
        String criminal_lname = request.getParameter("criminal_lname");
        Date date_of_birth = Date.valueOf(request.getParameter("date_of_birth"));
        String gender = request.getParameter("gender");
        String race_ethnicity = request.getParameter("race_ethnicity");
        String kebele_id = request.getParameter("kebele_id");
        String address = request.getParameter("address");
        String phone_number = request.getParameter("phone_number");    

        
        CriminalPersonalRecord cpr = new CriminalPersonalRecord();
        cpr.setAddress(address);
        cpr.setCriminalId(criminal_id);
        cpr.setDateOfBirth(date_of_birth);
        cpr.setFname(criminal_fname);
        cpr.setGender(gender);
        cpr.setKebeleId(kebele_id);
        cpr.setLname(criminal_lname);
        cpr.setPhoneNumber(phone_number);
        cpr.setRaceAndEthnicity(race_ethnicity);

        RecordDao record = new RecordDao();
        
        boolean success = record.editCriminalRecord(cpr);
        
        if (success) {
    		request.getRequestDispatcher("/pages/showCriminalRecord?criminal_id=" + criminal_id).forward(request, response);

        } else {
        	request.getRequestDispatcher("/addRecordError").forward(request, response);
        }

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
}
