package com.nagoya.tracker.repository;

import com.nagoya.tracker.domain.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository {
    private final String url = "jdbc:h2:mem:nagoyadb;DB_CLOSE_DELAY=-1";
    private final String user = "sa";
    private final String password = "";

    // 1. Init DB
    public void initDb() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

        } catch (SQLException e) {
            System.out.println("Init DB Fail: " + e.getMessage());
        }
    }

    // 2. Save DB
    public void save(Product product) {
        // 2-1. Defense SQL Injection
        String sql = "INSERT INTO PRODUCT (store_name, "
    }

    // 3. Select DB
}
