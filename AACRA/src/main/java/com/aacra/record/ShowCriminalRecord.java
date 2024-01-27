package com.aacra.record;

import java.io.IOException;
import java.util.ArrayList;

import com.aacra.auth.model.User;
import com.aacra.record.dao.RecordDao;
import com.aacra.record.model.ArrestRecord;
import com.aacra.record.model.BookingRecord;
import com.aacra.record.model.ConvictionRecord;
import com.aacra.record.model.CriminalPersonalRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/pages/showCriminalRecord")
public class ShowCriminalRecord extends HttpServlet{
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		User userData = (User) request.getSession().getAttribute("userData");
		
		int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
		RecordDao record = new RecordDao();
		
		CriminalPersonalRecord cpr = record.getCriminalPersonalRecordById(criminal_id);
		ArrayList<ArrestRecord> ar = record.getArrestRecordById(criminal_id);
		ArrayList<BookingRecord> bi = record.getBookingRecordById(criminal_id);
//		ArrayList<BookingRecord> bi = record.getBookingRecordById(criminal_id, response.getOutputStream());
		ArrayList<ConvictionRecord> cr = record.getConvictionRecordById(criminal_id);
		
		request.setAttribute("criminalPersonalRecord", cpr);
		request.setAttribute("arrestRecords", ar);
		request.setAttribute("bookingRecords", bi);
		request.setAttribute("convictionRecords", cr);
		request.setAttribute("criminal_id", criminal_id);
		
		HttpSession session = request.getSession();
		session.setAttribute("userData", userData);
		request.getRequestDispatcher("/pages/ShowCriminalRecord.jsp").forward(request, response);
		
	}
		
}
