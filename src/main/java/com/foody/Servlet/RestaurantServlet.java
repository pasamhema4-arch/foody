package com.foody.Servlet;

import java.io.IOException;
import java.util.List;

import com.foody.DAO.RestaurantDAO;
import com.foody.DAO.imp.RestaurantDAOImpl;
import com.foody.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("RestaurantServlet is called");

        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();
        for (Restaurant restaurant : restaurantList) {
        	System.out.println(restaurant);
        }

        request.setAttribute("restaurantList", restaurantList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("restaurant.jsp");
        dispatcher.forward(request, response);
    }
}