<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="com.tap.utility.Cart" %>
<%@ page import="com.tap.utility.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="./overc.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="confirmation-card">
            <div class="success-checkmark">
                <div class="check-icon">
                    <span class="icon-line line-tip"></span>
                    <span class="icon-line line-long"></span>
                    <div class="icon-circle"></div>
                    <div class="icon-fix"></div>
                </div>
            </div>

            <div class="content">
                <h1>Order Confirmed!</h1>
                <p class="greeting">Thank you, <span class="customer-name">
                    <%= request.getAttribute("receiverName") != null ? request.getAttribute("receiverName") : "Guest" %>
                </span>!</p>

                <div class="order-details">
                    <div class="detail-item">
                        <i class='bx bx-time-five'></i>
                        <span>Estimated Delivery Time: 30-45 minutes</span>
                    </div>
                    <div class="detail-item">
                        <i class='bx bx-map'></i>
                        <span>Delivery Address:<br>
                            <%= request.getAttribute("customerAddress") != null ? request.getAttribute("customerAddress") : "Not provided" %>
                        </span>
                    </div>
                    <div class="detail-item">
                        <i class='bx bx-phone'></i>
                        <span>Contact Number: 
                            <%= request.getAttribute("receiverPhone") != null ? request.getAttribute("receiverPhone") : "Not provided" %>
                        </span>
                    </div>
                </div>

                <div class="actions">
                    <a href="home.jsp" class="back-btn">
                        <i class='bx bx-restaurant'></i>
                        Browse Restaurants
                    </a>
                </div>
            </div>
        </div>

        
</body>
</html>