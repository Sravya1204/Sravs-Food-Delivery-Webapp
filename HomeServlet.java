package com.tap.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.tap.daoImplementation.RestaurantDAOImplementation;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchQuery = req.getParameter("search");
        RestaurantDAOImplementation restaurantDAO = new RestaurantDAOImplementation();
        
        List<Restaurant> restaurants;
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            restaurants = restaurantDAO.searchRestaurants(searchQuery.trim().toLowerCase());
        } else {
            restaurants = restaurantDAO.getAllRestaurants();
        }

        HttpSession session = req.getSession();
        session.setAttribute("restaurants", restaurants);

        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        dispatcher.forward(req, resp);
    }
}
