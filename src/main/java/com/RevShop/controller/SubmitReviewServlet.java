package com.RevShop.controller;


import com.RevShop.DAOImpl.productDAOImpl;
import com.RevShop.entity.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SubmitReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int buyerId = Integer.parseInt(request.getParameter("buyerId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        Review review = new Review();
        review.setProductId(productId);
        review.setBuyerId(buyerId);
        review.setRating(rating);
        review.setComment(comment);

        productDAOImpl productDAO = new productDAOImpl();
        productDAO.addReview(review);

        request.setAttribute("message", "Review submitted successfully!");
        response.sendRedirect("review.jsp?productId=" + productId);
    }
}
