package se.kth.iv1350.POS.Integration;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the inventory system.
 */
public class InventorySystem {
    private static final InventorySystem instance = new InventorySystem();
    private final Map<String, ItemInformation> inventory;
    private static final String DATABASE_FAILURE_ITEM_ID = "999999";

    /**
     * Private constructor to prevent instantiation from other classes.
     * Initializes the inventory with some items.
     */
    private InventorySystem() {
        this.inventory = new HashMap<>();
        inventory.put("abc123", new ItemInformation("abc123", "BigWheel Oatmeal", "BigWheel Oatmeal 500ml, whole grain oats, high fiber, gluten free", 29.90, 0.06));
        inventory.put("def456", new ItemInformation("def456", "YouGoGo Blueberry", " YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour", 14.90, 0.06));
    }

    /**
     * Gets the singleton instance of InventorySystem.
     * @return The singleton instance.
     */
    public static InventorySystem getInstance() {
        return instance;
    }

    /**
     * Finds an item in the inventory.
     *
     * @param itemId The ID of the item to find.
     * @return The item information if found.
     * @throws ItemNotFoundException if the item is not found in the inventory.
     * @throws DatabaseFailureException if the database cannot be accessed.
     */
    public ItemInformation findItem(String itemId) throws ItemNotFoundException, DatabaseFailureException {
        if (itemId.equals(DATABASE_FAILURE_ITEM_ID)) {
            throw new DatabaseFailureException(itemId);
        }

        ItemInformation item = inventory.get(itemId);
        if (item == null) {
            throw new ItemNotFoundException(itemId);
        }
        return item;
    }
}
