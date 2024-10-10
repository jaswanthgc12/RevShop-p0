package com.RevShop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.RevShop.DAO.CartItemDAO;
import com.RevShop.DAO.OrderDao;
import com.RevShop.DAO.OrderItemDAO;
import com.RevShop.DAO.productDAO;  
import com.RevShop.DAOImpl.CartItemDAOImpl;
import com.RevShop.DAOImpl.OrderDaoImpl;
import com.RevShop.DAOImpl.OrderItemDAOImpl;
import com.RevShop.DAOImpl.productDAOImpl;  
import com.RevShop.entity.CartItem;
import com.RevShop.entity.Order;
import com.RevShop.entity.OrderItem;
import com.RevShop.entity.Product;
import com.RevShop.utility.emailUtility;


public class CheckOutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("buyer"); 

        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CartItemDAO cartDAO = new CartItemDAOImpl();
        OrderDao orderDAO = new OrderDaoImpl();
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
        productDAO productDAO = new productDAOImpl();

        
		List<CartItem> cartItems = cartDAO.getCartItems(userId);
		if (cartItems == null || cartItems.isEmpty()) {
		    response.sendRedirect("cart.jsp?message=Your cart is empty.");
		    return;
		}

		BigDecimal totalAmount = BigDecimal.ZERO;

		
		for (CartItem cartItem : cartItems) {
		    Product product = productDAO.getProductById(cartItem.getProduct().getProductId());
		    if (product != null) {
		    	BigDecimal itemPrice = product.getPrice();
		        totalAmount = totalAmount.add(itemPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity())));
		    } else {
		        response.sendRedirect("error.jsp?message=Product not found");
		        return;
		    }
		}

		String shippingAddress = request.getParameter("shippingAddress");
		if (shippingAddress == null || shippingAddress.isEmpty()) {
		    System.out.println("Shipping address is null or empty");
		    response.sendRedirect("error.jsp?message=Shipping address can't be null.");
		    return;
		}
		String billingAddress = request.getParameter("billingAddress");
		if (billingAddress == null || billingAddress.isEmpty()) {
		    billingAddress = "default_billing_address";  
		}

		
		Order order = new Order();
		order.setBuyerId(userId); 
		order.setTotalAmount(totalAmount);
		order.setShippingAddress(shippingAddress);
		order.setOrderStatus("pending"); 
		order.setBillingAddress(billingAddress);
		order.setPaymentMethod("cash_on_delivery");
		Timestamp orderedAt = Timestamp.valueOf(LocalDateTime.now());
		order.setOrderedAt(orderedAt);
		
		LocalDateTime estimatedDeliveryTime = LocalDateTime.now().plusHours(2);
		Timestamp estimatedDeliveryTimestamp = Timestamp.valueOf(estimatedDeliveryTime);
		order.setEstimatedDeliveryDate(estimatedDeliveryTimestamp);
		
		orderDAO.createOrder(order);
		int orderId = orderDAO.getLatestOrderIdByUser(userId); 

		
		if (orderId == 0) {
		    response.sendRedirect("error.jsp?message=Order creation failed");
		    return;
		}

		session.setAttribute("orderId", orderId); 

		
		for (CartItem cartItem : cartItems) {
		    Product product = productDAO.getProductById(cartItem.getProduct().getProductId());
		    if (product != null) {
		        OrderItem orderItem = new OrderItem();
		        orderItem.setOrderId(orderId);
		        orderItem.setProductId(cartItem.getProduct().getProductId());
		        orderItem.setQuantity(cartItem.getQuantity());
		        orderItem.setPricePerUnit(product.getPrice().doubleValue()); 
		        orderItemDAO.addOrderItem(orderItem);
		    }
		}

		
		cartDAO.clearCart(userId);
		
		
//		String buyerEmail = session.getAttribute("buyerEmail").toString();
//        String sellerEmail = "gcjaswanth745@gmail.com"; 
//
//        
//        String buyerSubject = "Order Confirmation - Order #" + orderId;
//        String buyerMessage = "Dear Buyer,\n\nYour order has been successfully placed. Your order ID is " + orderId +
//                ".\nShipping Address: " + shippingAddress + "\nTotal Amount: " + totalAmount + "\n\nThank you for shopping with us!";
//        emailUtility.sendEmail(buyerEmail, buyerSubject, buyerMessage);
//
//        
//        String sellerSubject = "New Order Received - Order #" + orderId;
//        String sellerMessage = "Dear Seller,\n\nYou have received a new order. Please ship the order to the following address:\n" +
//                shippingAddress + "\nTotal Amount: " + totalAmount + "\n\nThank you!";
//        emailUtility.sendEmail(sellerEmail, sellerSubject, sellerMessage);
		
		response.sendRedirect("orderConfirmation.jsp?orderId=" + orderId);
    }
}
