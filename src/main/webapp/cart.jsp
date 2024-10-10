<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<%@ page import="com.RevShop.entity.*" %>
<%@ page import="com.RevShop.service.CartItemServices" %>
<%@ page import="com.RevShop.serviceImpl.CartItemServiceImpl" %>
<%@ page import="java.math.*" %>

<%
    Integer buyerId = (Integer) session.getAttribute("buyer");

    if (buyerId == null) {
        out.println("<p style='color:red;'>You need to log in to view your checkout.</p>");
        out.println("<a href='login.jsp'>Login</a>");
        return;
    }

    CartItemServices cartService = new CartItemServiceImpl();
    List<CartItem> cartItems = cartService.getCartItems(buyerId);

    if (cartItems == null || cartItems.isEmpty()) {
%>
        <p>Your cart is empty. You cannot proceed to checkout.</p>
        <a href="menu.jsp">Back to Menu</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shopping Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9; /* Light background */
            margin: 20px;
            padding: 20px;
        }

        h1 {
            color: #333; /* Dark color for the title */
            text-align: center; /* Center the title */
        }

        .cart-container {
            max-width: 800px; /* Limit the width of the cart container */
            margin: 0 auto; /* Center the container */
            background-color: white; /* White background for the cart */
            border-radius: 8px; /* Rounded corners */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            padding: 20px; /* Padding inside the container */
        }

        table {
            width: 100%; /* Full width of the container */
            border-collapse: collapse; /* Collapse table borders */
            margin-bottom: 20px; /* Space below the table */
        }

        th, td {
            padding: 12px; /* Padding for table cells */
            text-align: left; /* Align text to the left */
            border-bottom: 1px solid #ddd; /* Light bottom border */
        }

        th {
            background-color: #007bff; /* Header background color */
            color: white; /* Header text color */
        }

        tr:hover {
            background-color: #f1f1f1; /* Highlight row on hover */
        }

        .total-section {
            text-align: right; /* Align total amount to the right */
            font-size: 1.2em; /* Larger font size for total */
            margin-top: 20px; /* Space above the total section */
        }

        .update-btn, .remove-btn, .proceed-to-checkout-btn {
            background-color: #007bff; /* Button background color */
            color: white; /* Button text color */
            border: none; /* Remove border */
            padding: 10px 15px; /* Padding for buttons */
            border-radius: 5px; /* Rounded corners */
            cursor: pointer; /* Pointer cursor on hover */
            transition: background-color 0.3s; /* Smooth background transition */
        }

        .update-btn:hover, .remove-btn:hover, .proceed-to-checkout-btn:hover {
            background-color: #0056b3; /* Darker shade on hover */
        }

        input[type="number"] {
            width: 60px; /* Fixed width for quantity input */
            text-align: center; /* Center align input text */
        }
    </style>
</head>
<body>

    <h1>Your Shopping Cart</h1>
    <div class="cart-container">
        <table>
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th>Remove</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    BigDecimal totalAmount = BigDecimal.ZERO;
                    for (CartItem item : cartItems) {
                        session.setAttribute("cartId", item.getCartItemId());

                        Product product = item.getProduct();
                        BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                        totalAmount = totalAmount.add(subtotal);
                %>
                <tr>
                    <td><%= product.getProductName() %></td>
                    <td>$<%= String.format("%.2f", product.getPrice()) %></td>
                    <td>
                        <form action="CartItemServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" required>
                            <input type="hidden" name="cartId" value="<%= item.getCartItemId() %>">
                            <button type="submit" class="update-btn">Update</button>
                        </form>
                    </td>
                    <td>$<%= String.format("%.2f", subtotal) %></td>
                    <td>
                        <form action="CartItemServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="cartId" value="<%= item.getCartItemId() %>">
                            <button type="submit" class="remove-btn">Remove</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <div class="total-section">
            <span class="cart-total">Total Amount: $<%= String.format("%.2f", totalAmount) %></span>
        </div>
        
        <form action="checkout.jsp" method="post">
            <input type="submit" value="Proceed to Checkout" class="proceed-to-checkout-btn">
        </form>
    </div>

</body>
</html>
