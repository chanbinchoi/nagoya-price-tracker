package com.nagoya.tracker.repository;

import com.nagoya.tracker.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final String url = "jdbc:h2:mem:nagoyadb;DB_CLOSE_DELAY=-1";
    private final String user = "sa";
    private final String password = "";

    // 1. Init DB
    public void initDb() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS PRODUCT (store_name VARCHAR(255), item_name VARCHAR(255), price INT)");
        } catch (SQLException e) {
            System.out.println("Init DB Fail: " + e.getMessage());
        }
    }

    // 2. Save DB
    public void save(Product product) {
        // 2-1. Defense SQL Injection
        String sql = "INSERT INTO PRODUCT (store_name, item_name, price) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 2-2. ? Mapping Value (1 start)
            pstmt.setString(1, product.getStoreName());
            pstmt.setString(2, product.getItemName());
            pstmt.setInt(3, product.getPrice());

            // 2-3. Execute Query
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Data Save Error: " + e.getMessage());
        }
    }

    // 3. All Select DB
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement psmt = conn.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Product(rs.getString("store_name"), rs.getString("item_name"), rs.getInt("price")));
            }
        } catch (SQLException e) {
            System.err.println("Date Select Error: " + e.getMessage());
        }
        return list;
    }

}
