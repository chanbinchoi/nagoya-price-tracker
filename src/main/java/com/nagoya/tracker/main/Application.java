package com.nagoya.tracker.main;

import com.nagoya.tracker.domain.Product;
import com.nagoya.tracker.repository.ProductRepository;
import com.nagoya.tracker.service.LowestPriceAnalyzer;
import com.nagoya.tracker.service.PriceAnalyzer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) throws SQLException {

        ProductRepository productRepository = new ProductRepository();

        // 1. Create Table
        productRepository.initDb();

        // 2. Insert Data
        productRepository.save(new Product("V-drug", "apple", 200));
        productRepository.save(new Product("V-drug", "banana", 150));
        productRepository.save(new Product("Yamanaka", "banana", 180));
        productRepository.save(new Product("Amica", "banana", 130));

        // 3. Select Data
        List<Product> productList = productRepository.findAll();

        // 4. Service Logic, Print
        PriceAnalyzer analyzer = new LowestPriceAnalyzer();
        Optional<Product> lowestPriceProduct = analyzer.analyze(productList);

        lowestPriceProduct.ifPresentOrElse(
                p -> System.out.printf("最安値の商品情報を確認いたしました。店舗名: %s, 価格: %d円%n",
                        p.getStoreName(), p.getPrice()),
                () -> System.out.println("誠に恐れ入りますが、リストが空のため、最安値を算出できませんでした。")
        );
    }
}
