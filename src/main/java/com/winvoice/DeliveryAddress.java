package com.winvoice;

public class DeliveryAddress extends Address {
    private String deliveryInstructions;

    public DeliveryAddress(String street, String city, String zipCode, String country, String deliveryInstructions) {
        super(street, city, zipCode, country);
        this.deliveryInstructions = deliveryInstructions;
    }
}