package com.aacra.auth;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.aacra.auth.dao.UserDao;
import com.aacra.auth.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/pages/officerSignup")
@MultipartConfig
public class OfficerSignupServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Create a new user object to house all data entered earlier
		User user = new User();
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setRole(request.getParameter("role"));
		// New data entered recently
		int officerWereda = Integer.parseInt(request.getParameter("wereda"));
		String location = request.getParameter("workLocation");		
		Part filePart = request.getPart("officerIdPhoto");
		InputStream fileContent = filePart.getInputStream();

		
        UserDao userDao = new UserDao();
            
        boolean success = userDao.addOfficer(user, fileContent, location, officerWereda);

        if (success) {
        	request.setAttribute("userData", user);
        	HttpSession session = request.getSession();
        	session.setAttribute("userData", user);
        	session.setMaxInactiveInterval(7200);
        	request.getRequestDispatcher("/main").forward(request, response);

        } else {
        	
        	PrintWriter out = response.getWriter();
        	out.print("<h2>For some reason, it is unable to sign me up. Perhaps the sign up error servlet thing should be implemented here too.</h2>");
        }
    }
}
