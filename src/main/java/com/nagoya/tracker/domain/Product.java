package com.nagoya.tracker.domain;

public class Product {
    public String storeName;
    public String itemName;
    public int price;

    public Product(String storeName, String itemName, int price) {
        this.storeName = storeName;
        this.itemName = itemName;
        this.price = price;
    }
}
