package com.RevShop.service;

import com.RevShop.entity.buyers;

public interface userService {
	
	String isRegistered(buyers buyer);
	
	boolean isRegistered(String email);
    
    
    buyers getUserDetails(int buyerId);
    
    
    void deleteUser(int buyerId);
    
    
    void updateUserDetails(buyers buyer);
    
    buyers getUserDetailsByEmail(String email,String password);
}
