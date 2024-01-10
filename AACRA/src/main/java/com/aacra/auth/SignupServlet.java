package com.aacra.auth;

import java.io.IOException;
import java.io.PrintWriter;

import com.aacra.auth.dao.UserDao;
import com.aacra.auth.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/signup")
public class SignupServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User();
        user.setFname(fname);
        user.setLname(lname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        
        UserDao userDao = new UserDao();
        
        // Check if the email is available, all form data are in correct formats, 
        // and adhere to conditions they were given.     
        boolean success = userDao.checkUserData(user, request.getParameter("confirmPassword"));

        if (success) {
        	// Use the role attribute to specify the next servlet the user data will go to.
        	String urlPage;
        	
        	if ("regular".equals(user.getRole())) {
        		urlPage = "/regularUserSignup";
        		
            	request.setAttribute("userData", user);
            	request.getRequestDispatcher(urlPage).forward(request, response);        		
        		
        	} else if ("officer".equals(user.getRole())) {
        		urlPage = "/pages/OfficerSignupPage.jsp";
        		
            	request.setAttribute("userData", user);
            	request.getRequestDispatcher(urlPage).forward(request, response);   
            	
        	} else if ("analyst".equals(user.getRole())) {
        		urlPage = "/pages/AnalystSignupPage.jsp";
        		
            	request.setAttribute("userData", user);
            	request.getRequestDispatcher(urlPage).forward(request, response);
            	
        	} else {
	            PrintWriter out = response.getWriter();
	            out.print("<h1> USER SIGNED UP SUCCESSFULLY </h1>");
	            out.print("<h2> However, unable to redirect to anywhere </h2>");
        		
        	}

        } else {
        	// Redirect to sign up error servlet to check what the problem was that prevented the user to sign up
        	
        	request.setAttribute("userData", user);
        	request.setAttribute("confirmPassword", request.getParameter("confirmPassword"));
        	request.getRequestDispatcher("/signupError").forward(request, response);
        }
	}
}
