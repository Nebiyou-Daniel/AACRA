package com.aacra.auth;

import java.io.IOException;

import com.aacra.auth.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/AgreementServlet")
public class AgreementServlet extends HttpServlet{
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String agreeToTerms = request.getParameter("agreeToTerms");
        String agreeToLegalCompliance = request.getParameter("agreeToLegalCompliance");

        // Check if the user agreed to both terms
        if ("on".equals(agreeToTerms) && "on".equals(agreeToLegalCompliance)) {

        	request.getRequestDispatcher("/pages/SignupPage.jsp").forward(request, response);
        	
        } else {
        	response.getWriter().println("USER DIDN'T AGREE");
        }
    }
}
