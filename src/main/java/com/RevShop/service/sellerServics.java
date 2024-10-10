package com.RevShop.service;

import com.RevShop.entity.seller;

public interface sellerServics {
	String registerSeller(seller seller);
    boolean isRegistered(String email);
    seller getSellerDetails(int sellerId);
    void deleteSeller(int sellerId);
    void updateSellerDetails(seller seller);
    seller getSellerDetailsByEmail(String email,String password);
}
