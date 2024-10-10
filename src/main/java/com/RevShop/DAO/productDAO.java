package com.RevShop.DAO;

import java.util.List;

import com.RevShop.entity.Product;
import com.RevShop.entity.Review;

public interface productDAO {
	
	
	void addProduct(Product product);
	
	void updateProduct(Product product);
	
	void updateProductByQuantity(int productId,int quantityInStock);
	
	Product getProductById(int productId);
	
	void deleteProduct(int productId);
	
	List<Product> getAllProducts();
	
	List<Product> getProductBySellerId(int sellerId);
	
	void addReview(Review review);
	
	List<Review> getReviewsByProductId(int productId);
	
	List<String> getAllCategories();
	
	List<Product> getProductsByCategory(String category);
	
	
}
