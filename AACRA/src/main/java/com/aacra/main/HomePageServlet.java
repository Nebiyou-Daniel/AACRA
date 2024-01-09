package com.aacra.main;

import java.io.IOException;
import java.io.PrintWriter;

import com.aacra.auth.dao.UserDao;
import com.aacra.auth.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class HomePageServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getAttribute("userData");
		
		PrintWriter out = response.getWriter();
		out.println("<h1> You have successfully signed up </h1>");
		out.print("<h2>" + user.getFname() + " " + user.getLname() + "</h2>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
