package com.RevShop.DAOImpl;

import com.RevShop.DAO.UserDAO;
import com.RevShop.entity.buyers;
import com.RevShop.utility.dbUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAOImpl implements UserDAO {

    @Override
    public String registerUser(buyers buyer) {
        String result = "User registration failed.";
        String sql = "INSERT INTO Buyers (name, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, buyer.getName());
            pstmt.setString(2, buyer.getEmail());
            pstmt.setString(3, buyer.getPassword());
            pstmt.setLong(4, buyer.getPhoneNumber());
            pstmt.setString(5, buyer.getAddress());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                result = "User registered successfully.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isRegistered(String email) {
        String sql = "SELECT COUNT(*) FROM Buyers WHERE email = ?";
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
    public buyers getUserDetails(int buyerId) {
        String sql = "SELECT * FROM Buyers WHERE buyer_id = ?";
        buyers buyer = null;
        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, buyerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                buyer = new buyers();
                buyer.setBuyerId(rs.getInt("buyer_id"));
                buyer.setName(rs.getString("name"));
                buyer.setEmail(rs.getString("email"));
                buyer.setPassword(rs.getString("password"));
                buyer.setPhoneNumber(rs.getLong("phone_number"));
                buyer.setAddress(rs.getString("address"));
                buyer.setCreatedAt(rs.getTimestamp("created_at"));
                buyer.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyer;
    }

    @Override
    public void deleteUser(int buyerId) {
        String sql = "DELETE FROM Buyers WHERE buyer_id = ?";
        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, buyerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserDetails(buyers buyer) {
        String sql = "UPDATE Buyers SET name = ?, email = ?, password = ?, phone_number = ?, address = ? WHERE buyer_id = ?";
        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, buyer.getName());
            pstmt.setString(2, buyer.getEmail());
            pstmt.setString(3, buyer.getPassword());
            pstmt.setLong(4, buyer.getPhoneNumber());
            pstmt.setString(5, buyer.getAddress());
            pstmt.setInt(6, buyer.getBuyerId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public buyers getUserDetailsByEmail(String email, String password) {
        String sql = "SELECT * FROM buyers WHERE email = ? AND password = ?";
        buyers buyer = null;

        try (Connection connection = dbUtility.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet res = pstmt.executeQuery();
            
            
            if (res.next()) {
                buyer = new buyers();
                buyer.setBuyerId(res.getInt("buyer_id"));
                buyer.setName(res.getString("name"));
                buyer.setEmail(res.getString("email"));
                buyer.setPassword(res.getString("password"));
                buyer.setPhoneNumber(res.getLong("phone_number"));
                buyer.setAddress(res.getString("address"));
                buyer.setCreatedAt(res.getTimestamp("created_at"));
                buyer.setUpdatedAt(res.getTimestamp("updated_at"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return buyer; 
    }

}
