package com.tap.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tap.daoImplementation.OrderDAOImplementation;
import com.tap.daoImplementation.OrderItemDAOImplementation;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.utility.Cart;
import com.tap.utility.CartItem;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Check if the user is logged in by verifying if userId is present in the session
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            // If user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return; // Stop further execution
        }

        // Proceed with checkout if user is logged in
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        Cart cart = (Cart) session.getAttribute("cart");

        if (restaurantId == null || cart == null) {
            // Handle case where restaurantId or cart is not available (redirect to some error page or back to cart)
            response.sendRedirect("cart.jsp");
            return;
        }

        // Retrieve form data (receiver's name, phone, etc.)
        String receiverName = request.getParameter("receiverName");
        String receiverPhone = request.getParameter("receiverPhone");
        String customerAddress = request.getParameter("customerAddress");
        String paymentMethod = request.getParameter("paymentMethod");

        // Calculate total amount
        double totalAmount = cart.calculateTotalPrice();

        // Create and save the order
        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending"); // Default status
        order.setPaymentMode(paymentMethod);

        OrderDAOImplementation orderDAO = new OrderDAOImplementation();
        int orderId = orderDAO.addOrder(order); // Save the order and get the generated ID

        // Save order items
        OrderItemDAOImplementation orderItemDAO = new OrderItemDAOImplementation();

        // Loop through the cart items and insert them as order items
        for (CartItem cartItem : cart.getItems().values()) {
            // Calculate the total price for this item (price * quantity)
            double itemTotalPrice = cartItem.getPrice() * cartItem.getQuantity();

            // Create order item and set the total price
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setMenuId(cartItem.getMenuId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(itemTotalPrice); // Set total price

            // Insert the order item
            orderItemDAO.orderItem(orderItem);
        }

        // Clear the cart and session data after successful checkout
        session.removeAttribute("cart");

        // Set attributes to forward to orderConfirmation.jsp
        request.setAttribute("receiverName", receiverName);
        request.setAttribute("receiverPhone", receiverPhone);
        request.setAttribute("customerAddress", customerAddress);
        request.setAttribute("paymentMethod", paymentMethod);
        
        // Forward to order confirmation page
        request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
    }
}
