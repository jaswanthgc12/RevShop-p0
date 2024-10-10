package com.RevShop.serviceImpl;

import java.util.List;

import com.RevShop.DAO.OrderItemDAO;
import com.RevShop.DAOImpl.OrderItemDAOImpl;
import com.RevShop.entity.OrderItem;
import com.RevShop.service.orderItemService;

public class orderItemServiceImpl implements orderItemService{

	private OrderItemDAO orderItemDAO;

    
    public orderItemServiceImpl() {
        this.orderItemDAO = new OrderItemDAOImpl();
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        // Business logic (if any) before calling the DAO layer
        orderItemDAO.addOrderItem(orderItem);
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        // Business logic (if any) before calling the DAO layer
        return orderItemDAO.getOrderItem(orderItemId);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        // Business logic (if any) before calling the DAO layer
        orderItemDAO.updateOrderItem(orderItem);
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        // Business logic (if any) before calling the DAO layer
        orderItemDAO.deleteOrderItem(orderItemId);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        // Business logic (if any) before calling the DAO layer
        return orderItemDAO.getAllOrderItems();
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        // Business logic (if any) before calling the DAO layer
        return orderItemDAO.getOrderItemsByOrderId(orderId);
    }

}
