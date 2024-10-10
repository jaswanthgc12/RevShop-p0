package com.RevShop.serviceImpl;

import java.util.List;

import com.RevShop.DAO.CartItemDAO;
import com.RevShop.DAO.OrderDao;
import com.RevShop.DAO.OrderItemDAO;
import com.RevShop.DAOImpl.CartItemDAOImpl;
import com.RevShop.DAOImpl.OrderDaoImpl;
import com.RevShop.DAOImpl.OrderItemDAOImpl;
import com.RevShop.entity.Order;
import com.RevShop.entity.OrderItem;
import com.RevShop.service.OrderService;

public class orderServiceImpl implements OrderService{

	private OrderDao orderDao;

    public orderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }

    @Override
    public int createOrder(Order order) {
        orderDao.createOrder(order);
        return 0;
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public void updateOrder(Order order) {
        
        orderDao.updateOrder(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }

	@Override
	public List<Order> getOrdersByUserId(int userId) {
	        
	        List<Order> orders = orderDao.getOrdersByBuyerId(userId);
	        
	        
	        for (Order order : orders) {
	        	OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
	            List<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrderId(order.getOrderId());
	            order.setOrderItems(orderItems);
	        }
	        
	        return orders;
	}
	
}
