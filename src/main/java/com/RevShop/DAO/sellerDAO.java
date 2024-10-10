package com.RevShop.DAO;

import com.RevShop.entity.buyers;
import com.RevShop.entity.seller;

public interface sellerDAO {
	String registerSeller(seller seller);
    boolean isRegistered(String email);
    seller getSellerDetails(int sellerId);
    void deleteSeller(int sellerId);
    void updateSellerDetails(seller seller);
    public seller getUserDetailsByEmail(String email, String password);
}
