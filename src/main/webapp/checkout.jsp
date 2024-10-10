<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.util.*, com.RevShop.DAO.*, com.RevShop.entity.*, com.RevShop.DAOImpl.*" %>
<%@ page import="java.math.BigDecimal" %>

<%
    // Assume userId is already available in the session
    Integer userId = (Integer) session.getAttribute("buyer");
    List<CartItem> cartItems = null; 

    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch cart items for the logged-in user
    CartItemDAO cartDAO = new CartItemDAOImpl();
    cartItems = cartDAO.getCartItems(userId); 

    if (cartItems == null || cartItems.isEmpty()) {
        out.println("<div class='error'><h2>Your cart is empty. Please add items to your cart first.</h2></div>");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            background-color: #f7f7f8;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            padding: 20px;
            animation: fadeIn 0.5s ease;
        }

        h2 {
            color: #4C5690;
            text-align: center;
            margin-bottom: 10px;
        }

        h3 {
            color: #333;
            margin-bottom: 10px;
        }

        .cart-item {
            display: flex;
            justify-content: space-between;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            margin: 10px 0;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .cart-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .cart-item div {
            flex: 1;
            padding: 5px;
        }

        .cart-item .product-name {
            font-weight: bold;
            color: #4CAF50;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #333;
        }

        textarea, select, input[type="text"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        textarea:focus, select:focus, input[type="text"]:focus {
            border-color: #4CAF50;
            outline: none;
        }

        input[type="submit"] {
            background-color: #4CA65u8;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.2s;
        }

        input[type="submit"]:hover {
            background-color: #453329;
            transform: translateY(-2px);
        }

        .error {
            color: red;
            font-size: 14px;
            text-align: center;
            margin-bottom: 15px;
        }

        .total-amount {
            font-size: 18px;
            font-weight: bold;
            color: #4CAF50;
            text-align: right;
            margin-top: 20px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
            transition: color 0.3s;
        }

        a:hover {
            color: #38W56C;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Checkout</h2>
        <h3>Your Cart Items</h3>
        <div>
            <%
                BigDecimal totalAmount = BigDecimal.ZERO;
                for (CartItem item : cartItems) {
                    BigDecimal itemTotal = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                    totalAmount = totalAmount.add(itemTotal);
            %>
            <div class="cart-item">
                <div><span class="product-name">Product ID:</span> <%= item.getProduct().getProductId() %></div>
                <div><span class="product-name">Product Name:</span> <%= item.getProduct().getProductName() %></div>
                <div><span class="product-name">Quantity:</span> <%= item.getQuantity() %></div>
                <div><span class="product-name">Price (Each):</span> <%= item.getProduct().getPrice() %></div>
                <div><span class="product-name">Total Price:</span> <%= itemTotal %></div>
            </div>
            <% } %>
        </div>

        <h3 class="total-amount">Total Amount: <%= totalAmount %></h3>

        <form action="CheckOutController" method="post">
            <label for="shippingAddress">Shipping Address:</label>
            <textarea id="shippingAddress" name="shippingAddress" required maxlength="255" placeholder="Enter your delivery address..."></textarea>

            <label for="billingAddress">Billing Address (Optional):</label>
            <textarea id="billingAddress" name="billingAddress" maxlength="255" placeholder="Enter your billing address (optional)..."></textarea>

            <label for="paymentMethod">Payment Method:</label>
            <select name="paymentMethod" required>
                <option value="" disabled selected>Select a payment method</option>
                <option value="cash_on_delivery">Cash on Delivery</option>
                <option value="UPI">UPI</option>
                <option value="debit_card">Debit Card</option>
                <option value="credit_card">Credit Card</option>
            </select>

            <input type="submit" value="Place Order">
        </form>

        <a href="menu.jsp">Back to Shopping</a>
    </div>
</body>
</html>
