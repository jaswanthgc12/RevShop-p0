package com.RevShop.entity;

import java.sql.Timestamp;

public class seller {
	private int sellerId;
	private String SellerName;
	private String email;
	private String password;
	private String businessType;
	private String businessDetails;
	private long phoneNumber;
	private String address;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	public seller() {
		
	}
	public seller( String sellerName, String email, String password, String businessType,
			String businessDetails, long phoneNumber, String address, Timestamp lastLogin, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		
		SellerName = sellerName;
		this.email = email;
		this.password = password;
		this.businessType = businessType;
		this.businessDetails = businessDetails;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
	}
	
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerName() {
		return SellerName;
	}
	public void setSellerName(String sellerName) {
		SellerName = sellerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessDetails() {
		return businessDetails;
	}
	public void setBusinessDetails(String businessDetails) {
		this.businessDetails = businessDetails;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getSellerId() {
		return sellerId;
	}
	@Override
	public String toString() {
		return "seller [sellerId=" + sellerId + ", SellerName=" + SellerName + ", email=" + email + ", password="
				+ password + ", businessType=" + businessType + ", businessDetails=" + businessDetails
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
