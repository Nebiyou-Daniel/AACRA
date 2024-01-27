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
import jakarta.servlet.http.HttpSession;

@WebServlet("/regularUserSignup")
public class RegularUserSignupServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getAttribute("userData");

        UserDao userDao = new UserDao();
        
        // Check if the email is available, all form data are in correct formats, 
        // and adhere to conditions they were given.     
        boolean success = userDao.addUser(user);

        if (success) {
        	request.setAttribute("userData", user);
        	HttpSession session = request.getSession();
        	session.setAttribute("userData", user);
        	session.setMaxInactiveInterval(7200);
        	request.getRequestDispatcher("/main").forward(request, response);

        } else {
        	
        	PrintWriter out = response.getWriter();
        	out.println("<h2>For some reason, it is unable to sign me up. Perhaps the sign up error servlet thing should be implemented here too.</h2>");
        }
    }
}
