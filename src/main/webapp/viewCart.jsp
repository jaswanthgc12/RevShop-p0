<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<%@ page import="com.RevShop.entity.Product" %>
<%@ page import="com.RevShop.service.productService" %>
<%@ page import="com.RevShop.serviceImpl.productServiceImpl" %>
<%@ page import="java.math.BigDecimal" %>
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
    boolean isLoggedIn = (session != null && session.getAttribute("buyer") != null); 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Cart - RevShop</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #f5f5f5;
        }
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .header h1 {
            display: inline-block;
            margin: 0;
            font-size: 24px;
        }
        .cart-item {
            border: 1px solid #ddd;
            margin: 15px 0;
            padding: 15px;
            background-color: #fff;
            border-radius: 8px;
            transition: box-shadow 0.3s;
        }
        .cart-item:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .cart-item img {
            width: 100%;
            height: auto;
            border-radius: 8px;
        }
        .footer {
            background-color: #333;
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
    </style>
</head>
<body>

    <!-- Header -->
    <div class="header">
        <h1>RevShop - Your Cart</h1>
        <div class="pull-right">
            <%
                if (isLoggedIn) {
            %>
                <a href="profile.jsp" class="btn btn-light">Profile</a>
                <a href="logout.jsp" class="btn btn-light">Logout</a>
                <a href="viewCart.jsp" class="btn btn-light">View Cart</a>
            <%
                } else {
            %>
                <a href="login.jsp" class="btn btn-light">Login</a>
                <a href="register.jsp" class="btn btn-light">Register</a>
            <%
                }
            %>
        </div>
    </div>

    <!-- Cart Items -->
    <div class="container">
        <div class="row">
            <%
                if (productList != null && !productList.isEmpty()) {
                    BigDecimal grandTotal = BigDecimal.ZERO;
                    for (Product product : productList) {
                        BigDecimal price = (product.getPrice() != null) ? product.getPrice() : BigDecimal.ZERO;
                        BigDecimal discountedPrice = (product.getDiscountedPrice() != null) ? product.getDiscountedPrice() : BigDecimal.ZERO;
                        int quantity = 1; 
                        BigDecimal totalPrice = price.multiply(new BigDecimal(quantity));

                        grandTotal = grandTotal.add(totalPrice);

                        byte[] imageData = product.getImage();
                        String base64Image = Base64.getEncoder().encodeToString(imageData);
            %>
            <div class="col-xs-12 col-sm-6 col-md-4">
                <div class="cart-item">
                    <h2><%= product.getProductName() %></h2>
                    <img src="data:image/jpeg;base64,<%= base64Image %>" alt="<%= product.getProductName() %>">
                    <p><strong>Price:</strong> $<%= (price != BigDecimal.ZERO) ? price : "N/A" %></p>
                    <p><strong>Discounted Price:</strong> $<%= (discountedPrice != BigDecimal.ZERO) ? discountedPrice : "N/A" %></p>
                    <p><strong>Quantity:</strong> <%= quantity %></p>
                    <p><strong>Total:</strong> $<%= totalPrice %></p>
                    <a href="removeFromCart.jsp?productId=<%= product.getProductId() %>" class="btn btn-danger">Remove</a>
                </div>
            </div>
            <%
                    }
            %>
            <div class="col-xs-12">
                <h3>Grand Total: $<%= grandTotal %></h3>
                <a href="checkout.jsp" class="btn btn-success">Proceed to Checkout</a>
            </div>
            <%
                } else {
            %>
            <p>Your cart is empty.</p>
            <%
                }
            %>
        </div>
    </div>

    <!-- Footer -->
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
