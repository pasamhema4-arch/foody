package com.foody.Servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

    
    	@Override
    	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	        throws ServletException, IOException {

    	    HttpSession session = request.getSession(false);

    	    if (session != null) {
    	        System.out.println("Profile Session ID: " + session.getId());
    	        System.out.println("Profile User: " + session.getAttribute("User"));
    	    } else {
    	        System.out.println("No Session Found");
    	    }

    	    RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
    	    rd.forward(request, response);
    	}
}
