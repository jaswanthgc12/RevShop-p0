<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<%@ page import="com.RevShop.entity.Product" %>
<%@ page import="com.RevShop.service.productService" %>
<%@ page import="com.RevShop.serviceImpl.productServiceImpl" %>
<%@ page import="java.util.Base64" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    productService productService = new productServiceImpl();
    String selectedCategory = request.getParameter("category");

    List<Product> productList = null;
    if (selectedCategory == null || selectedCategory.isEmpty() || selectedCategory.equals("All")) {
        productList = productService.getAllProducts();  
    } else {
        productList = productService.getProductsByCategory(selectedCategory);  
    }

    HttpSession session1 = request.getSession(false);
    boolean isLoggedIn = (session1 != null && session1.getAttribute("buyer") != null); 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Menu - RevShop</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #f5f5f5;
        }
        .header {
            background-color: #4C2090;
            color: white;
            padding: 15px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .header h1 {
            display: inline-block;
            margin: 20;
            font-size: 24px;
        }
        .product-item {
            border: 1px solid #ddd;
            margin: 15px 0;
            padding: 15px;
            background-color: #fff;
            border-radius: 8px;
            transition: box-shadow 0.3s;
            display: flex;
            flex-direction: column; /* Align items in a column */
            justify-content: space-between; /* Space out children elements */
            height: 100%; /* Allow cards to grow */
        }
        .product-item:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .product-item img {
            width: 100%;
            height: auto;
            border-radius: 8px;
            object-fit: cover; /* Maintain aspect ratio */
            margin-bottom: 10px; /* Add space between image and text */
        }
        .footer {
            background-color: #39763;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .footer a {
            color: white;
            text-decoration: none;
        }
        .footer a:hover {
            text-decoration: underline;
        }
        .product-container {
            display: flex;
            flex-wrap: wrap; /* Allow items to wrap */
            justify-content: space-between; /* Space between items */
        }
        .product-card {
            flex: 1 1 30%; /* Allow flexible sizing */
            margin: 10px; /* Space between cards */
            min-width: 280px; /* Minimum width for cards */
            max-width: 350px; /* Maximum width for cards */
        }
    </style>
</head>
<body>

    <!-- Header -->
    <div class="header">
        <h1>RevShop - Product Menu</h1>
        <div class="pull-right">
            <%
                if (isLoggedIn) {
            %>
                <a href="profile.jsp" class="btn btn-light btn-sm">Profile</a>
                <a href="logout.jsp" class="btn btn-danger btn-sm">Logout</a>
                <a href="viewCart.jsp" class="btn btn-primary btn-sm">View Cart</a>
                <a href="orderHistory.jsp" class="btn btn-info btn-sm">Order History</a>
            <%
                } else {
            %>
                <a href="login.jsp" class="btn btn-success btn-sm">Login</a>
                <a href="register.jsp" class="btn btn-warning btn-sm">Register</a>
            <%
                }
            %>
        </div>
    </div>

    <div class="container">
        <form method="get" action="menu.jsp" class="form-inline">
            <label for="category" class="control-label">Browse by Category:</label>
            <select name="category" id="category" class="form-control" onchange="this.form.submit()">
                <option value="All" <%= (selectedCategory == null || selectedCategory.equalsIgnoreCase("All")) ? "selected" : "" %>>All</option>
                <option value="Mobiles" <%= "Mobiles".equalsIgnoreCase(selectedCategory) ? "selected" : "" %>>Mobiles</option>
                <option value="Laptops" <%= "Laptops".equalsIgnoreCase(selectedCategory) ? "selected" : "" %>>Laptops</option>
                <option value="Electronics" <%= "Electronics".equalsIgnoreCase(selectedCategory) ? "selected" : "" %>>Electronics</option>
                <option value="Fashion" <%= "Fashion".equalsIgnoreCase(selectedCategory) ? "selected" : "" %>>Fashion</option>
                <option value="Kitchen Appliances" <%= "Kitchen Appliances".equalsIgnoreCase(selectedCategory) ? "selected" : "" %>>Kitchen Appliances</option>
                <option value="Gaming Accessories" <%= "Gaming Accessories".equalsIgnoreCase(selectedCategory) ? "selected" : "" %>>Gaming Accessories</option>
                <option value="Video Games" <%= "Video Games".equalsIgnoreCase(selectedCategory) ? "selected" : "" %>>Video Games</option>
            </select>
        </form>
    </div>

    <div class="container">
        <div class="product-container">
            <%
                if (productList != null && !productList.isEmpty()) {
                    for (Product product : productList) {
                        byte[] imageData = product.getImage();
                        String base64Image = Base64.getEncoder().encodeToString(imageData);
            %>
            <div class="product-card">
                <div class="product-item">
                    <h2><%= product.getProductName() %></h2>
                    <img src="data:image/jpeg;base64,<%= base64Image %>" alt="<%= product.getProductName() %>">
                    <p><strong>Price:</strong> $<%= product.getPrice() %></p>
                    <p><strong>Discounted Price:</strong> $<%= product.getDiscountedPrice() %></p>
                    <a href="ViewProductDetailsServlet?productId=<%= product.getProductId() %>" class="btn btn-primary">View Details</a>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <p>No products available in this category.</p>
            <%
                }
            %>
        </div>
    </div>

    <div class="footer">
        <p>© 2024 RevShop. All rights reserved.</p>
        <p>
            <a href="#privacy-policy">Privacy Policy</a> | 
            <a href="#terms-of-service">Terms of Service</a>
        </p>
        <p>Follow us: 
            <a href="https://facebook.com">Facebook</a> | 
            <a href="https://twitter.com">Twitter</a> | 
            <a href="https://instagram.com">Instagram</a>
        </p>
    </div>

</body>
</html>
