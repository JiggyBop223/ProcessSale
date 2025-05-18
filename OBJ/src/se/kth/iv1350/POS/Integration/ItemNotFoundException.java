package se.kth.iv1350.POS.Integration;

/**
 * Thrown when an item cannot be found in the inventory system.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance with a message specifying which item could not be found.
     *
     * @param itemId The ID of the item that could not be found.
     */
    public ItemNotFoundException(String itemId) {
        super("Item with ID " + itemId + " could not be found in the inventory system.");
    }
} 