package com.RevShop.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RevShop.DAO.sellerDAO;
import com.RevShop.entity.buyers;
import com.RevShop.entity.seller;
import com.RevShop.utility.dbUtility;

public class sellerDAOImpl implements sellerDAO {

    @Override
    public String registerSeller(seller seller) {
        String result = "Seller registration failed.";
        String sql = "INSERT INTO Sellers (name, email, password, business_name, business_details, phone_number, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, seller.getSellerName());
            pstmt.setString(2, seller.getEmail());
            pstmt.setString(3, seller.getPassword());
            pstmt.setString(4, seller.getBusinessType());
            pstmt.setString(5, seller.getBusinessDetails());
            pstmt.setLong(6, seller.getPhoneNumber());
            pstmt.setString(7, seller.getAddress());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                result = "Seller registered successfully.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isRegistered(String email) {
        String sql = "SELECT COUNT(*) FROM Sellers WHERE email = ?";
        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public seller getSellerDetails(int sellerId) {
        String sql = "SELECT * FROM Sellers WHERE sellerId = ?";
        seller seller = null;
        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, sellerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                seller = new seller();
                seller.setSellerId(rs.getInt("seller_id"));
                seller.setSellerName(rs.getString("name"));
                seller.setEmail(rs.getString("email"));
                seller.setPassword(rs.getString("password"));
                seller.setBusinessType(rs.getString("business_name"));
                seller.setBusinessDetails(rs.getString("business_details"));
                seller.setPhoneNumber(rs.getLong("phone_number"));
                seller.setAddress(rs.getString("address"));
                seller.setCreatedAt(rs.getTimestamp("createdAt"));
                seller.setUpdatedAt(rs.getTimestamp("updatedAt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seller;
    }

    @Override
    public void deleteSeller(int sellerId) {
        String sql = "DELETE FROM Sellers WHERE sellerId = ?";
        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, sellerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSellerDetails(seller seller) {
        String sql = "UPDATE Sellers SET SellerName = ?, email = ?, password = ?, businessType = ?, businessDetails = ?, phoneNumber = ?, address = ? WHERE sellerId = ?";
        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, seller.getSellerName());
            pstmt.setString(2, seller.getEmail());
            pstmt.setString(3, seller.getPassword());
            pstmt.setString(4, seller.getBusinessType());
            pstmt.setString(5, seller.getBusinessDetails());
            pstmt.setLong(6, seller.getPhoneNumber());
            pstmt.setString(7, seller.getAddress());
            pstmt.setInt(8, seller.getSellerId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public seller getUserDetailsByEmail(String email, String password) {
		String sql = "SELECT * FROM Sellers WHERE email = ? AND password = ?";
        seller seller = null;

        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet res = pstmt.executeQuery();
            
            
            if (res.next()) {
                seller = new seller();
                seller.setSellerId(res.getInt("seller_id"));
                seller.setSellerName(res.getString("name"));
                seller.setEmail(res.getString("email"));
                seller.setPassword(res.getString("password"));
                seller.setBusinessType(res.getString("business_name"));
                seller.setBusinessDetails(res.getString("business_details"));
                seller.setPhoneNumber(res.getLong("phone_number"));
                seller.setAddress(res.getString("address"));
                seller.setCreatedAt(res.getTimestamp("created_at"));
                seller.setUpdatedAt(res.getTimestamp("updated_at"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return seller;
	}
}