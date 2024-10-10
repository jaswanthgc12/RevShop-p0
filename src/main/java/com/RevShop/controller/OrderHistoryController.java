package com.RevShop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.RevShop.DAO.OrderDao;
import com.RevShop.DAOImpl.OrderDaoImpl;
import com.RevShop.entity.Order;
import com.RevShop.entity.OrderHistory;
import com.RevShop.service.OrderService;
import com.RevShop.serviceImpl.orderServiceImpl;

/**
 * Servlet implementation class OrderHistoryController
 */
public class OrderHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private OrderService orderService;

    public OrderHistoryController() {
        this.orderService = new orderServiceImpl(); 
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		HttpSession session = request.getSession(false); 
        if (session == null) {
            response.sendRedirect("login.jsp");
            return; 
        }

        
        Integer buyerId = (Integer) session.getAttribute("buyer");
        System.out.println("Buyer ID retrieved from session: " + buyerId);
        if (buyerId == null) {
            response.sendRedirect("login.jsp"); 
            return; 
        }

        
        List<Order> orders = orderService.getOrdersByUserId(buyerId);
        System.out.println("Order history retrieved: " + (orders != null ? orders.size() + " orders found" : "No orders found"));

        
        if (orders != null && !orders.isEmpty()) {
            request.setAttribute("orderHistory", orders); 
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "No orders found in your order history.");
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); 
    }

}
