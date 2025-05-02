package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Model.Item;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private ItemInformation itemInfo;
    private Item item;

    @BeforeEach
    void setUp() {
        itemInfo = new ItemInformation("abc123", "Oatmeal", "Tasty oatmeal", 10.0, 0.12);
        item = new Item(itemInfo, 2);
    }

    @Test
    void getItemInfo() {
        assertEquals(itemInfo, item.getItemInfo(), "Item info should match");
    }

    @Test
    void getQuantity() {
        assertEquals(2, item.getQuantity(), "Initial quantity should be 2");
    }

    @Test
    void increaseQuantity() {
        item.increaseQuantity(3);
        assertEquals(5, item.getQuantity(), "Quantity should increase to 5");
    }

    @Test
    void getTotalPrice() {
        BigDecimal expected = BigDecimal.valueOf(20.0); // 10.0 * 2
        assertEquals(0, expected.compareTo(item.getTotalPrice()), "Total price should be 20.0");
    }

    @Test
    void getTotalVAT() {
        BigDecimal expectedVAT = BigDecimal.valueOf(2.14); // (20 * 0.12) / 1.12 = 2.14
        assertEquals(0, expectedVAT.compareTo(item.getTotalVAT()), "VAT should be 2.14");
    }

    @Test
    void getTotalPriceWithVAT() {
        BigDecimal expected = BigDecimal.valueOf(20.0); // Same as getTotalPrice
        assertEquals(0, expected.compareTo(item.getTotalPriceWithVAT()), "Total price with VAT should be 20.0");
    }

    @Test
    void constructorThrowsExceptionOnNullItemInfo() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, 1), "Should throw on null itemInfo");
    }

    @Test
    void constructorThrowsExceptionOnNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new Item(itemInfo, -1), "Should throw on negative quantity");
    }

    @Test
    void increaseQuantityThrowsExceptionOnNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> item.increaseQuantity(-1), "Should throw on negative increase");
    }
}
