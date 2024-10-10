package com.RevShop.DAO.com.main;



import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.RevShop.DAO.OrderDao;
import com.RevShop.DAOImpl.OrderDaoImpl;
import com.RevShop.entity.Order;
import com.RevShop.entity.Review;
import com.RevShop.entity.buyers;
import com.RevShop.serviceImpl.productServiceImpl;

public class Main {
    public static void main(String[] args) {
        
    	
    	buyers buyer = new buyers();
    	
    	productServiceImpl productService = new productServiceImpl();

        // Create a Review object
        Review review = new Review();
        review.setProductId(2);
        int num = buyer.set// Assuming the product ID is 1
        review.setBuyer(buyer.setBuyerId(1)); // Assuming the buyer ID is 1
        review.setRating(4);     // Assuming a rating of 5
        review.setComment("Great product!"); // Optional comment, if your Review class has this field

        // Call the addReview method
        try {
            productService.addReview(review);
            System.out.println("Review added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding review: " + e.getMessage());
        }
    	
//        OrderDao orderDao = new OrderDaoImpl();
//
//        // Create a new Order object
//        Order newOrder = new Order();
//        newOrder.setBuyerId(1);
//        newOrder.setTotalAmount(new BigDecimal("99.99"));
//        newOrder.setOrderStatus("pending");
//        newOrder.setShippingAddress("123 Main St");
//        newOrder.setBillingAddress("123 Main St");
//        newOrder.setPaymentMethod("Credit_Card");
//        newOrder.setEstimatedDeliveryDate(Date.valueOf("2024-10-15"));
//        newOrder.setCancellationReason(null); // No cancellation
//        newOrder.setDiscount(new BigDecimal("5.00"));
//        newOrder.setOrderedAt(new Timestamp(System.currentTimeMillis()));
//
//        // Create a new order in the database
//        orderDao.createOrder(newOrder);
//        System.out.println("Order created successfully.");
//
//        // Get the order by its ID (assuming the order ID is 1 for demo purposes)
//        Order order = orderDao.getOrderById(1);
//        if (order != null) {
//            System.out.println("Order retrieved: " + order);
//        } else {
//            System.out.println("Order not found.");
//        }

        // Update the order (change status, for example)
        
//        newOrder.setOrderStatus("shipped");
//        orderDao.updateOrder(newOrder);
//        System.out.println("Order updated successfully.");
//
//        // Retrieve all orders
//        List<Order> allOrders = orderDao.getAllOrders();
//        System.out.println("All orders: ");
//        for (Order o : allOrders) {
//            System.out.println(o);
//        }

        // Delete the order (assuming order ID 1)
//        orderDao.deleteOrder(1);
//        System.out.println("Order deleted successfully.");
    }
}
