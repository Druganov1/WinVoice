package com.winvoice;
import java.util.Date;

public class Payment {
    private String paymentType;
    private String paymentStatus;
    private Date paymentDate;

    public Payment(String paymentType, String paymentStatus, Date paymentDate) {
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    public void processPayment(String paymentMethod) {
        if (paymentStatus.equals("Pending")) { // alleen als die pending is, dus niet betaald is
            paymentType = paymentMethod;
            paymentStatus = "Completed";
            paymentDate = new Date(); // we zetten de datum neer wanneer die is gemaakt
            System.out.println("Payment has been successfully processed.");
        } else {
            System.out.println("Payment cannot be processed. Current status: " + paymentStatus);
        }
    }
}