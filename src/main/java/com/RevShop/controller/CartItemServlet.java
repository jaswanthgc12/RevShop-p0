package com.RevShop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.RevShop.entity.CartItem;
import com.RevShop.entity.Product;
import com.RevShop.entity.buyers;
import com.RevShop.service.CartItemServices;
import com.RevShop.serviceImpl.CartItemServiceImpl;


public class CartItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartItemServices cartItemServices;

    @Override
    public void init() throws ServletException {
        cartItemServices = new CartItemServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addToCart(request, response);
                    break;
                case "update":
                    updateCartItem(request, response);
                    break;
                case "remove":
                    removeFromCart(request, response);
                    break;
                case "clear":
                    clearCart(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                    break;
            }
        } catch (Exception e) {
            handleError(response, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("get".equals(action)) {
                getCartItems(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } catch (Exception e) {
            handleError(response, e);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("buyer") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int buyerId = (int) session.getAttribute("buyer");
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Product product = new Product();
            product.setProductId(productId);
            product.setQuantityInStock(quantity); 

            buyers buyer = new buyers(); 
            buyer.setBuyerId(buyerId);

            CartItem cartItem = new CartItem();
            cartItem.setBuyer(buyer);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setAddedAt(new Timestamp(System.currentTimeMillis()));

            cartItemServices.addToCart(cartItem,session);
            response.sendRedirect("cart.jsp");
        } catch (NumberFormatException e) {
            handleError(response, new Exception("Invalid input. Please enter valid numeric values."));
        } catch (Exception e) {
            handleError(response, e);
        }
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("buyer") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int buyerId = (int) session.getAttribute("buyer");
            Integer cartItemId = (Integer) session.getAttribute("cartId");
            String quantityStr = request.getParameter("quantity");

            System.out.println("Updating Cart: buyerId = " + buyerId + ", cartItemId = " + cartItemId + ", quantity = " + quantityStr);

            if ( quantityStr == null || quantityStr.isEmpty()) {
                handleError(response, new Exception("Missing or invalid cartItemId or quantity."));
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    
                    cartItemServices.removeFromCart(cartItemId); 
                    response.sendRedirect("cart.jsp");
                    return;
                }
            } catch (NumberFormatException e) {
                handleError(response, new Exception("Invalid input. Please enter valid numeric values."));
                return;
            }


            CartItem cartItem = new CartItem();
            cartItem.setCartItemId((cartItemId)); 
            cartItem.setQuantity(quantity);
            
            buyers buyer = new buyers();
            buyer.setBuyerId(buyerId);
            cartItem.setBuyer(buyer);

            
            cartItemServices.updateCartItem(cartItem);
            response.sendRedirect("cart.jsp?msg=Cart updated successfully.");

        } catch (Exception e) {
            handleError(response, e);
        }
    }



    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("buyer") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int buyerId = (int) session.getAttribute("buyer");
            int cartItemId = Integer.parseInt(request.getParameter("cartId")); 
            System.out.println(cartItemId + " this is my cartItemId");

            cartItemServices.removeFromCart(cartItemId); 
            response.sendRedirect("cart.jsp"); 
        } catch (NumberFormatException e) {
            handleError(response, new Exception("Invalid input. Please enter valid numeric values."));
        } catch (Exception e) {
            handleError(response, e);
        }
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("buyer") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int buyerId = (int) session.getAttribute("buyer");

            cartItemServices.clearCart(buyerId);
            response.getWriter().println("Cart cleared successfully.");
        } catch (Exception e) {
            handleError(response, e);
        }
    }

    private void getCartItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("buyer") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int buyerId = (int) session.getAttribute("buyer");

            List<CartItem> cartItems = cartItemServices.getCartItems(buyerId);
            if (cartItems.isEmpty()) {
                response.getWriter().println("Your cart is empty.");
            } else {
                for (CartItem item : cartItems) {
                    response.getWriter().println("Product: " + item.getProduct().getProductName() + ", Quantity: " + item.getQuantity());
                }
            }
        } catch (Exception e) {
            handleError(response, e);
        }
    }

    private void handleError(HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace(); 
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
