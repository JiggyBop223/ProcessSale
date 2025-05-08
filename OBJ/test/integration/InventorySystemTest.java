package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.ItemInformation;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemTest {
    private InventorySystem inventory;

    @BeforeEach
    void setUp() {
        inventory = new InventorySystem();
    }

    @Test
    void testFindExistingItem() {
        String itemId = "abc123";
        ItemInformation item = inventory.findItem(itemId);

        assertNotNull(item, "Item should be found in the inventory");
        assertEquals("abc123", item.getItemId(), "Item ID should match");
        assertEquals("BigWheel Oatmeal", item.getName(), "Item name should match");
        assertEquals("BigWheel Oatmeal 500ml, whole grain oats, high fiber, gluten free", item.getDescription(), "Item description should match");
        assertEquals(29.90, item.getPrice(), 0.001, "Item price should match");
        assertEquals(0.06, item.getVatRate(), 0.001, "Item VAT rate should match");
    }

    @Test
    void testFindNonExistingItem() {
        String itemId = "invalidID";
        ItemInformation item = inventory.findItem(itemId);

        assertNull(item, "Item should not be found in the inventory");
    }
}
