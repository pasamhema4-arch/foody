package com.foody.Servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.foody.DAO.imp.userDAOimp;
import com.foody.model.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    HttpSession Session = req.getSession();

	    userDAOimp dao = new userDAOimp();
	    user User = dao.getUserByEmail(email);

	    if (User != null && BCrypt.checkpw(password, User.getPassword())) {

	        Session.setAttribute("User", User);
	        System.out.println("Login Session ID: " + Session.getId());
	        System.out.println("Session User: " + Session.getAttribute("User"));

	        System.out.println("Login Successful");
	        System.out.println(User);
	        System.out.println(Session.getAttribute("User"));

	        resp.sendRedirect("restaurants");

	    } else {

	        System.out.println("Invalid Email or Password");
	        resp.sendRedirect("login.html");
	    }
	}
    
}