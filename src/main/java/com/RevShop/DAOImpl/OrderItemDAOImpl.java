package com.RevShop.DAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RevShop.DAO.OrderItemDAO;
import com.RevShop.entity.Order;
import com.RevShop.entity.OrderItem;
import com.RevShop.utility.dbUtility;

public class OrderItemDAOImpl implements OrderItemDAO{
	private static final String INSERT_ORDER_ITEM = "INSERT INTO Order_Items (order_id, product_id, quantity, price_per_unit) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ORDER_ITEM_BY_ID = "SELECT * FROM Order_Items WHERE order_item_id = ?";
    private static final String UPDATE_ORDER_ITEM = "UPDATE Order_Items SET order_id = ?, product_id = ?, quantity = ?, price_per_unit = ? WHERE order_item_id = ?";
    private static final String DELETE_ORDER_ITEM = "DELETE FROM Order_Items WHERE order_item_id = ?";
    private static final String SELECT_ALL_ORDER_ITEMS = "SELECT * FROM Order_Items";
    private static final String SELECT_ORDER_ITEMS_BY_ORDER_ID = "SELECT * FROM Order_Items WHERE order_id = ?";

	@Override
	public void addOrderItem(OrderItem orderItem) {
		try (Connection con = dbUtility.getInstance().getConnection();
	        	 PreparedStatement preparedStatement = con.prepareStatement(INSERT_ORDER_ITEM)) {
	            preparedStatement.setInt(1, orderItem.getOrderId());
	            preparedStatement.setInt(2, orderItem.getProductId());
	            preparedStatement.setInt(3, orderItem.getQuantity());
	            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(orderItem.getPricePerUnit()));

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
	@Override
	public OrderItem getOrderItem(int orderItemId) {
	    OrderItem orderItem = null;
	    String query = "SELECT * FROM Order_Items WHERE order_item_id = ?"; // SQL query

	    try (Connection con = dbUtility.getInstance().getConnection();
	         PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        // Set the orderItemId in the prepared statement
	        preparedStatement.setInt(1, orderItemId);
	        
	        // Execute the query
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        // If a result is found, populate the OrderItem object
	        if (resultSet.next()) {
	            orderItem = new OrderItem();
	            resultSet.getInt("order_item_id");
                resultSet.getInt("order_id");
                resultSet.getInt("product_id");
                resultSet.getInt("quantity");
                resultSet.getBigDecimal("price_per_unit");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle SQL exceptions
	    }
	    return orderItem; // Return the populated OrderItem object (or null if not found)
	}


	@Override
	public void updateOrderItem(OrderItem orderItem) {
		try (Connection con = dbUtility.getInstance().getConnection();
	        	 PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ORDER_ITEM)) {
	            preparedStatement.setInt(1, orderItem.getOrderId());
	            preparedStatement.setInt(2, orderItem.getProductId());
	            preparedStatement.setInt(3, orderItem.getQuantity());
	            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(orderItem.getPricePerUnit()));
	            preparedStatement.setInt(5, orderItem.getOrderItemId());

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
	@Override
	public void deleteOrderItem(int orderItemId) {
        try (Connection con = dbUtility.getInstance().getConnection();
        	 PreparedStatement preparedStatement = con.prepareStatement(DELETE_ORDER_ITEM)) {
            preparedStatement.setInt(1, orderItemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> orderItems = new ArrayList<>();

        try (Connection con = dbUtility.getInstance().getConnection();
        	 PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_ORDER_ITEMS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	OrderItem orderItem = new OrderItem();
	            resultSet.getInt("order_item_id");
                resultSet.getInt("order_id");
                resultSet.getInt("product_id");
                resultSet.getInt("quantity");
                resultSet.getBigDecimal("price_per_unit");
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {
		 List<OrderItem> orderItems = new ArrayList<>();

	        try (Connection con = dbUtility.getInstance().getConnection();
	        	 PreparedStatement preparedStatement = con.prepareStatement(SELECT_ORDER_ITEMS_BY_ORDER_ID)) {
	            preparedStatement.setInt(1, orderId);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	            	OrderItem orderItem = new OrderItem();
		            resultSet.getInt("order_item_id");
	                resultSet.getInt("order_id");
	                resultSet.getInt("product_id");
	                resultSet.getInt("quantity");
	                resultSet.getBigDecimal("price_per_unit");
	                
	                orderItems.add(orderItem);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderItems;
	    }

}
