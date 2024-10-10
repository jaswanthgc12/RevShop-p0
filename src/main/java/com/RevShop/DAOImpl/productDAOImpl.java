package com.RevShop.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RevShop.DAO.productDAO;
import com.RevShop.entity.Product;
import com.RevShop.entity.Review;
import com.RevShop.entity.buyers;
import com.RevShop.utility.dbUtility;

public class productDAOImpl implements productDAO{
	@Override
	public void addProduct(Product product) {
		String sql = "INSERT INTO products (seller_id, name, description, price, discounted_price, image, image_url, quantity_in_stock, category, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = dbUtility.getInstance().getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, product.getSellerId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getDescription());
            pstmt.setBigDecimal(4, product.getPrice());
            pstmt.setBigDecimal(5, product.getDiscountedPrice());
            pstmt.setBytes(6, product.getImage());
            pstmt.setString(7, product.getImageUrl());
            pstmt.setInt(8, product.getQuantityInStock());
            pstmt.setString(9, product.getCategory());
            pstmt.setTimestamp(10, product.getCreatedAt());

            pstmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void updateProduct(Product product) {
		String sql = "UPDATE Products SET seller_id = ?, name = ?, description = ?, price = ?, discounted_price = ?, image = ?, image_url = ?, quantity_in_stock = ?, category = ?, created_at = ? WHERE product_id = ?";
        try (Connection conn = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getSellerId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getDescription());
            pstmt.setBigDecimal(4, product.getPrice());
            pstmt.setBigDecimal(5, product.getDiscountedPrice());
            pstmt.setBytes(6, product.getImage());
            pstmt.setString(7, product.getImageUrl());
            pstmt.setInt(8, product.getQuantityInStock());
            pstmt.setString(9, product.getCategory());
            pstmt.setTimestamp(10, product.getCreatedAt());
            pstmt.setInt(11, product.getProductId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void updateProductByQuantity(int productId, int quantityInStock) {
		String sql = "UPDATE Products SET quantity_in_stock = ? WHERE product_id = ?";
		try (Connection connection = dbUtility.getInstance().getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, quantityInStock);
			pstmt.setInt(2,productId);

           pstmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public Product getProductById(int productId) {
		Product product = null;
		String sql = "SELECT * FROM Products Where product_id=?";
		try (Connection connection = dbUtility.getInstance().getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            
	            pstmt.setInt(1, productId);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	            	product = new Product(
	                        rs.getInt("product_id"),
	                        rs.getInt("seller_id"),
	                        rs.getString("name"),
	                        rs.getString("description"),
	                        rs.getBigDecimal("price"),
	                        rs.getBigDecimal("discounted_price"),
	                        rs.getBytes("image"),
	                        rs.getString("image_url"),
	                        rs.getInt("quantity_in_stock"),
	                        rs.getString("category"),
	                        rs.getTimestamp("created_at")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return product;
	}

	@Override
	public void deleteProduct(int productId) {
		String sql = "DELETE FROM Products WHERE product_id = ?";
        try (Connection conn = dbUtility.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Product> getAllProducts() {
		String sql = "SELECT * FROM products";
		List<Product> products = new ArrayList<>();
		try (Connection connection = dbUtility.getInstance().getConnection();
	             Statement stmt = connection.createStatement();
				 ResultSet rs = stmt.executeQuery(sql)) {
	            
	        
	             while(rs.next()) {
	            	Product product = new Product(
	                        rs.getInt("product_id"),
	                        rs.getInt("seller_id"),
	                        rs.getString("name"),
	                        rs.getString("description"),
	                        rs.getBigDecimal("price"),
	                        rs.getBigDecimal("discounted_price"),
	                        rs.getBytes("image"),
	                        rs.getString("image_url"),
	                        rs.getInt("quantity_in_stock"),
	                        rs.getString("category"),
	                        rs.getTimestamp("created_at")
	                );
	            	products.add(product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return products;
	}

	@Override
	public List<Product> getProductBySellerId(int sellerId) {
		String sql = "SELECT * FROM products WHERE seller_id = ?";
		List<Product> products = new ArrayList<>();
		try (Connection connection = dbUtility.getInstance().getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            
	        	pstmt.setInt(1, sellerId);
	        	ResultSet rs = pstmt.executeQuery();
	             while(rs.next()) {
	            	Product product = new Product(
	                        rs.getInt("product_id"),
	                        rs.getInt("seller_id"),
	                        rs.getString("name"),
	                        rs.getString("description"),
	                        rs.getBigDecimal("price"),
	                        rs.getBigDecimal("discounted_price"),
	                        rs.getBytes("image"),
	                        rs.getString("image_url"),
	                        rs.getInt("quantity_in_stock"),
	                        rs.getString("category"),
	                        rs.getTimestamp("created_at")
	                );
	            	products.add(product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return products;
	}

	@Override
	public void addReview(Review review) {
		String query = "INSERT INTO Reviews (product_id, buyer_id, rating, comment) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = dbUtility.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, review.getProductId());
            ps.setInt(2, review.getBuyerId());
            ps.setInt(3, review.getRating());
            ps.setString(4, review.getComment());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Review> getReviewsByProductId(int productId) {
		List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM Reviews WHERE product_id = ?";
        
        try (Connection conn = dbUtility.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("review_id"));
                review.setProductId(rs.getInt("product_id"));
                review.setBuyerId(rs.getInt("buyer_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setReviewDate(rs.getTimestamp("review_date"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
	}

	@Override
	public List<String> getAllCategories() {
		List<String> categories = new ArrayList<>();
        String query = "SELECT DISTINCT category FROM Products WHERE category IS NOT NULL";

        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                categories.add(resultSet.getString("category"));
            }
        }catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return categories;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE category = ?";

        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, category);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setProductName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getBigDecimal("price"));
                    product.setDiscountedPrice(resultSet.getBigDecimal("discounted_price"));
                    product.setImage(resultSet.getBytes("image"));
                    product.setQuantityInStock(resultSet.getInt("quantity_in_stock"));
                    product.setCategory(resultSet.getString("category"));
                    products.add(product);
                }
            }
        }catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return products;
	}

}
