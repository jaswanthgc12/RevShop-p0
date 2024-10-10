package com.RevShop.entity;


import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderHistory {
    private int orderHistoryId;
    private int buyerId;
    private int orderId;
    private String orderStatus;
    private BigDecimal totalAmount;
    private Timestamp createdAt;
    private Order order;

    
    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderHistory() {}

    public OrderHistory(int orderHistoryId, int buyerId, int orderId, String orderStatus, BigDecimal totalAmount, Timestamp createdAt) {
        this.orderHistoryId = orderHistoryId;
        this.buyerId = buyerId;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    
    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

