package com.RevShop.DAO;

import com.RevShop.entity.buyers;

public interface UserDAO {
	
	
	public String registerUser(buyers buyer);
	
	public boolean isRegistered(String email);
	
	public buyers getUserDetails(int buyerId);
	
	public void deleteUser(int buyerId);
	
	public void updateUserDetails(buyers buyer);
	
	public buyers getUserDetailsByEmail(String email,String password);
}
