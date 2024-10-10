package com.RevShop.service;

import java.util.List;

import com.RevShop.entity.OrderItem;

public interface orderItemService {
	void addOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(int orderItemId);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int orderItemId);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrderId(int orderId);
}
