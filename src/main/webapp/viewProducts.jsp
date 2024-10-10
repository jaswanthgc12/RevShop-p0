<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.RevShop.entity.Product" %>
<%@ page import="com.RevShop.DAO.productDAO" %>
<%@ page import="com.RevShop.DAOImpl.productDAOImpl" %>

<%
    
    Integer sellerId = (Integer) session.getAttribute("sellerId");
    
    // Ensure the sellerId exists
    if (sellerId == null) {
        response.sendRedirect("sellerLogin.jsp"); // Redirect to login if no sellerId found
        return;
    }

    // Use the sellerId to get the seller's products
    productDAO productDAO = new productDAOImpl();
    List<Product> products = productDAO.getProductBySellerId(sellerId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Products</title>
    <link rel="stylesheet" href="path/to/bootstrap.css">
</head>
<body>

<div class="container">
    <h2>Your Products</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Discounted Price</th>
                <th>Quantity in Stock</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                
                for (Product product : products) {
            %>
                <tr>
                    <td><%= product.getProductId() %></td>
                    <td><%= product.getProductName() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getDiscountedPrice() %></td>
                    <td><%= product.getQuantityInStock() %></td>
                    <td>
                       
                        <a href="editProduct.jsp?productId=<%= product.getProductId() %>" class="btn btn-warning">Edit</a>
                        <a href="deleteProduct.jsp?productId=<%= product.getProductId() %>" class="btn btn-danger" 
                           onclick="return confirm('Are you sure you want to delete this product?');">Delete</a>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <a href="addProduct.jsp" class="btn btn-success">Add New Product</a>
</div>

<script src="path/to/bootstrap.js"></script>
</body>
</html>
