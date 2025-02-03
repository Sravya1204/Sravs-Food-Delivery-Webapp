package com.tap.servlets;

import java.io.IOException;
import com.tap.utility.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cartAction")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Retrieve or initialize the cart
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Get form parameters
        String action = req.getParameter("action");
        int menuId = Integer.parseInt(req.getParameter("menuId"));

        Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        if (restaurantId == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required.");
            return;
        }

        if ("add".equals(action)) {
            String itemName = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            cart.addCartItem(restaurantId, menuId, itemName, price, quantity);
        } else if ("remove".equals(action)) {
            cart.removeCartItem(menuId);
        } else if ("update".equals(action)) {
            int newQuantity = Integer.parseInt(req.getParameter("quantity"));
            cart.updateCartItem(menuId, newQuantity);
        }

        // Update the cart item count in the session
        session.setAttribute("cartItemCount", cart.getTotalItems());

        // Redirect to the cart page after performing the action
        resp.sendRedirect(req.getContextPath() + "/cart.jsp");
    }
}
