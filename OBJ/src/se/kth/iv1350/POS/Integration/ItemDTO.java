package se.kth.iv1350.POS.Integration;

/**
 * Data Transfer Object for item information.
 * Used to transfer item data between layers without exposing the model.
 */
public class ItemDTO {
    private final String itemId;
    private final String name;
    private final double price;
    private final double vatRate;
    private final String description;
    private final int quantity;

    /**
     * Creates a new ItemDTO.
     * @param itemId The item identifier
     * @param name The item name
     * @param price The item price
     * @param vatRate The VAT rate
     * @param description The item description
     * @param quantity The item quantity
     */
    public ItemDTO(String itemId, String name, double price, double vatRate, String description, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * Gets the item identifier.
     *
     * @return The item identifier
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Gets the item name.
     *
     * @return The item name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the item price.
     *
     * @return The item price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the VAT rate.
     *
     * @return The VAT rate
     */
    public double getVatRate() {
        return vatRate;
    }

    /**
     * Gets the item description.
     *
     * @return The item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the item quantity.
     *
     * @return The item quantity
     */
    public int getQuantity() {
        return quantity;
    }
}