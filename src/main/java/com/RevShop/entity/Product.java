package com.RevShop.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

public class Product {
    private int productId;
    private int sellerId;
    private String productName;
    private String description;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private byte[] image;
    private String imageUrl;
    private int quantityInStock;
    private String category;
    private Timestamp createdAt;

    public Product() {
    	
    }
    public Product(int productId, int sellerId, String productName, String description, BigDecimal price,
                   BigDecimal discountedPrice, byte[] image, String imageUrl, int quantityInStock,
                   String category, Timestamp createdAt) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.image = image;
        this.imageUrl = imageUrl;
        this.quantityInStock = quantityInStock;
        this.category = category;
        this.createdAt = createdAt;
    }

    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", sellerId=" + sellerId + ", productName=" + productName
                + ", description=" + description + ", price=" + price + ", discountedPrice=" + discountedPrice
                + ", image=" + Arrays.toString(image) + ", imageUrl=" + imageUrl + ", quantityInStock=" + quantityInStock
                + ", category=" + category + ", createdAt=" + createdAt + "]";
    }
}
