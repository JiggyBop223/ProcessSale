package se.kth.iv1350.POS.Integration;

/**
 * Thrown when there is a database failure.
 */
public class DatabaseFailureException extends Exception {
    /**
     * Creates a new instance with a message specifying which item caused the database failure.
     *
     * @param itemId The ID of the item that caused the database failure.
     */
    public DatabaseFailureException(String itemId) {
        super("Database failure occurred while searching for item with ID " + itemId);
    }
} 