package com.RevShop.DAO;

import java.util.List;

import com.RevShop.entity.Order;
import com.RevShop.entity.OrderHistory;

public interface OrderDao {
    int createOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int orderId);
	int getLatestOrderIdByUser(Integer userId);
	public List<OrderHistory> getOrderHistory(int buyerId);
	public List<Order> getOrdersByBuyerId(int buyerId); 
}

