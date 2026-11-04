package com.foody.Servlet;

import java.io.IOException;

import com.foody.DAO.imp.MenuDAOImpl;
import com.foody.model.Menu;
import com.foody.model.cart;
import com.foody.model.cartItem;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    HttpSession session = req.getSession();

	    cart Cart = (cart) session.getAttribute("Cart");

	    if (Cart == null) {
	        Cart = new cart();
	        session.setAttribute("Cart", Cart);
	    }

	    String action = req.getParameter("action");

	    if ("add".equals(action)) {

	        int newrestaurantId = Integer.parseInt(req.getParameter("restaurantId"));

	        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

	        if (restaurantId == null) {
	            session.setAttribute("restaurantId", newrestaurantId);
	        } else if (!restaurantId.equals(newrestaurantId)) {
	            Cart = new cart();
	            session.setAttribute("Cart", Cart);
	            session.setAttribute("restaurantId", newrestaurantId);
	        }

	        addItemToCart(req, Cart);

	    } else if ("update".equals(action)) {

	        updateItemToCart(req, Cart);

	    } else if ("remove".equals(action)) {

	        removeItemToCart(req, Cart);

	    }

	    RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
	    rd.forward(req, resp);
	}
	private void removeItemToCart(HttpServletRequest req, cart cart) {
		  int menuId = Integer.parseInt(req.getParameter("menuId"));

		    cart.removeItem(menuId);
		
	}

	private void updateItemToCart(HttpServletRequest req, cart cart) {
		int menuId = Integer.parseInt(req.getParameter("menuId"));
	    int qty = Integer.parseInt(req.getParameter("quantity"));

	    if (qty <= 0) {
	        cart.removeItem(menuId);
	    } else {
	        cart.updateItem(menuId, qty);
	    }
		
	}

	private void addItemToCart(HttpServletRequest req, cart cart) {
		
		int menuId=Integer.parseInt(req.getParameter("menuId"));
		int qty=Integer.parseInt(req.getParameter("qty"));
		
		MenuDAOImpl menuDAOImpl=new MenuDAOImpl();
		Menu menu=menuDAOImpl.getMenu(menuId);
		
		cartItem CartItem=new cartItem(menu.getMenuId(),
				menu.getRestaurantId(), 
				menu.getItemName(), 
				menu.getPrice(),
				qty);
	
		System.out.println("Before Add: " + cart.getItems().size());

		cart.addItem(CartItem);

		System.out.println("After Add: " + cart.getItems().size());
		
	}

}
