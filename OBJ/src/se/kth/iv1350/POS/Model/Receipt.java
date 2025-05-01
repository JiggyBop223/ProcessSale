package se.kth.iv1350.POS.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a sale receipt.
 */
public class Receipt {
    private final Sale sale;
    private final double amountPaid;
    private final LocalDateTime saleTime;

    /**
     * Creates a new receipt.
     *
     * @param sale The completed sale.
     * @param amountPaid The amount paid by customer.
     */
    public Receipt(Sale sale, double amountPaid) {
        this.sale = sale;
        this.amountPaid = amountPaid;
        this.saleTime = LocalDateTime.now();
    }

    /**
     * Builds the receipt as a string.
     */
    public String generateReceiptText() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=== BEGIN RECEIPT ===\n");
        receipt.append("Date: ").append(saleTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");
        
        for (Item item : sale.getItems()) {
            receipt.append(String.format("%s %d x %.2f %.2f SEK\n",
                item.getItemInfo().getName(),
                item.getQuantity(),
                item.getItemInfo().getPrice(),
                item.getTotalPriceWithVAT().doubleValue()));
        }

        receipt.append(String.format("\nTotal: %.2f SEK\n", sale.getRunningTotal()));
        receipt.append(String.format("VAT: %.2f SEK\n", sale.getTotalVAT()));
        receipt.append(String.format("Cash: %.2f SEK\n", amountPaid));
        receipt.append(String.format("Change: %.2f SEK\n", getChange()));
        receipt.append("=== End of Receipt ===\n");
        return receipt.toString();
    }

    /**
     * Calculates the change to be given to the customer.
     *
     * @return The change to be given to the customer.
     */
    public double getChange() {
        return amountPaid - sale.getRunningTotal();
    }
}
