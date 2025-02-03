<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.utility.Cart" %>
<%@ page import="com.tap.utility.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="addcar.css">
</head>
<body>
    <!-- Home Button -->
    <a href="home.jsp" class="home-btn">Home</a>

    <!-- Cart Container -->
    <div class="cart-container">
        <h1>Your Cart</h1>

        <!-- Display error message if any -->
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <p style="color: red; text-align: center;"><%= errorMessage %></p>
        <% 
            } 
        %>

        <table>
            <thead>
                <tr>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Cart cart = (Cart) session.getAttribute("cart");
                    Integer restaurantId = (Integer) session.getAttribute("restaurantId");

                    if (cart != null && !cart.getItems().isEmpty()) {
                        for (CartItem item : cart.getItems().values()) {
                %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td>₹<%= item.getPrice() %></td>

                    <td>
                        <!-- Decrease Quantity -->
                        <form action="${pageContext.request.contextPath}/cartAction" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                            <button type="submit" name="quantity" value="<%= item.getQuantity() - 1 %>" class="quantity-btn">-</button>
                        </form>

                        <!-- Display Current Quantity -->
                        <input type="text" value="<%= item.getQuantity() %>" class="quantity-input" disabled>

                        <!-- Increase Quantity -->
                        <form action="${pageContext.request.contextPath}/cartAction" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                            <button type="submit" name="quantity" value="<%= item.getQuantity() + 1 %>" class="quantity-btn">+</button>
                        </form>
                    </td>

                    <!-- Total Price for the Item -->
                    <td>₹<%= item.getPrice() * item.getQuantity() %></td>

                    <!-- Remove Item Button -->
                    <td>
                        <form action="${pageContext.request.contextPath}/cartAction" method="post">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                            <button type="submit" class="remove-btn">Remove</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="5">Your cart is empty.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>

        <!-- Total Section -->
        <div class="total-container">
            <p>Total: ₹<%= cart != null ? cart.calculateTotalPrice() : 0 %></p>
        </div>

        <!-- Buttons for Adding More Items or Checkout -->
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/menu.jsp">
                <button class="checkout-btn">Add more items</button>
            </a>
        </div>

        <div style="text-align: center;">
            <form action="login.jsp" method="post">
                <button type="submit" class="checkout-btn">Proceed to Checkout</button>
            </form>
        </div>
    </div>
</body>
</html>
