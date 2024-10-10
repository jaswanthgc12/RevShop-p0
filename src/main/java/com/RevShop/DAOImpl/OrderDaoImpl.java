package com.RevShop.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.RevShop.DAO.OrderDao;
import com.RevShop.DAO.OrderItemDAO;
import com.RevShop.entity.Order;
import com.RevShop.entity.OrderHistory;
import com.RevShop.entity.OrderItem;
import com.RevShop.utility.dbUtility;

public class OrderDaoImpl implements OrderDao {

    @Override
    public int createOrder(Order order) {
        String query = "INSERT INTO Orders (buyer_id, total_amount, order_status, shipping_address, " +
                       "billing_address, payment_method, estimated_delivery_date, cancellation_reason, " +
                       "discount, ordered_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = dbUtility.getInstance().getConnection();
        		PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getBuyerId());
            preparedStatement.setBigDecimal(2, order.getTotalAmount());
            preparedStatement.setString(3, order.getOrderStatus());
            preparedStatement.setString(4, order.getShippingAddress());
            preparedStatement.setString(5, order.getBillingAddress());
            preparedStatement.setString(6, order.getPaymentMethod());
            preparedStatement.setTimestamp(7, order.getEstimatedDeliveryDate());
            preparedStatement.setString(8, order.getCancellationReason());
            preparedStatement.setBigDecimal(9, order.getDiscount());
            preparedStatement.setTimestamp(10, order.getOrderedAt());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        String query = "SELECT * FROM Orders WHERE order_id = ?";

        try (Connection con = dbUtility.getInstance().getConnection();
        		PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = new Order(
                    resultSet.getInt("order_id"),
                    resultSet.getInt("buyer_id"),
                    resultSet.getBigDecimal("total_amount"),
                    resultSet.getString("order_status"),
                    resultSet.getString("shipping_address"),
                    resultSet.getString("billing_address"),
                    resultSet.getString("payment_method"),
                    resultSet.getTimestamp("estimated_delivery_date"),
                    resultSet.getString("cancellation_reason"),
                    resultSet.getBigDecimal("discount"),
                    resultSet.getTimestamp("ordered_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";

        try (Connection con = dbUtility.getInstance().getConnection();
        		PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order(
                    resultSet.getInt("order_id"),
                    resultSet.getInt("buyer_id"),
                    resultSet.getBigDecimal("total_amount"),
                    resultSet.getString("order_status"),
                    resultSet.getString("shipping_address"),
                    resultSet.getString("billing_address"),
                    resultSet.getString("payment_method"),
                    resultSet.getTimestamp("estimated_delivery_date"),
                    resultSet.getString("cancellation_reason"),
                    resultSet.getBigDecimal("discount"),
                    resultSet.getTimestamp("ordered_at")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        String query = "UPDATE Orders SET buyer_id = ?, total_amount = ?, order_status = ?, " +
                       "shipping_address = ?, billing_address = ?, payment_method = ?, " +
                       "estimated_delivery_date = ?, cancellation_reason = ?, discount = ? " +
                       "WHERE order_id = ?";

        try (Connection con = dbUtility.getInstance().getConnection();
        		PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getBuyerId());
            preparedStatement.setBigDecimal(2, order.getTotalAmount());
            preparedStatement.setString(3, order.getOrderStatus());
            preparedStatement.setString(4, order.getShippingAddress());
            preparedStatement.setString(5, order.getBillingAddress());
            preparedStatement.setString(6, order.getPaymentMethod());
            preparedStatement.setTimestamp(7, order.getEstimatedDeliveryDate());
            preparedStatement.setString(8, order.getCancellationReason());
            preparedStatement.setBigDecimal(9, order.getDiscount());
            preparedStatement.setInt(10, order.getOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String query = "DELETE FROM Orders WHERE order_id = ?";

        try (Connection con = dbUtility.getInstance().getConnection();
        		PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public int getLatestOrderIdByUser(Integer userId) {
		String query = "SELECT order_id FROM Orders WHERE buyer_id = ? ORDER BY ordered_at DESC LIMIT 1";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("order_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
	}

//	@Override
//	public List<OrderHistory> getOrderHistory(int buyerId) {
//		List<OrderHistory> orderHistories = new ArrayList<>();
//        String query = "SELECT o.order_id, o.total_amount, o.order_status, o.ordered_at, "
//                     + "o.estimated_delivery_date, p.product_name, oi.quantity "
//                     + "FROM orders o "
//                     + "JOIN order_items oi ON o.order_id = oi.order_id "
//                     + "JOIN products p ON oi.product_id = p.product_id "
//                     + "WHERE o.buyer_id = ?";
//
//        try (Connection conn = dbUtility.getInstance().getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setInt(1, buyerId);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                
//                int orderId = rs.getInt("order_id");
//                OrderHistory orderHistory = new OrderHistory();
//                orderHistory.setOrderId(orderId);
//                orderHistory.setTotalAmount(rs.getBigDecimal("total_amount"));
//                orderHistory.setOrderStatus(rs.getString("order_status"));
//                orderHistory.setOrderedAt(rs.getTimestamp("ordered_at"));
//                orderHistory.setEstimatedDeliveryDate(rs.getDate("estimated_delivery_date"));
//
//                
//                OrderItem orderItem = new OrderItem();
//                orderItem.getProduct().getProductName();
//                orderItem.setQuantity(rs.getInt("quantity"));
//
//                
//                orderHistory.addOrderItem(orderItem); 
//
//                
//                if (!orderHistories.contains(orderHistory)) {
//                    orderHistories.add(orderHistory);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return orderHistories;
//	}

	@Override
	public List<Order> getOrdersByBuyerId(int buyerId) {
		List<Order> orders = new ArrayList<>();
	    String query = "SELECT * FROM orders WHERE buyer_id = ?";
	    
	    try (Connection conn = dbUtility.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        
	        ps.setInt(1, buyerId);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Order order = new Order();
	            order.setOrderId(rs.getInt("order_id"));
	            order.setTotalAmount(rs.getBigDecimal("total_amount"));
	            order.setOrderStatus(rs.getString("status"));
	            order.setOrderedAt(rs.getTimestamp("ordered_at"));
	            order.setEstimatedDeliveryDate(rs.getTimestamp("estimated_delivery"));
	            
	            
	            OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
	            List<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrderId(order.getOrderId());
	            order.setOrderItems(orderItems);
	            
	            orders.add(order);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();  
	    }
	    
	    return orders;
	}

	@Override
	public List<OrderHistory> getOrderHistory(int buyerId) {
		// TODO Auto-generated method stub
		return null;
	}
}

