package com.nagoya.tracker.service;

import com.nagoya.tracker.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LowestPriceAnalyzerTest {

    private final PriceAnalyzer analyzer = new LowestPriceAnalyzer();

    @Test
    @DisplayName("Verification of lowest price product search function")
    void analyze_LowestPrice() {
        // Given: Data preparation
        List<Product> productList = List.of(
                new Product("V-drug", "banana", 100),
                new Product("Yamanaka", "banana", 200),
                new Product("Amica", "apple", 300)
        );

        // When: Execution
        Optional<Product> result = analyzer.analyze(productList);

        // Then: Assertion
        // Check if result is not empty
        assertTrue(result.isPresent());

        // Check if min price is 100
        assertEquals(100, result.get().getPrice());

        // Check if min storeName is "V-drug"
        assertEquals("V-drug", result.get().getStoreName());


    }

    @Test
    @DisplayName("Validation of exception handling when inputting an empty list")
    void alayze_EmptyList() {
        // Given: Data preparation
        List<Product> productList = new ArrayList<>();

        // When: Execution
        Optional<Product> result = analyzer.analyze(productList);

        // Then: Assertion
        // Check if optional is empty
        assertTrue(result.isEmpty());

    }
}