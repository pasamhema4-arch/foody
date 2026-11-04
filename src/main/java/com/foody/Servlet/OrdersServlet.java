package com.foody.Servlet;

import java.io.IOException;
import java.util.List;

import com.foody.DAO.imp.OrderTableDAOImpl;
import com.foody.model.OrderTable;
import com.foody.model.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        user User = (user) session.getAttribute("User");

        if(User == null) {
            response.sendRedirect("login.html");
            return;
        }

        OrderTableDAOImpl dao = new OrderTableDAOImpl();

        List<OrderTable> orders =
                dao.getOrdersByUserId(User.getId());

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("orders.jsp")
               .forward(request, response);
    }
}
