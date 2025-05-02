package model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Model.*;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @Test
    void getChange_shouldReturnCorrectChange() {
        // Arrange
        Sale sale = new Sale();
        ItemInformation itemInfo = new ItemInformation("abc123", "BigWheel Oatmeal", "Oats", 10.0, 0.1);
        sale.registerItem(itemInfo); // Adds one item costing 10 + 1 VAT = 11
        double amountPaid = 20.0;
        Receipt receipt = new Receipt(sale, amountPaid);

        // Act
        double actualChange = receipt.getChange();
        double expectedChange = 9.0;

        // Assert
        assertEquals(expectedChange, actualChange, 0.0001, "Change should be amount paid minus total cost");
    }

    @Test
    void generateReceiptText_shouldContainItemAndAmounts() {
        // Arrange
        Sale sale = new Sale();
        ItemInformation itemInfo = new ItemInformation("def456", "YouGoGo Blueberry", "Yogurt", 15.0, 0.1);
        sale.registerItem(itemInfo); // Total = 15 + 1.5 = 16.5
        double amountPaid = 20.0;
        Receipt receipt = new Receipt(sale, amountPaid);

        // Act
        String receiptText = receipt.generateReceiptText();

        // Assert
        assertTrue(receiptText.contains("YouGoGo Blueberry"), "Receipt should include item name");
        assertTrue(receiptText.contains("20.00 SEK"), "Receipt should include cash amount");
        assertTrue(receiptText.contains("Change: 3.50 SEK"), "Receipt should show correct change");
    }
}
