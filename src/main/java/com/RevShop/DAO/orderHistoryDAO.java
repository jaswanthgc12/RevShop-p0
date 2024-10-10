package com.RevShop.DAO;

import java.util.List;

import com.RevShop.entity.OrderHistory;

public interface orderHistoryDAO {
	void addOrderHistory(OrderHistory orderHistory);
    OrderHistory getOrderHistoryById(int orderHistoryId);
    List<OrderHistory> getAllOrderHistories();
    void updateOrderHistory(OrderHistory orderHistory);
    void deleteOrderHistory(int orderHistoryId);
}
