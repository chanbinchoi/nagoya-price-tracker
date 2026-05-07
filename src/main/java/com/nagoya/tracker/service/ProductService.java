package com.nagoya.tracker.service;

import com.nagoya.tracker.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductService {

    // min price in List
    public Optional<Product> getLowestPriceProduct(List<Product> products) {
        return products.stream()
                .min(Comparator.comparingInt(Product::getPrice));
    }
}
