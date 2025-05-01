package se.kth.iv1350.POS.Integration;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the inventory system.
 */
public class InventorySystem {
    private final Map<String, ItemInformation> inventory;

    /**
     * Creates a new inventory system.
     */
    public InventorySystem() {
        this.inventory = new HashMap<>();
        inventory.put("abc123", new ItemInformation("abc123", "Milk", "Fresh milk 1L", 10.90, 0.12));
        inventory.put("def456", new ItemInformation("def456", "Bread", "Wheat bread 500g", 25.90, 0.12));
    }

    /**
     * Finds an item in the inventory.
     *
     * @param itemId The ID of the item to find.
     * @return The item information if found, null if not found.
     */
    public ItemInformation findItem(String itemId) {
        return inventory.get(itemId);
    }
}
