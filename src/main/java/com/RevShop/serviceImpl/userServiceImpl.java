package com.RevShop.serviceImpl;

import com.RevShop.DAO.UserDAO;
import com.RevShop.DAOImpl.userDAOImpl;
import com.RevShop.entity.buyers;
import com.RevShop.service.userService;

public class userServiceImpl implements userService {

    private UserDAO userDAO;

    
    public userServiceImpl() {
        this.userDAO = new userDAOImpl();
    }

    @Override
    public String isRegistered(buyers buyer) {
        if (userDAO.isRegistered(buyer.getEmail())) {
            return "User already registered!";
        } 
        return userDAO.registerUser(buyer);
    }

    @Override
    public boolean isRegistered(String email) {
        return userDAO.isRegistered(email);
    }

    @Override
    public buyers getUserDetails(int buyerId) {
        return userDAO.getUserDetails(buyerId);
    }

    @Override
    public void deleteUser(int buyerId) {
        userDAO.deleteUser(buyerId);
    }

    @Override
    public void updateUserDetails(buyers buyer) {
        userDAO.updateUserDetails(buyer);
    }

    @Override
    public buyers getUserDetailsByEmail(String email, String password) {
        buyers buyer = userDAO.getUserDetailsByEmail(email, password);
        
        if (buyer != null) {
            
            if (buyer.getPassword().equals(password)) {
                return buyer;  // User authenticated successfully
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
