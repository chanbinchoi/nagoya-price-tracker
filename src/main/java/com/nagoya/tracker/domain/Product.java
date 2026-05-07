package com.nagoya.tracker.domain;

public class Product {
    final private String storeName;
    final private String itemName;
    final private int price;

    public String getStoreName() {
        return storeName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public Product(String storeName, String itemName, int price) {
        this.storeName = storeName;
        this.itemName = itemName;
        this.price = price;
    }
}
