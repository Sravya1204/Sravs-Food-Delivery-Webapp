package com.tap.daoImplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.utility.DBConnection;

public class OrderDAOImplementation implements OrderDAO {

    private static final String INSERT_ORDER_QUERY = "INSERT into `order`(`userId`, `restaurantId`, `orderDate`, `totalAmount`, `status`, `paymentMode`) values(?,?,?,?,?,?)";
    private static final String UPDATE_ORDER_QUERY = "UPDATE `order` SET `userId`=?, `restaurantId`=?, `orderDate`=?, `totalAmount`=?, `status`=?, `paymentMode`=? WHERE `orderId`=?";
    private static final String GET_ORDER_QUERY = "SELECT * from `order` WHERE `orderId`=?";
    private static final String DELETE_ORDER_QUERY = "DELETE from `order` WHERE orderId=?";
    private static final String GET_ALL_ORDERS = "SELECT * from `order`";

    @Override
    public int addOrder(Order order) {
        int orderId = 0;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setInt(1, order.getUserId());
            prepareStatement.setInt(2, order.getRestaurantId());
            prepareStatement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            prepareStatement.setDouble(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPaymentMode());

            int res = prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDER_QUERY)) {

            prepareStatement.setInt(1, orderId);
            ResultSet res = prepareStatement.executeQuery();

            if (res.next()) {
                order = extractOrder(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDER_QUERY)) {

            prepareStatement.setInt(1, order.getUserId());
            prepareStatement.setInt(2, order.getRestaurantId());
            prepareStatement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            prepareStatement.setDouble(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPaymentMode());
            prepareStatement.setInt(7, order.getOrderId());

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDER_QUERY)) {

            prepareStatement.setInt(1, orderId);
            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(GET_ALL_ORDERS)) {

            while (res.next()) {
                Order order = extractOrder(res);
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM `order` WHERE `userId` = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                Order order = extractOrder(res);
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private Order extractOrder(ResultSet res) throws SQLException {
        int orderId = res.getInt("orderId");
        int userId = res.getInt("userId");
        int restaurantId = res.getInt("restaurantId");
        java.util.Date orderDate = res.getDate("orderDate");
        double totalAmount = res.getDouble("totalAmount");
        String status = res.getString("status");
        String paymentMode = res.getString("paymentMode");

        return new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
    }
}
