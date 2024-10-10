package com.RevShop.serviceImpl;

import java.util.List;

import com.RevShop.DAO.productDAO;
import com.RevShop.DAOImpl.productDAOImpl;
import com.RevShop.entity.Product;
import com.RevShop.entity.Review;
import com.RevShop.service.productService;

public class productServiceImpl implements  productService{
	
	
	private productDAO productDao;
	
	public productServiceImpl() {
		this.productDao = new productDAOImpl();
	}

	@Override
	public void addProduct(Product product) {
		if(product != null) {
			productDao.addProduct(product);
		}else {
			throw new IllegalArgumentException("Product cannot be null");
		}
		
	}

	@Override
	public void updateProduct(Product product) {
		if (product != null && product.getProductId() > 0) {
            productDao.updateProduct(product);
        } else {
            throw new IllegalArgumentException("Invalid product or product ID");
        }
		
	}

	@Override
	public void updateProductByQuantity(int productId, int quantityInStock) {
		if (productId > 0 && quantityInStock >= 0) {
            productDao.updateProductByQuantity(productId, quantityInStock);
        } else {
            throw new IllegalArgumentException("Invalid product ID or quantity");
        }
		
	}

	@Override
	public Product getProductById(int productId) {
		if (productId > 0) {
            return productDao.getProductById(productId);
        } else {
            throw new IllegalArgumentException("Invalid product ID");
        }
	}

	@Override
	public void deleteProduct(int productId) {
		if(productId > 0) {
			productDao.deleteProduct(productId);
		}else {
			throw new IllegalArgumentException("Invalid product ID");
		}
		
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public List<Product> getProductBySellerId(int sellerId) {
		if(sellerId>0) {
			return productDao.getProductBySellerId(sellerId);
		}else {
			throw new IllegalArgumentException("Invalid seller ID");
		}
	}

	@Override
	public void addReview(Review review) {
		if (review != null && review.getProductId() > 0 && review.getBuyerId() > 0 && review.getRating() >= 1 && review.getRating() <= 5) {
            productDao.addReview(review);
        } else {
            throw new IllegalArgumentException("Invalid review data");
        }
		
	}

	@Override
	public List<Review> getReviewsByProductId(int productId) {
		if (productId > 0) {
	        return productDao.getReviewsByProductId(productId);
	    } else {
	        throw new IllegalArgumentException("Invalid product ID");
	    }
	}

	@Override
	public List<String> getAllCategories() {
		return productDao.getAllCategories();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		if (category != null && !category.isEmpty()) {
            return productDao.getProductsByCategory(category);
        } else {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
	}
	
	
	
}
