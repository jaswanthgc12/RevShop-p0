package com.RevShop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.RevShop.entity.seller;
import com.RevShop.service.sellerServics;
import com.RevShop.serviceImpl.sellerServiceImpl;


public class sellerLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        
        sellerServics sellerService = new sellerServiceImpl();

        
        if (sellerService.isRegistered(email)) {
            seller seller = sellerService.getSellerDetailsByEmail(email, password);
            
            
            if (seller!=null && seller.getPassword().equals(password)) {
                
                request.getSession().setAttribute("sellerId", seller.getSellerId());
                response.sendRedirect("sellerDashboard.jsp"); 
            } else {
                
                request.setAttribute("errorMessage", "Invalid email or password!");
                request.getRequestDispatcher("sellerLogin.jsp").forward(request, response);
            }
        } else {
            
            request.setAttribute("errorMessage", "Seller not registered!");
            request.getRequestDispatcher("sellerRegistration.jsp").forward(request, response);
        }
    }
}
