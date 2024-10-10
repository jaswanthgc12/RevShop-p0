package com.RevShop.DAO;

import java.util.List;
import com.RevShop.entity.CartItem;

public interface CartItemDAO {
    void addToCart(CartItem cartItem); 
    void updateCartItem(CartItem cartItem); 
    List<CartItem> getCartItems(int buyerId); 
    void removeFromCart(int cartItemId); 
    void clearCart(int buyerId); 
    CartItem getCartItem(int buyerId, int productId);
    int getCartItemId(int buyerId, int productId);  
}
