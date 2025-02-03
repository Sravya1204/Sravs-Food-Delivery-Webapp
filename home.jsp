<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Restaurant" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodDelivery - Delicious Food at Your Doorstep</title>
    <link rel="stylesheet" href="./homeres.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="/" class="navbar-logo">
                <i class="fas fa-utensils"></i>
                <span>FoodDelivery</span>
            </a>
            <div class="navbar-links">
                <a href="cart.jsp" class="navbar-link">
                    <i class="fas fa-shopping-cart"></i>
                    <span>Cart</span>
                </a>
            </div>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero-section">
        <div class="hero-overlay"></div>
        <div class="hero-container">
            <h1>Delicious Food Delivered</h1>
            <p>Order from the finest restaurants in your neighborhood</p>
            <form action="home" method="GET" class="search-form">
                <div class="search-container">
                    <i class="fas fa-search search-icon"></i>
                    <input type="text" 
                           name="search" 
                           class="search-bar" 
                           placeholder="Search for restaurants or cuisines..." 
                           value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
                    <button type="submit">Search</button>
                </div>
            </form>
        </div>
    </section>

    <!-- Restaurant Grid -->
    <section class="restaurant-section">
        <div class="section-header">
            <h2>Popular Restaurants</h2>
            <p>Discover the best food & drinks near you</p>
        </div>
        <div class="restaurant-grid">
            <%
                List<Restaurant> restaurants = (List<Restaurant>) session.getAttribute("restaurants");
                String searchQuery = request.getParameter("search");

                if (restaurants != null && !restaurants.isEmpty()) {
                    boolean found = false;
                    for (Restaurant restaurant : restaurants) {
                        if (searchQuery == null || searchQuery.trim().isEmpty() ||
                            restaurant.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                            restaurant.getCuisineType().toLowerCase().contains(searchQuery.toLowerCase())) {
                            found = true;
            %>
            <div class="restaurant-card">
                <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="restaurant-link">
                    <div class="image-container">
                        <img src="<%= restaurant.getImagePath() != null ? restaurant.getImagePath() : "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3" %>" 
                             alt="<%= restaurant.getName() %>" 
                             class="restaurant-image">
                        <div class="quick-view">
                            <span>View Menu</span>
                        </div>
                    </div>
                    <div class="card-content">
                        <div class="restaurant-info">
                            <h3 class="restaurant-name"><%= restaurant.getName() %></h3>
                            <div class="tags">
                                <span class="cuisine-tag"><%= restaurant.getCuisineType() %></span>
                                <span class="status <%= restaurant.getIsActive() ? "open" : "closed" %>">
                                    <%= restaurant.getIsActive() ? "Open Now" : "Closed" %>
                                </span>
                            </div>
                        </div>
                        <div class="details">
                            <div class="rating">
                                <span class="rating-badge">
                                    <i class="fas fa-star"></i>
                                    <%= restaurant.getRating() %>
                                </span>
                            </div>
                            <div class="delivery-info">
                                <i class="fas fa-clock"></i>
                                <span><%= restaurant.getEstimateArrivalTime() %> mins</span>
                            </div>
                        </div>
                        <p class="address"><i class="fas fa-map-marker-alt"></i> <%= restaurant.getAddress() %></p>
                    </div>
                </a>
            </div>
            <%
                        }
                    }
                    if (!found) {
            %>
            <div class="no-results">
                <i class="fas fa-search"></i>
                <h3>No Results Found</h3>
                <p>We couldn't find any restaurants matching your search.</p>
            </div>
            <%
                    }
                } else {
            %>
            <div class="no-results">
                <i class="fas fa-utensils"></i>
                <h3>No Restaurants Available</h3>
                <p>Please check back later for restaurant updates.</p>
            </div>
            <% } %>
        </div>
    </section>

    <footer class="footer">
        <div class="footer-container">
            <div class="footer-content">
                <div class="footer-logo">
                    <i class="fas fa-utensils"></i>
                    <span>FoodDelivery</span>
                </div>
                <p>Delicious food delivered to your doorstep</p>
            </div>
            <div class="footer-bottom">
                <p>&copy; 2024 FoodDelivery. All rights reserved.</p>
            </div>
        </div>
    </footer>
</body>
</html>