package com.RevShop.DAO;

import java.util.List;

import com.RevShop.entity.Order;
import com.RevShop.entity.OrderItem;


public interface OrderItemDAO {
	void addOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(int orderItemId);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int orderItemId);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrderId(int orderId);
}
