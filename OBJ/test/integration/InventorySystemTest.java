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
        assertEquals("BigWheel Oatmeal", item.getName(), "The name of the item should match");
    }

    @Test
    void testFindNonExistingItem() {
        String itemId = "invalidID";
        ItemInformation item = inventory.findItem(itemId);

        assertNull(item, "Item should not be found in the inventory");
    }
}
