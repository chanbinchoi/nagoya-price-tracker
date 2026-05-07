package com.nagoya.tracker.service;

import com.nagoya.tracker.domain.Product;

import java.util.List;
import java.util.Optional;

public interface PriceAnalyzer {
    public Optional<Product> analyze(List<Product> products);
}
