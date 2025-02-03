<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Menu" %>
<%@ page import="com.tap.utility.Cart" %>

<!DOCTYPE html> <html lang="en"> <head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <title>Restaurant Menu</title> 
<link rel="stylesheet" href="menusss.css"> </head> <body> <!-- Navigation Bar --> <nav class="nav-bar">
 <div class="nav-container"> <a href="index.jsp" class="nav-logo"> <i class="fas fa-utensils"></i> FoodDelivery </a> <div class="nav-links"> <a href="home.jsp" class="nav-link"> <i class="fas fa-home"></i> Home </a> <a href="cart.jsp" class="nav-link"> <i class="fas fa-shopping-cart"></i> Cart </a> </div> </div> </nav>

<!-- Menu Grid -->
<div class="menu-grid">
    <%
        // Fetch the menu list from the session
        List<Menu> menus = (List<Menu>) session.getAttribute("menus");
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
    %>
    <div class="menu-item">
        <img src="<%= menu.getImagePath() != null ? menu.getImagePath() : "default-menu-image.jpg" %>" 
             alt="<%= menu.getItemName() %>" class="menu-image">
        <div class="menu-content">
            <h2 class="menu-name"><%= menu.getItemName() %></h2>
            <div class="menu-rating">★ <%= menu.getRatings() %></div>
            <p class="menu-description"><%= menu.getDescription() %></p>
            <div class="menu-footer">
                <p class="menu-price">₹<%= menu.getPrice() %></p>
                <form action="${pageContext.request.contextPath}/cartAction" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                    <input type="hidden" name="name" value="<%= menu.getItemName() %>">
                    <input type="hidden" name="price" value="<%= menu.getPrice() %>">
                    <label for="quantity-<%= menu.getMenuId() %>">Qty:</label>
                    <input type="number" id="quantity-<%= menu.getMenuId() %>" name="quantity" value="1" min="1">
                    <button type="submit" class="add-to-cart">Add to Cart</button>
                </form>
            </div>
        </div>
    </div>
    <%
            }
        } else {
    %>
    <p>No menu items available.</p>
    <%
        }
    %>
</div>
</body> </html>