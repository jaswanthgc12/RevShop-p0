//package com.RevShop.controller;
//
//import java.io.IOException;
//import java.util.List;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import com.RevShop.entity.Product;
//import com.RevShop.service.productService;
//import com.RevShop.serviceImpl.productServiceImpl;
//
//
//public class ProductCategoryServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    
//    private productService productService;
//
//    public ProductCategoryServlet() {
//        this.productService = new productServiceImpl();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String category = request.getParameter("category");
//        
//        
//        List<Product> products;
//        if (category == null || category.equals("All")) {
//            products = productService.getAllProducts(); 
//        } else {
//        	products = productService.getAllProducts(); 
//        }
//
//     
//        request.setAttribute("productList", products);
//        request.setAttribute("selectedCategory", category);
//        
//        
//        request.getRequestDispatcher("menu.jsp").forward(request, response);
//    }
//}
