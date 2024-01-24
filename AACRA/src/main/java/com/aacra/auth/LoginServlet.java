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

@WebServlet("/pages/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        
        user.setEmail(email);
        user.setPassword(password);
        
        UserDao userDao = new UserDao();
        
        // Check if the email is available, all form data are in correct formats, 
        // and adhere to conditions they were given.     
        User userData = userDao.validateUser(user);

        if (userData != null) {
        	
        	request.getSession().setAttribute("userData", userData);
        	request.getRequestDispatcher("/main").forward(request, response);        	

        } else {
        	// Redirect to sign up error servlet to check what the problem was that prevented the user to sign up
        	
        	request.setAttribute("userData", user);
        	request.getRequestDispatcher("/loginError").forward(request, response);
        }
	}
}
