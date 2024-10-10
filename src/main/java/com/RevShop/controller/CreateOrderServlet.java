//package com.RevShop.controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import org.json.JSONObject;
//
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//
///**
// * Servlet implementation class CreateOrderServlet
// */
//public class CreateOrderServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Initialize Razorpay client
//        RazorpayClient client = null; // Declare client outside try
//        try {
//            client = new RazorpayClient("YOUR_KEY_ID", "YOUR_KEY_SECRET");
//
//            // Create a new order
//            JSONObject orderRequest = new JSONObject();
//            orderRequest.put("amount", 50000); // Amount in paise (e.g., 500.00 INR = 50000)
//            orderRequest.put("currency", "INR");
//            orderRequest.put("payment_capture", "1"); // Auto capture payment
//
//            Order order = client.orders.create(orderRequest); // Use Order object
//            String orderId = order.get("id"); // Retrieve order ID
//
//            // Send the order ID back to the client
//            response.setContentType("application/json");
//            response.getWriter().write("{\"orderId\":\"" + orderId + "\"}");
//        } catch (RazorpayException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("Order creation failed: " + e.getMessage());
//        }
//    }
//
//}
