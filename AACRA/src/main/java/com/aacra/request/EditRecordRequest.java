package com.aacra.request;

import java.io.IOException;

import com.aacra.request.dao.RequestDao;
import com.aacra.request.model.RecordRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/editRecordRequest")
public class EditRecordRequest extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int request_id = Integer.parseInt(request.getParameter("request_id"));
        int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
    	String reason = request.getParameter("reason");
    	boolean for_business = Boolean.parseBoolean(request.getParameter("for_business"));
    	String business_name = request.getParameter("business_name");
    	String business_address = request.getParameter("business_address");
    	
    	RecordRequest rr = new RecordRequest();
    	rr.setRequestId(request_id);
    	rr.setCriminalId(criminal_id);
    	rr.setUserId(user_id);
    	rr.setForBusiness(for_business);
    	rr.setReason(reason);
    	rr.setBusinessName(business_name);
    	rr.setBusinessAddress(business_address);
    	
    	RequestDao record = new RequestDao();
    	
    	boolean success = record.editRecordRequest(rr);
    	
    	if (success) {
    		request.getRequestDispatcher("/pages/viewRecordRequest?user_id=" + user_id + "&criminal_id=" + criminal_id).forward(request, response);
    	} else {
    		request.setAttribute("criminalData", rr);
    		request.getRequestDispatcher("/addRecordError").forward(request, response);
    	}
		
	}
}
