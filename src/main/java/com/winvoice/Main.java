package com.winvoice;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.*;
import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create a customer
        Address billingAddress = new Address("Poststraat 141", "Delfzijl ", "9934 BE", "Nederland");
        Address deliveryAddress = new Address("Poststraat 141", "Delfzijl ", "9934 BE", "Nederland");
        Customer customer = new Customer(1, "IKEA", billingAddress, deliveryAddress, "123456789", "billing@ikea.com");

        // Create a tax object
        Tax tax = new Tax(21, 1);

        // Create a payment object
        Payment payment = new Payment("Credit Card", "Pending", new Date());


        String invoiceNum = generateRandomInvoiceNumber();
        // Create an invoice
        Invoice invoice = new Invoice(invoiceNum, new Date(), customer, tax, payment);

        // Add items to the invoice
        Item item1 = new Item(1, "Laptop", 1, 1200.00);
        Item item2 = new Item(2, "Mouse", 2, 25.00);
        invoice.addItem(item1);
        invoice.addItem(item2);

        // Generate and calculate invoice
        invoice.calculateTotal();
        invoice.generateInvoice();

        String htmlContent = processHtmlTemplate(invoice, customer);

        generatePdf(htmlContent, invoiceNum);
    }

    public static String generateRandomInvoiceNumber() {
        Random random = new Random();

        // Generate a random 6-digit number
        int randomNumber = random.nextInt(1000000); // Generates a number between 0 and 999999

        // Format it to always have 6 digits
        String formattedNumber = String.format("%06d", randomNumber); // Pads with leading zeros if necessary

        // Return the final invoice number
        return "WVN-" + formattedNumber;
    }

    public static String processHtmlTemplate(Invoice invoice, Customer customer) throws IOException {
        // Read the HTML template from the file
        String htmlContent = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Main.class.getClassLoader().getResourceAsStream("templates/invoice_template.html")))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            htmlContent = sb.toString();
        } catch (Exception e){
            System.out.println("An error occured "+ e.getMessage());
        }

        double total = invoice.calculateTotal();

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        // Replace placeholders with actual values
        htmlContent = htmlContent.replace("{{invoiceNumber}}", String.valueOf(invoice.getInvoiceNumber()));
        htmlContent = htmlContent.replace("{{total}}", String.format("%.2f", total));
        htmlContent = htmlContent.replace("{{taxAmount}}", String.format("%.2f", invoice.getTax().calculateTax(total)));
        htmlContent = htmlContent.replace("{{subTotal}}", String.format("%.2f", invoice.calculateSubTotal()));
        htmlContent = htmlContent.replace("{{billingAddress}}", customer.getBillingAddress().getFullAddress());
        htmlContent = htmlContent.replace("{{customerName}}", customer.getName());
        htmlContent = htmlContent.replace("{{invoiceDate}}", formattedDate);
        htmlContent = htmlContent.replace("{{taxPercentage}}", String.valueOf(invoice.getTax().getTaxRate()));


        return htmlContent;
    }

    public static void generatePdf(String htmlContent, String invoiceNumber) throws DocumentException, IOException {
        // Create the PDF renderer
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        // Output the PDF to a file
        try (FileOutputStream outputStream = new FileOutputStream("invoice-"+invoiceNumber+".pdf")) {
            renderer.createPDF(outputStream);
        }

        System.out.println("PDF generated successfully!");
    }

}