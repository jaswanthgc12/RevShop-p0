package com.RevShop.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.RevShop.DAO.CartItemDAO;
import com.RevShop.DAOImpl.CartItemDAOImpl;
import com.RevShop.entity.CartItem;
import com.RevShop.service.CartItemServices;

import jakarta.servlet.http.HttpSession;

public class CartItemServiceImpl implements CartItemServices{

	private CartItemDAO cartItemDAO;

    public CartItemServiceImpl() {
        this.cartItemDAO = new CartItemDAOImpl();
    }

    public void addToCart(CartItem cartItem, HttpSession session) {
        // Persist the CartItem to the database
        cartItemDAO.addToCart(cartItem);
        
        // Retrieve the existing cart from the session
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");

        // If the cart is null, create a new list
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Add the new CartItem to the cart
        cartItems.add(cartItem);
        
        // Update the session with the new cart
        session.setAttribute("cart", cartItems);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
        System.out.println("this is getting updated");
    }

    @Override
    public List<CartItem> getCartItems(int buyerId) {
        return cartItemDAO.getCartItems(buyerId);
    }

    @Override
    public void removeFromCart(int cartItemId) {
        cartItemDAO.removeFromCart(cartItemId);
        
    }

    @Override
    public void clearCart(int buyerId) {
        cartItemDAO.clearCart(buyerId);
    }

    @Override
    public CartItem getCartItem(int buyerId, int productId) {
        return cartItemDAO.getCartItem(buyerId, productId);
    }

}
