package com.RevShop.DAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RevShop.DAO.CartItemDAO;
import com.RevShop.entity.CartItem;
import com.RevShop.entity.Product;
import com.RevShop.entity.buyers;
import com.RevShop.utility.dbUtility;

public class CartItemDAOImpl implements CartItemDAO{

	@Override
    public void addToCart(CartItem cartItem) {
        String query = "INSERT INTO CartItems (buyer_id, product_id, quantity, added_at) VALUES (?, ?, ?, ?)";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, cartItem.getBuyer().getBuyerId());  
            ps.setInt(2, cartItem.getProduct().getProductId()); 
            ps.setInt(3, cartItem.getProduct().getQuantityInStock());  
            ps.setTimestamp(4, cartItem.getAddedAt());  
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void updateCartItem(CartItem cartItem) {
        String query = "UPDATE CartItems SET quantity = ? WHERE cart_item_id=? and buyer_id = ?";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, cartItem.getQuantity());
            ps.setInt(2, cartItem.getCartItemId());
            ps.setInt(3, cartItem.getBuyer().getBuyerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public List<CartItem> getCartItems(int buyerId) {
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT c.*, p.* FROM CartItems c INNER JOIN Products p ON c.product_id = p.product_id WHERE c.buyer_id = ?";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setPrice(new BigDecimal(rs.getDouble("price")));
                product.setDescription(rs.getString("description"));
                product.setQuantityInStock(rs.getInt("quantity_in_stock"));
                byte[] imageBytes = rs.getBytes("image");
                product.setImage(imageBytes);  

                buyers buyer = new buyers();
                buyer.setBuyerId(buyerId);

                CartItem cartItem = new CartItem();
                
                cartItem.setBuyer(buyer);
                cartItem.setProduct(product);
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setCartItemId(rs.getInt("cart_item_id"));
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    
    @Override
    public void removeFromCart(int cartItemId) {
        String query = "DELETE FROM CartItems WHERE cart_item_id=?";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void clearCart(int buyerId) {
        String query = "DELETE FROM CartItems WHERE buyer_id = ?";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, buyerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public CartItem getCartItem(int buyerId, int productId) {
        String query = "SELECT c.quantity, p.* FROM CartItems c INNER JOIN Products p ON c.product_id = p.product_id WHERE c.buyer_id = ? AND c.product_id = ?";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setPrice(new BigDecimal(rs.getDouble("price")));
                product.setDescription(rs.getString("description"));
                product.setQuantityInStock(rs.getInt("quantity_in_stock"));
                byte[] imageBytes = rs.getBytes("image");
                product.setImage(imageBytes);

                buyers buyer = new buyers();
                buyer.setBuyerId(buyerId);

                CartItem cartItem = new CartItem();
                cartItem.setBuyer(buyer);
                cartItem.setProduct(product);
                cartItem.setQuantity(rs.getInt("quantity"));
                return cartItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

        


	public int getCartItemId(int buyerId, int productId) {
          
		String query = "SELECT cart_item_id FROM cartitems WHERE buyer_id = ? AND product_id = ?";
        try (Connection con = dbUtility.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("cart_item_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


	
	
	

}
