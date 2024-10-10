package com.RevShop.service;

import java.util.List;

import com.RevShop.entity.CartItem;

import jakarta.servlet.http.HttpSession;

public interface CartItemServices {
	void addToCart(CartItem cartItem, HttpSession session);;

    void updateCartItem(CartItem cartItem);

    List<CartItem> getCartItems(int buyerId);

    void clearCart(int buyerId);

    CartItem getCartItem(int buyerId, int productId);

	void removeFromCart(int cartItemId);
}
