package com.RevShop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.RevShop.entity.buyers;
import com.RevShop.service.userService;
import com.RevShop.serviceImpl.userServiceImpl;


public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        userService userService = new userServiceImpl();
        
        if ( userService.isRegistered(email)) {
            buyers buyer = userService.getUserDetailsByEmail(email,password);
            if (buyer!=null && buyer.getPassword().equals(password)) {
            	
            	request.getSession().setAttribute("buyerEmail", email);
                request.getSession().setAttribute("buyer", buyer.getBuyerId());
                response.sendRedirect("menu.jsp"); 
            } else {
                
                request.setAttribute("errorMessage", "Invalid email or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            
            request.setAttribute("errorMessage", "User not registered!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
