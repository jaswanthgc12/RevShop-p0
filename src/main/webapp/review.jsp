<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.RevShop.entity.Review" %>
<%@ page import="com.RevShop.DAO.productDAO" %>
<%@ page import="com.RevShop.DAOImpl.productDAOImpl" %>

<%
    // Initialize productId and buyerId
    Integer productId = (Integer) session.getAttribute("productId");
    Integer buyerId = (Integer) session.getAttribute("buyerId"); 

    
    if (productId == null || buyerId == null) {
        
        response.sendRedirect("error.jsp");
        return; 
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Write a Review</title>
    <link rel="stylesheet" href="path/to/your/css/styles.css"> 
</head>
<body>
    <div class="container">
        <h2>Write a Review for Product ID: <%= productId %></h2>
        <form action="SubmitReviewServlet" method="post">
            <input type="hidden" name="productId" value="<%= productId %>">
            <input type="hidden" name="buyerId" value="<%= buyerId %>">

            <label for="rating">Rating:</label>
            <select name="rating" id="rating" required>
                <option value="" disabled selected>Select a rating</option>
                <option value="1">1 Star</option>
                <option value="2">2 Stars</option>
                <option value="3">3 Stars</option>
                <option value="4">4 Stars</option>
                <option value="5">5 Stars</option>
            </select>
            <br><br>

            <label for="comment">Comment:</label>
            <textarea name="comment" id="comment" rows="5" required></textarea>
            <br><br>

            <input type="submit" value="Submit Review">
        </form>
    </div>
</body>
</html>
