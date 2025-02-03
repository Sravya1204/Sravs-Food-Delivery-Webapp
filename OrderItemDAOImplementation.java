package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImplementation implements OrderItemDAO {
    
    private static final String INSERT_ORDERITEM_QUERY = "INSERT INTO `orderItem`(`orderId`, `menuId`, `quantity`, `totalPrice`) VALUES(?, ?, ?, ?)";
    private static final String GET_ORDERITEM_QUERY = "SELECT * FROM `orderItem` WHERE `orderItemId`=?";
    private static final String UPDATE_ORDERITEM_QUERY = "UPDATE `orderItem` SET `orderId`=?, `menuId`=?, `quantity`=?, `totalPrice`=? WHERE `orderItemId`=?";
    private static final String DELETE_ORDERITEM_QUERY = "DELETE FROM `orderItem` WHERE `orderItemId`=?";
    private static final String GET_ALL_ORDERITEM = "SELECT * FROM `orderItem`";
    
    @Override
    public void orderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDERITEM_QUERY)) {

            prepareStatement.setInt(1, orderItem.getOrderId());
            prepareStatement.setInt(2, orderItem.getMenuId());
            prepareStatement.setInt(3, orderItem.getQuantity());
            prepareStatement.setDouble(4, orderItem.getTotalPrice());

            int res = prepareStatement.executeUpdate();
            if (res == 0) {
                throw new SQLException("Failed to insert order item.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        OrderItem orderItem = null;
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDERITEM_QUERY)) {
            
            prepareStatement.setInt(1, orderItemId);
            ResultSet res = prepareStatement.executeQuery();
            
            if (res.next()) {
                orderItem = extractOrderItem(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDERITEM_QUERY)) {
            
            prepareStatement.setInt(1, orderItem.getOrderId());
            prepareStatement.setInt(2, orderItem.getMenuId());
            prepareStatement.setInt(3, orderItem.getQuantity());
            prepareStatement.setDouble(4, orderItem.getTotalPrice());
            prepareStatement.setInt(5, orderItem.getOrderItemId()); // Added orderItemId as the last parameter

            int rowsUpdated = prepareStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Failed to update order item.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDERITEM_QUERY)) {
            
            prepareStatement.setInt(1, orderItemId);
            int rowsDeleted = prepareStatement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Failed to delete order item.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItemsList = new ArrayList<>();
        
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            ResultSet res = statement.executeQuery(GET_ALL_ORDERITEM);
            while (res.next()) {
                OrderItem orderItem = extractOrderItem(res);
                orderItemsList.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderItemsList;
    }

    // Helper method to extract OrderItem from ResultSet
    private OrderItem extractOrderItem(ResultSet res) throws SQLException {
        int orderItemId = res.getInt("orderItemId");
        int orderId = res.getInt("orderId");
        int menuId = res.getInt("menuId");
        int quantity = res.getInt("quantity");
        double totalPrice = res.getDouble("totalPrice");
        
        return new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
    }
}
