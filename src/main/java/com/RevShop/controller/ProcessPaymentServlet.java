//package com.RevShop.controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//
///**
// * Servlet implementation class ProcessPaymentServlet
// */
//public class ProcessPaymentServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String razorpayPaymentId = request.getParameter("razorpay_payment_id");
//        String razorpayOrderId = request.getParameter("razorpay_order_id");
//        String razorpaySignature = request.getParameter("razorpay_signature");
//
//        // Initialize Razorpay client with your key and secret
//        RazorpayClient client = null;
//        try {
//            client = new RazorpayClient("YOUR_KEY_ID", "YOUR_KEY_SECRET");
//        } catch (RazorpayException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("Razorpay client initialization failed: " + e.getMessage());
//            return;
//        }
//
//        try {
//            // Create a map of payment verification details
//            HashMap<String, String> params = new HashMap<>();
//            params.put("razorpay_order_id", razorpayOrderId);
//            params.put("razorpay_payment_id", razorpayPaymentId);
//            params.put("razorpay_signature", razorpaySignature);
//
//            // Verify payment signature
//            client.paymentLink.fetch(razorpayPaymentId);
//
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().write("Payment successful! Payment ID: " + razorpayPaymentId);
//        } catch (RazorpayException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("Payment verification failed: " + e.getMessage());
//        }
//    }
//
//}
