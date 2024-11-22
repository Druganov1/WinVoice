package com.winvoice;

public class Customer {
    private int customerId;
    private String name;
    private Address billingAddress;
    private Address deliveryAddress;
    private String phone;
    private String email;

    public Customer(int customerId, String name, Address billingAddress, Address deliveryAddress, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.email = email;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public String getCustomerInfo() {
        String customerInfo = "Customer Info:\n";
        customerInfo += "Name: " + name + "\n";
        customerInfo += "Phone: " + phone + "\n";
        customerInfo += "Email: " + email + "\n";
        customerInfo += "Billing Address: " + billingAddress.getFullAddress() + "\n";  // Use getFullAddress
        customerInfo += "Delivery Address: " + deliveryAddress.getFullAddress();      // Use getFullAddress
        return customerInfo;
    }

    public String getName() {
        return name;
    }



}