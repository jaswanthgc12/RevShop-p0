<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.RevShop.entity.*, com.RevShop.DAO.OrderDao, com.RevShop.DAOImpl.OrderDaoImpl" %>
<%
    Integer orderId = (Integer) session.getAttribute("orderId");

    if (orderId == null) {
        response.sendRedirect("cart.jsp?message=No order found.");
        return;
    }

    OrderDao orderDAO = new OrderDaoImpl();
    Order order = orderDAO.getOrderById(orderId);

    if (order == null) {
        response.sendRedirect("error.jsp?message=Order not found.");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .order-summary {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            margin: 20px auto;
            max-width: 600px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            animation: fadeIn 0.5s ease-in-out;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        h1 {
            font-size: 2.5em;
        }
        h2 {
            margin-top: 0;
        }
        strong {
            color: #4CAF50;
        }
        .link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: white;
            background-color: #4CAF50;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .link:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <header class="header">
        <h1>Order Confirmation</h1>
    </header>

    <div class="container">
        <div class="order-summary">
            <h2>Thank you for your order, <%= session.getAttribute("buyerName") %>!</h2>
            <p>Your order number is: <strong>#<%= order.getOrderId() %></strong></p>
            <p>Order Total: <strong>$<%= order.getTotalAmount() %></strong></p>
            <p>Shipping Address: <strong><%= order.getShippingAddress() %></strong></p>
            <p>Billing Address: <strong><%= order.getBillingAddress() %></strong></p>
            <p>Payment Method: <strong><%= order.getPaymentMethod() %></strong></p>
            <p>Estimated Delivery Date: <strong><%= order.getEstimatedDeliveryDate() %></strong></p>

            <h3>Order Details:</h3>
            <p>
                <a href="orderHistory.jsp" class="link">View your order history</a><br>
                <a href="menu.jsp" class="link">Shop Again!</a><br>
                <a href="review.jsp" class="link">Product Review</a>
            </p>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</body>
</html>
