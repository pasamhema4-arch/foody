package com.foody.Servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.foody.DAO.imp.OrderItemDAOImpl;
import com.foody.DAO.imp.OrderTableDAOImpl;
import com.foody.model.OrderItem;
import com.foody.model.OrderTable;
import com.foody.model.cart;
import com.foody.model.cartItem;
import com.foody.model.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       // RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
        //rd.forward(request, response);
    	
    	HttpSession session= request.getSession();
    	user User=(user)session.getAttribute("User");
    	Integer restaurantId=(Integer)session.getAttribute("restaurantId");
    	cart Cart=(cart)session.getAttribute("Cart");
    	
    	Double grandTotal = (Double) session.getAttribute("grandTotal");

    	if (grandTotal == null) {
    	    grandTotal = 0.0;
    	}
    	if(User != null) 
    	{
    		if(Cart != null && !Cart.getItems().isEmpty()) {
    			int userId=User.getId();
    			String paymentmode=request.getParameter("paymentMode");
    			OrderTable orderTable=new OrderTable(userId, restaurantId,new Timestamp(System.currentTimeMillis()),grandTotal , "pending", paymentmode);  
    			
    			OrderTableDAOImpl OrderTableDAOImpl=new OrderTableDAOImpl();
    			int orderId=OrderTableDAOImpl.addOrder(orderTable);
    			
    			for(cartItem cartItem : Cart.getItems().values()) {
    				int menuid=cartItem.getMenuId();
    				int qty=cartItem.getQty();
    				double price=cartItem.getPrice();
    				
    				OrderItem orderItem=new OrderItem( orderId, menuid, qty, price);
    				OrderItemDAOImpl OrderItemDAOImpl=new OrderItemDAOImpl();
    				OrderItemDAOImpl.addOrderItem(orderItem);
    			}
    			session.removeAttribute("restaurantId");
    			session.removeAttribute("Cart");
    			session.removeAttribute("grandTotal");
    			
    			response.sendRedirect("orderConfirmation.jsp");
    		}
    	}
    	else 
    	{
    		RequestDispatcher rd=request.getRequestDispatcher("login.html");
    		rd.forward(request,response);
    	}
    	
    }
    
}