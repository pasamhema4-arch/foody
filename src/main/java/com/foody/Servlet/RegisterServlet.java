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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String address=req.getParameter("address");
		String role=req.getParameter("role");
		
		String hashpw=BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		user User=new user(name,email,hashpw,address,role);
		
		userDAOimp UserDAOimp= new userDAOimp();
		
		int i =UserDAOimp.adduser(User);
		
		if(i == 1) {
		    System.out.println("Redirecting to login.html");
		    resp.sendRedirect("login.html");
		} else {
		    System.out.println("Registration Failed");
		    resp.sendRedirect("register.html");
		}
	}

}
