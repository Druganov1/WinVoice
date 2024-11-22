package com.winvoice;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private String invoiceNumber;
    private Date invoiceDate;
    private Customer customer;
    private ArrayList<Item> items;
    private Tax tax;
    private Payment payment;

    public Invoice(String invoiceNumber, Date invoiceDate, Customer customer, Tax tax, Payment payment) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.tax = tax;
        this.payment = payment;
    }

    public double calculateTotal() {
        double total = 0.0;

        for (Item item : items) {
            total += item.getTotalPrice(); // Calculate item subtotal
        }

        double taxAmount = tax.calculateTax(total); // Calculate tax
        total += taxAmount; // Add tax to the total

        return total;
    }

    public double calculateSubTotal(){
        double total = 0.0;

        for (Item item : items) {
            total += item.getTotalPrice(); // Calculate item subtotal
        }


        return total;
    }

    public Tax getTax() {
        return this.tax;
    }
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void generateInvoice() {
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Invoice Date: " + invoiceDate);
        System.out.println("Customer Details:");
        System.out.println(customer.getCustomerInfo());

        double total = calculateTotal();
        System.out.println("Tax: " + tax.calculateTax(total));
        System.out.println("Total (including tax): " + total);

    }

    public String getInvoiceNumber(){
        return invoiceNumber;
    }

}