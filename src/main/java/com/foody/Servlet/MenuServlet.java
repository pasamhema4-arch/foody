package com.foody.Servlet;

import java.io.IOException;
import java.util.List;

import com.foody.DAO.imp.MenuDAOImpl;
import com.foody.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	MenuDAOImpl menuDAOImpl=new MenuDAOImpl();
    	int restaurantId =Integer.parseInt(request.getParameter("restaurantId"));
    	List<Menu> menuList = menuDAOImpl.getMenuByRestaurantId(restaurantId);
    	for(Menu menu :  menuList) {
    		System.out.println(menu);
    	}
    	request.setAttribute("menuList", menuList);
    	
    	RequestDispatcher rd=request.getRequestDispatcher("menu.jsp");
    	rd.forward(request, response);
    }
}
