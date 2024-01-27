package com.aacra.request;

import java.io.IOException;
import java.sql.Date;

import com.aacra.request.dao.RequestDao;
import com.aacra.request.model.RecordRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/requestForRestrictedRecord")
public class RequestForRestrictedRecordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
        int criminal_id = Integer.parseInt(request.getParameter("criminal_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
    	String reason = request.getParameter("reason");
    	boolean for_business = Boolean.parseBoolean(request.getParameter("for_business"));
    	String business_name = request.getParameter("business_name");
    	String business_address = request.getParameter("business_address");
    	
    	RecordRequest rr = new RecordRequest();
    	rr.setCriminalId(criminal_id);
    	rr.setUserId(user_id);
    	rr.setForBusiness(for_business);
    	rr.setReason(reason);
    	rr.setBusinessName(business_name);
    	rr.setBusinessAddress(business_address);
    	
    	RequestDao recordRequest = new RequestDao();
    	
    	boolean isRequestMade = recordRequest.checkRecordRequest(user_id, criminal_id);
    	
    	if (isRequestMade) {
    		
    		String requestExists = "You have made a request to access restricted data belonging to " + request.getParameter("fname") +
    				" " +request.getParameter("lname") + ". Please wait until your request has been reviewed";

    		request.setAttribute("requestExists", requestExists);
    		request.getRequestDispatcher("/pages/errorPages/BadNews.jsp").forward(request, response); 
    		
    	} else {
    		
    		boolean success;
    		if (for_business) {
    			success = recordRequest.addRecordRequestForBusiness(rr);
    		} else {
    			success = recordRequest.addRecordRequest(rr);    			    			
    		}
    		
    		if(success) {
        		String requestMade = "You have successfully made a request to access the restricted records of " + request.getParameter("fname") +
        				" " +request.getParameter("lname") + ". Please wait until your request gets reviewed. It may take a some time.";

        		request.setAttribute("requestMade", requestMade);
    			
    			request.getRequestDispatcher("/pages/other/RecordRequestMade.jsp").forward(request, response);     			
    		}
    	}
	}
}
