package com.RevShop.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbUtility {
    private static String url;
    private static String username;
    private static String password;

    private static dbUtility instance;

    private dbUtility() {
        try (InputStream inp = dbUtility.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();

            if (inp == null) {
                throw new IOException("Unable to find db.properties");
            }
            prop.load(inp);

            url = prop.getProperty("db.URL");
            username = prop.getProperty("db.USER");
            password = prop.getProperty("db.PASSWORD");

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException e) {
            System.err.println("Error reading database properties: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        }
    }
    
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    
    
    
    public static dbUtility getInstance() {
        if (instance == null) {
            synchronized (dbUtility.class) {
                if (instance == null) {
                    instance = new dbUtility();
                }
            }
        }
        return instance;
    }

    

}
