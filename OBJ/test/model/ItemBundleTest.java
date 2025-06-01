package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Model.Item;
import se.kth.iv1350.POS.Model.ItemBundle;
import se.kth.iv1350.POS.Model.Sale;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemBundleTest {
    private ItemBundle bundle;
    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        bundle = new ItemBundle("Test Bundle", 1);
        ItemInformation info1 = new ItemInformation("abc123", "Item 1", "Description 1", 10.0, 0.1);
        ItemInformation info2 = new ItemInformation("def456", "Item 2", "Description 2", 20.0, 0.1);
        item1 = new Item(info1, 1);
        item2 = new Item(info2, 1);
    }

    @Test
    void testAddItem() {
        bundle.addItem(item1);
        assertEquals(1, bundle.getItems().size(), "Bundle should contain one item");
        assertEquals(item1, bundle.getItems().get(0), "Added item should be in bundle");
    }

    @Test
    void testGetTotalPrice() {
        bundle.addItem(item1);
        bundle.addItem(item2);
        BigDecimal expected = BigDecimal.valueOf(30.0); // 10.0 + 20.0
        assertEquals(0, expected.compareTo(bundle.getTotalPrice()), 
            "Total price should be sum of item prices");
    }

    @Test
    void testGetTotalVAT() {
        bundle.addItem(item1);
        bundle.addItem(item2);
        BigDecimal expectedVAT = BigDecimal.valueOf(2.73); // (30 * 0.1) / 1.1
        assertEquals(0, expectedVAT.compareTo(bundle.getTotalVAT()), 
            "Total VAT should be correct");
    }

    @Test
    void testGetName() {
        assertEquals("Test Bundle", bundle.getName(), "Bundle name should match");
    }

    @Test
    void testGetQuantity() {
        ItemBundle bundleWithQuantity = new ItemBundle("Test", 2);
        assertEquals(2, bundleWithQuantity.getQuantity(), "Quantity should match");
    }

    @Test
    void testConstructorThrowsExceptionOnNullName() {
        assertThrows(IllegalArgumentException.class, 
            () -> new ItemBundle(null, 1), 
            "Should throw on null bundle name");
    }

    @Test
    void testConstructorThrowsExceptionOnEmptyName() {
        assertThrows(IllegalArgumentException.class, 
            () -> new ItemBundle("", 1), 
            "Should throw on empty bundle name");
    }

    @Test
    void testConstructorThrowsExceptionOnNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, 
            () -> new ItemBundle("Test", -1), 
            "Should throw on negative quantity");
    }

    @Test
    void testAddItemThrowsExceptionOnNullItem() {
        assertThrows(IllegalArgumentException.class, 
            () -> bundle.addItem(null), 
            "Should throw on null item");
    }

    @Test
    void testBundleInSale() {
        Sale sale = new Sale();
        bundle.addItem(item1);
        bundle.addItem(item2);
        sale.addBundle(bundle);
        
        assertEquals(1, sale.getItems().size(), "Sale should contain one bundle");
        assertTrue(sale.getItems().get(0) instanceof ItemBundle, "Item should be a bundle");
        assertEquals(30.0, sale.getRunningTotal(), 0.001, "Sale total should include bundle price");
    }
} 