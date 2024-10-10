<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    
    if (session.getAttribute("sellerId") == null) {
        
        response.sendRedirect("sellerLogin.jsp");
        return; 
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seller Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
        .dashboard-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
            border-radius: 8px;
        }
        .dashboard-actions {
            margin-top: 20px;
        }
        .dashboard-actions a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .dashboard-actions a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <h1>Welcome</h1>
        <h2>Seller Dashboard</h2>
        
        <p>Manage your product inventory, add new products, and track your orders from here.</p>
        
        <div class="dashboard-actions">
            <a href="products.jsp">Add New Product</a>
            <a href="viewProducts.jsp">View/Edit Products</a>
            <a href="orders.jsp">View Orders</a>
            <a href="logout.jsp">Logout</a>
        </div>
    </div>
</body>
</html>
