package com.tap.dao;

import java.util.List;

import com.tap.model.Order;
import com.tap.model.OrderItem;

public interface OrderItemDAO {

	void orderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItem);
	List<OrderItem> getAllOrderItems();
}
