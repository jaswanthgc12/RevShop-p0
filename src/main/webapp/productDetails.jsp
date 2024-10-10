<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<%@ page import="com.RevShop.entity.*" %>
<%@ page import="com.RevShop.entity.Review" %>
<%@ page import="com.RevShop.service.productService" %>
<%@ page import="com.RevShop.serviceImpl.productServiceImpl" %>
<%@ page import="java.util.*" %>

<%
    int productId;
    try {
        productId = Integer.parseInt(request.getParameter("productId"));
    } catch (NumberFormatException e) {
        out.println("<p style='color:red;'>Invalid product ID.</p>");
        return;
    }

    productService productService = new productServiceImpl();
    Product product = productService.getProductById(productId);
    
    if (product == null) {
        out.println("<p style='color:red;'>Product not found.</p>");
        return;
    }

    List<Review> reviews = productService.getReviewsByProductId(productId);
    if (reviews == null) {
        reviews = new ArrayList<>(); 
    }

    byte[] imageData = product.getImage();
    String base64Image = Base64.getEncoder().encodeToString(imageData);
    
    Integer buyerId = (Integer) session.getAttribute("buyer");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= product.getProductName() %> - Details</title>
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
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .product-details {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }
        .product-details:hover {
            transform: scale(1.02);
        }
        .product-details img {
            width: 300px;
            height: auto;
        }
        .add-to-cart {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        .user-reviews {
            margin-top: 20px;
        }
        .footer {
            background-color: #333;
            color: white;
            padding: 20px;
            text-align: center;
            position: relative;
            bottom: 0;
            width: 100%;
        }
        .review {
            background-color: #f9f9f9; 
            border: 1px solid #ddd; 
            padding: 10px; 
            border-radius: 5px; 
            margin-top: 10px;
            transition: background-color 0.3s;
        }
        .review:hover {
            background-color: #eaeaea;
        }
        /* Animation styles */
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
        .animated {
            animation: fadeIn 0.5s ease-in-out;
        }
    </style>
    <script>
        $(document).ready(function(){
            $(".review").each(function(index) {
                $(this).delay(500 * index).fadeIn(500).addClass("animated");
            });
        });
    </script>
</head>
<body>

    <header class="header">
        <h1>Product Details</h1>
    </header>
    
    <main class="container">
        <section class="product-details">
            <h2><%= product.getProductName() %></h2>
            <img src="data:image/jpeg;base64,<%= base64Image %>" alt="<%= product.getProductName() %>">
            <p><strong>Description:</strong> <%= product.getDescription() %></p>
            <p><strong>Price:</strong> $<%= product.getPrice() %></p>
            <p><strong>Discounted Price:</strong> $<%= product.getDiscountedPrice() %></p>
        </section>

        <section class="add-to-cart">
            <h2>Add to Cart</h2>
            <form action="CartItemServlet" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productId" value="<%= productId %>">
                <input type="hidden" name="buyerId" value="<%= buyerId %>"> 
                
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" class="form-control" value="1" min="1" required>
                </div>
                <button type="submit" class="btn btn-success">Add to Cart</button>
            </form>
        </section>

        <section class="user-reviews">
            <h2>User Reviews</h2>
            <div class="reviews">
                <%
                    if (!reviews.isEmpty()) {
                        for (Review review : reviews) {
                %>
                    <div class="review" style="display:none;">
                        <p><strong>Rating:</strong> <%= review.getRating() %> / 5</p>
                        <p><strong>Comment:</strong> <%= review.getComment() %></p>
                        <p><em>Reviewed on: <%= review.getReviewDate() %></em></p>
                    </div>
                <%
                        }
                    } else {
                %>
                    <p>No reviews available for this product.</p>
                <%
                    }
                %>
            </div>
        </section>

        <section class="cart-link">
            <h2>Cart</h2>
            <p><a href="CartItemServlet?action=get" class="btn btn-info">View Cart</a></p>
        </section>
    </main>

    <footer class="footer">
        <p><a href="menu.jsp" class="btn btn-primary">Back to Menu</a></p>
    </footer>
</body>
</html>
