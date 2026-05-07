package com.nagoya.tracker.service;

import com.nagoya.tracker.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

// Cmd + Shift + T : Create Test
public class LowestPriceAnalyzer implements PriceAnalyzer {
    @Override
    public Optional<Product> analyze(List<Product> products) {
        return products.stream()
                .min(Comparator.comparingInt(Product::getPrice));
    }
}
