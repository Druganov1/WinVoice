package com.winvoice;

public class Item {
    private int itemId;
    private String description;
    private int quantity;
    private double unitPrice;

    public Item(int itemId, String description, int quantity, double unitPrice) {
        this.itemId = itemId;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }
}
