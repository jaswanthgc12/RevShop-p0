package com.RevShop.entity;

import java.sql.Timestamp;

public class CartItem {
    private int cartItemId;    
    private buyers buyer;
    private Product product;
    private int quantity;  
    private Timestamp addedAt;

    public CartItem() {}

    public CartItem(int cartItemId, buyers buyer, Product product, int quantity, Timestamp addedAt) {
        this.cartItemId = cartItemId;
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity; 
        this.addedAt = addedAt;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public buyers getBuyer() {
        return buyer;
    }

    public void setBuyer(buyers buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", buyer=" + buyer +
                ", product=" + product +
                ", quantity=" + quantity +
                ", addedAt=" + addedAt +
                '}';
    }
}

