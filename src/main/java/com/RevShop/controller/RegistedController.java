package com.RevShop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

import com.RevShop.entity.buyers; 
import com.RevShop.service.userService; 
import com.RevShop.serviceImpl.userServiceImpl; 


public class RegistedController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        Long phoneNumber;
        try {
            phoneNumber = Long.valueOf(request.getParameter("phone_number"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid phone number format!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; 
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        buyers buyer = new buyers(); 
        buyer.setName(name);
        buyer.setEmail(email);
        buyer.setPassword(password);
        buyer.setPhoneNumber(phoneNumber);
        buyer.setAddress(address);
        buyer.setCreatedAt(createdAt);
        
        userService userService = new userServiceImpl(); 

       
        if (userService.isRegistered(email)) {
            request.setAttribute("errorMessage", "User already registered!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            String result = userService.isRegistered(buyer); 
            if ("Registration successful!".equals(result)) {
            	request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                
            } else {
            	request.setAttribute("successMessage", "Registration successful!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
