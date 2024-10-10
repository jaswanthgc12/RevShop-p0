package com.RevShop.controller;

import com.RevShop.entity.seller;
import com.RevShop.service.sellerServics;
import com.RevShop.serviceImpl.sellerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


public class SellerRegistrationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private sellerServics sellerService;

    @Override
    public void init() throws ServletException {
        
        sellerService = new sellerServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String sellerName = request.getParameter("sellerName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String businessType = request.getParameter("businessType");
        String businessDetails = request.getParameter("businessDetails");
        
        Long phoneNumber;
            phoneNumber = Long.valueOf(request.getParameter("phone_number"));
        
        
        String address = request.getParameter("address");

        
        seller newSeller = new seller();
        newSeller.setSellerName(sellerName);
        newSeller.setEmail(email);
        newSeller.setPassword(password);
        newSeller.setBusinessType(businessType);
        newSeller.setBusinessDetails(businessDetails);
        newSeller.setPhoneNumber(phoneNumber);
        newSeller.setAddress(address);
        newSeller.setCreatedAt(new Timestamp(System.currentTimeMillis()));  // setting the current timestamp
        newSeller.setUpdatedAt(new Timestamp(System.currentTimeMillis()));  // setting the current timestamp

        
        String registrationResult = sellerService.registerSeller(newSeller);

        
        if ("Seller registered successfully.".equals(registrationResult)) {
            
            request.setAttribute("message", "Seller registered successfully!");
            request.getRequestDispatcher("sellerLogin.jsp").forward(request, response);
        } else {
            
            request.setAttribute("message", registrationResult);  
            request.getRequestDispatcher("sellerRegistration.jsp").forward(request, response);
        }
    }
}
