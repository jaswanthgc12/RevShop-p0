package com.RevShop.serviceImpl;

import com.RevShop.entity.seller;
import com.RevShop.service.sellerServics;
import com.RevShop.DAO.*;
import com.RevShop.DAOImpl.*;

public class sellerServiceImpl implements sellerServics {

    private sellerDAO sellerDAO;

    public sellerServiceImpl() {
        this.sellerDAO = new sellerDAOImpl();
    }

    @Override
    public String registerSeller(seller seller) {
        if (sellerDAO.isRegistered(seller.getEmail())) {
            return "Seller already registered!";
        }
        return sellerDAO.registerSeller(seller);
    }

    @Override
    public boolean isRegistered(String email) {
        return sellerDAO.isRegistered(email);
    }

    @Override
    public seller getSellerDetails(int sellerId) {
        return sellerDAO.getSellerDetails(sellerId);
    }

    @Override
    public void deleteSeller(int sellerId) {
        sellerDAO.deleteSeller(sellerId);
    }

    @Override
    public void updateSellerDetails(seller seller) {
        sellerDAO.updateSellerDetails(seller);
    }

	@Override
	public seller getSellerDetailsByEmail(String email, String password) {
		seller Seller =sellerDAO.getUserDetailsByEmail(email, password);
		if (Seller != null) {
            // Check if the password matches
            if (Seller.getPassword().equals(password)) {
                return Seller;  // User authenticated successfully
            } else {
                // Password doesn't match
                System.out.println("Invalid password.");
                return null;
            }
        } else {
            // User not found
            System.out.println("User not found.");
            return null;
        }
	}
}
