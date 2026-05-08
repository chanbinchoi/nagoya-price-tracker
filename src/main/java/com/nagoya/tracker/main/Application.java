package com.nagoya.tracker.main;

import com.nagoya.tracker.domain.Product;
import com.nagoya.tracker.service.LowestPriceAnalyzer;
import com.nagoya.tracker.service.PriceAnalyzer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:nagoyadb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String password = "";

        List<Product> productList = new ArrayList<>();

        // 1. Connect DB
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // 2. Create Table
            stmt.execute("CREATE TABLE PRODUCT (store_name VARCHAR(255), item_name VARCHAR(255), price INT)");

            // 3. Insert Data
            stmt.execute("INSERT INTO PRODUCT VALUES ('V-drug', 'apple', 200)");

            // 4. Select Data
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

            // 5. Passing ResultSet, Add List
            while (rs.next()) {
                String storeName = rs.getString("store_name");
                String itemName = rs.getString("item_name");
                int price = rs.getInt("price");

                productList.add(new Product(storeName, itemName, price));
            }
        } catch (SQLException e) {
            System.out.println("Datebase Connect Or Query Error: " + e.getMessage());
        }

        // 6. Service Logic, Print
        PriceAnalyzer analyzer = new LowestPriceAnalyzer();
        Optional<Product> lowestPriceProduct = analyzer.analyze(productList);

        lowestPriceProduct.ifPresentOrElse(
                p -> System.out.printf("最安値の商品情報を確認いたしました。店舗名: %s, 価格: %d円%n",
                        p.getStoreName(), p.getPrice()),
                () -> System.out.println("誠に恐れ入りますが、リストが空のため、最安値を算出できませんでした。")
        );
    }
}
