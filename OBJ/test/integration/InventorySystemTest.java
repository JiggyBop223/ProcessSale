package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Integration.DatabaseFailureException;
import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Integration.ItemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the InventorySystem class, focusing on exception handling.
 */
public class InventorySystemTest {
    private InventorySystem inventorySystem;
    private static final String NON_EXISTENT_ITEM_ID = "nonExistentItem";
    private static final String DATABASE_FAILURE_ITEM_ID = "999999";

    @BeforeEach
    void setUp() {
        inventorySystem = InventorySystem.getInstance();
    }

    @Test
    void testFindItemWithNonExistentItemId() {
        try {
            inventorySystem.findItem(NON_EXISTENT_ITEM_ID);
            fail("Expected ItemNotFoundException was not thrown");
        } catch (ItemNotFoundException e) {
            assertTrue(e.getMessage().contains(NON_EXISTENT_ITEM_ID), 
                "Exception message should contain the item ID");
        } catch (DatabaseFailureException e) {
            fail("Unexpected DatabaseFailureException was thrown");
        }
    }

    @Test
    void testFindItemWithDatabaseFailureItemId() {
        try {
            inventorySystem.findItem(DATABASE_FAILURE_ITEM_ID);
            fail("Expected DatabaseFailureException was not thrown");
        } catch (DatabaseFailureException e) {
            assertTrue(e.getMessage().contains(DATABASE_FAILURE_ITEM_ID), 
                "Exception message should contain the item ID");
        } catch (ItemNotFoundException e) {
            fail("Unexpected ItemNotFoundException was thrown");
        }
    }

    @Test
    void testFindItemWithValidItemId() {
        try {
            ItemInformation item = inventorySystem.findItem("abc123");
            assertNotNull(item, "Should return item information for valid item ID");
            assertEquals("abc123", item.getItemId(), "Item ID should match");
        } catch (ItemNotFoundException | DatabaseFailureException e) {
            fail("No exception should be thrown for valid item ID");
        }
    }
}
