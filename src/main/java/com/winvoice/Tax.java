package com.winvoice;

public class Tax {
    private int taxRate;
    private int taxRegion;

    public Tax(int taxRate, int taxRegion) {
        this.taxRate = taxRate;
        this.taxRegion = taxRegion;
    }

    public double calculateTax(double amount) {
        return amount * taxRate / 100;
    }

    public int getTaxRate(){
        return taxRate;
    }
}
