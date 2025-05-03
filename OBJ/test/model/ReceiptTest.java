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


        double actualChange = receipt.getChange();
        double expectedChange = 10.0;


        assertEquals(expectedChange, actualChange, 0.0001, "Change should be amount paid minus total cost");
    }


    @Test
    void generateReceiptText_shouldContainBasicInfo() {
        Sale sale = new Sale();
        ItemInformation itemInfo = new ItemInformation("def456", "YouGoGo Blueberry", "Yogurt", 15.0, 0.1);
        sale.registerItem(itemInfo);
        double amountPaid = 20.0;
        Receipt receipt = new Receipt(sale, amountPaid);

        String receiptText = receipt.generateReceiptText();


        assertTrue(receiptText.contains("YouGoGo Blueberry"), "Should contain item name");
        assertTrue(receiptText.contains("Cash:"), "Should mention cash");
        assertTrue(receiptText.contains("Change:"), "Should mention change");
    }



}
