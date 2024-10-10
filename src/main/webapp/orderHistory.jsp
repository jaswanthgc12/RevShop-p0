<%@ page import="com.RevShop.entity.OrderHistory" %>
<%@ page import="com.RevShop.entity.Order" %>
<%@ page import="com.RevShop.entity.OrderItem" %>
<%@ page import="com.RevShop.DAO.OrderDao" %>
<%@ page import="com.RevShop.DAOImpl.OrderDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page session="true" %>

<%
    
    Integer buyerId = (Integer) session.getAttribute("buyer");
    if (buyerId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    
    OrderDao orderDAO = new OrderDaoImpl(); 
    List<OrderHistory> orderHistories = orderDAO.getOrderHistory(buyerId); 

    
    System.out.println("Buyer ID: " + buyerId);
    if (orderHistories != null) {
        System.out.println("Order histories fetched successfully. Total histories: " + orderHistories.size());
    } else {
        System.out.println("No order histories found for Buyer ID: " + buyerId);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

    <h1>Your Order History</h1>

    <%
        if (orderHistories != null && !orderHistories.isEmpty()) {
    %>
        <table border="1" cellpadding="10">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                    <th>Ordered At</th>
                    <th>Estimated Delivery</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%
				    
				    for (OrderHistory orderHistory : orderHistories) {
				        List<Order> orders = (List<Order>)orderHistory.getOrder(); 
				        for (Order order : orders) {
				            for (OrderItem item : order.getOrderItems()) {
				%>
				                <tr>
				                    <td><%= order.getOrderId() %></td>
				                    <td><%= order.getTotalAmount() %></td>
				                    <td><%= order.getOrderStatus() %></td>
				                    <td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderedAt()) %></td>
				                    <td><%= new SimpleDateFormat("yyyy-MM-dd").format(order.getEstimatedDeliveryDate()) %></td>
				                    <td><%= item.getProduct().getProductName() %></td>
				                    <td><%= item.getQuantity() %></td>
				                </tr>
				<%
				            } 
				        } 
				    } 
				%>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p>No orders found in your order history.</p>
    <%
        }
    %>

    <a href="menu.jsp">Continue Shopping</a>

</body>
</html>
