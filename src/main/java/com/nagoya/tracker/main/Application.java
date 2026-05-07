package com.nagoya.tracker.main;

import com.nagoya.tracker.domain.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

        // 4. Create productMap
        Map<String, Product> productMap = new HashMap<>();

        // 5. put productMap
        for (Product p : productList) {
            String compositeKey = p.getStoreName() + "_" + p.getItemName();
            productMap.put(compositeKey, p);
        }

        // 6. Search for Optional
        Optional<Product> resultOpt = Optional.ofNullable(productMap.get("Amica_banana"));

        // 7. Print
        resultOpt.ifPresentOrElse(
                p -> System.out.println("store: " + p.getStoreName() + ", price: " + p.getPrice()),
                () -> System.out.println("Data is nothing")
        );

    }
}
