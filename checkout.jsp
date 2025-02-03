<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.utility.Cart" %>
<%@ page import="com.tap.utility.CartItem" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="./checksout.css">
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <h1>Checkout</h1>
        <div class="nav-buttons">
            <a href="home.jsp" class="nav-btn">🏠 Home</a>
            <a href="cart.jsp" class="nav-btn">🛒 Cart</a>
        </div>
    </div>

    <div class="checkout-container">
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null || cart.getItems().isEmpty()) {
        %>
            <p>Your cart is empty. <a href="menu.jsp">Go back to menu</a></p>
        <%
            } else {
        %>
        <div class="total-display">
            <h3>Grand Total: ₹<%= cart.calculateTotalPrice() %></h3>
        </div>

        <form action="checkout" method="post">
            <div class="form-group">
                <label for="receiverName">Receiver Name</label>
                <input type="text" id="receiverName" name="receiverName" required>
            </div>

            <div class="form-group">
                <label for="receiverPhone">Phone Number</label>
                <input type="text" id="receiverPhone" name="receiverPhone" required>
            </div>

            <div class="form-group">
                <label for="customerAddress">Address</label>
                <textarea id="customerAddress" name="customerAddress" rows="3" required></textarea>
            </div>

            <div class="form-group">
                <label for="paymentMethod">Payment Method</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="Cash on Delivery">Cash on Delivery</option>
                    <option value="Credit Card">Credit Card</option>
                    <option value="UPI">UPI</option>
                </select>
            </div>

            <div class="button-group">
                <button type="submit" class="confirm-btn">Confirm Order</button>
                <button type="button" class="cancel-btn" onclick="window.location.href='cart.jsp'">Cancel</button>
            </div>
        </form>

        <%
            }
        %>
    </div>
</body>
</html>