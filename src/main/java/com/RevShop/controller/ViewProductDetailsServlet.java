package com.RevShop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.RevShop.entity.Product;
import com.RevShop.entity.Review;
import com.RevShop.service.productService;
import com.RevShop.serviceImpl.productServiceImpl;


public class ViewProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private productService ProductService;
       
    
    public ViewProductDetailsServlet() {
        this.ProductService = new productServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
        
        
        Product product = ProductService.getProductById(productId);

        
        List<Review> reviews = ProductService.getReviewsByProductId(productId);

        
        request.setAttribute("product", product);
        request.setAttribute("reviews", reviews);

        
        request.getRequestDispatcher("productDetails.jsp").forward(request, response);
	}
}
