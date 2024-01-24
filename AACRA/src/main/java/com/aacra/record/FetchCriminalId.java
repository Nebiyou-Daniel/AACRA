package com.aacra.record;

import java.io.IOException;

import com.aacra.record.dao.RecordDao;
import com.aacra.record.model.CriminalPersonalRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fetchCriminalId")
public class FetchCriminalId extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		CriminalPersonalRecord cpr = (CriminalPersonalRecord) request.getAttribute("criminalData");
		
		// Fetch Kebele Id, Address and Phone Number since they are the most unique things a criminal has  
        String kebeleId = cpr.getKebeleId();
        String address = cpr.getAddress();
        String phoneNumber = cpr.getPhoneNumber();
        
        // Fetch the first and last names as last resort in case the criminal has none of the above
        String fname = cpr.getFname();
        String lname = cpr.getLname();
        
        RecordDao record = new RecordDao();
        int criminalId = record.getCriminalId(kebeleId, address, phoneNumber, fname, lname);
        
        if (criminalId != 0) {
        	request.setAttribute("criminalData", cpr);
        	request.setAttribute("criminal_id", criminalId);
        	request.getRequestDispatcher("/pages/AdditionalInfo.jsp").forward(request, response); 
        	
        } else {
        	response.getWriter().println("EXCUSE ME!?");
        }

		
	}
}
