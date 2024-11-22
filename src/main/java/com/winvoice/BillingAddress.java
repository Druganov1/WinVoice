package com.winvoice;

public class BillingAddress extends Address {
    private String billingContact;

    public BillingAddress(String street, String city, String zipCode, String country, String billingContact) {
        super(street, city, zipCode, country);
        this.billingContact = billingContact;
    }

    public String getFullBillingAddress(){
        return super.getFullAddress();
    }
}