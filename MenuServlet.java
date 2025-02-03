package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.daoImplementation.MenuDAOImplementation;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restaurantIdStr = req.getParameter("restaurantId");
        HttpSession session = req.getSession();

        if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
            int restaurantId = Integer.parseInt(restaurantIdStr);
            session.setAttribute("restaurantId", restaurantId);
        }

        MenuDAOImplementation menuDAOImplementation = new MenuDAOImplementation();
        List<Menu> menus;

        if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
            int restaurantId = Integer.parseInt(restaurantIdStr);
            menus = menuDAOImplementation.getMenuByRestaurant(restaurantId);
        } else {
            menus = menuDAOImplementation.getAllMenus();
        }

        session.setAttribute("menus", menus);
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}
