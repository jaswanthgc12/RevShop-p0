package com.RevShop.controller;

import com.RevShop.DAO.productDAO;
import com.RevShop.entity.Product;
import com.RevShop.service.productService;
import com.RevShop.serviceImpl.productServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;


@MultipartConfig(maxFileSize = 16177215) 
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private productService productService;

    public AddProductServlet() {
        this.productService = new productServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer sellerId = (Integer) session.getAttribute("sellerId");
        
        if (sellerId == null) {
            response.sendRedirect("sellerLogin.jsp");
            return;
        }
        
        
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        BigDecimal discountedPrice = new BigDecimal(request.getParameter("discountedPrice"));
        String imageUrl = request.getParameter("imageUrl");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String category = request.getParameter("category");

        
        Part imagePart = request.getPart("image");
        byte[] imageBytes = null;

        if (imagePart != null && imagePart.getSize() > 0) {
            InputStream inputStream = imagePart.getInputStream();
            imageBytes = new byte[(int) imagePart.getSize()];
            inputStream.read(imageBytes);
            inputStream.close();
        }

        
        Product newProduct = new Product(
                0, 
                sellerId,
                productName,
                description,
                price,
                discountedPrice,
                imageBytes, 
                imageUrl,
                quantity,
                category,
                new Timestamp(System.currentTimeMillis()) 
        );

        
        productService.addProduct(newProduct);

        
        response.sendRedirect("menu.jsp");
    }
}
