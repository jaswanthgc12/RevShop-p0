package com.RevShop.service;

import java.util.List;
import com.RevShop.entity.Order;

public interface OrderService {

    int createOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(int orderId);
    
    List<Order> getOrdersByUserId(int userId);
}
