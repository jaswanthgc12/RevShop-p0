package com.RevShop.entity;

import java.sql.Timestamp;

public class Review {

    private int reviewId;
    private int productId;
    private int buyerId;
    private int rating; 
    private String comment;
    private Timestamp reviewDate;
    private buyers buyer;
    private Product product;

    // Constructors
    public Review() {}

    public Review(int reviewId, int productId, int buyerId, int rating, String comment, Timestamp reviewDate) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.buyerId = buyerId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
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

	// Getters and Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", productId=" + productId +
                ", buyerId=" + buyerId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
