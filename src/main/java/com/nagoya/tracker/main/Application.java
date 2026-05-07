package com.nagoya.tracker.main;

import com.nagoya.tracker.domain.Product;
import com.nagoya.tracker.service.LowestPriceAnalyzer;
import com.nagoya.tracker.service.PriceAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        // 1. Create productList
        List<Product> productList = new ArrayList<>();

        // 2. Read File
        try (BufferedReader br = new BufferedReader(new FileReader("prices.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                // 3. add List
                if (values.length == 3) {
                    productList.add(new Product(values[0], values[1], Integer.parseInt(values[2])));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 4. ProductService
        PriceAnalyzer analyzer = new LowestPriceAnalyzer();
        Optional<Product> lowestPriceProduct = analyzer.analyze(productList);

        // 5. Print
        lowestPriceProduct.ifPresentOrElse(
                p -> System.out.printf("最安値の商品情報を確認いたしました。店舗名: %s, 価格: %d円%n",
                        p.getStoreName(), p.getPrice()),
                () -> System.out.println("誠に恐れ入りますが、リストが空のため、最安値を算出できませんでした。")
        );
    }
}
