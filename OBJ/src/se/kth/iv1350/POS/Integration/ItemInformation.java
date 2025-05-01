package se.kth.iv1350.POS.Integration;

/**
 * Represents information about an item.
 */
public class ItemInformation {
    private final String itemId;
    private final String name;
    private final String description;
    private final double price;
    private final double vatRate;

    /**
     * Creates a new item information.
     *
     * @param itemId The ID of the item.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param price The price of the item.
     * @param vatRate The VAT rate of the item.
     */
    public ItemInformation(String itemId, String name, String description, double price, double vatRate) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
    }

    /**
     * Gets the item ID.
     *
     * @return The item ID.
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Gets the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the price.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the VAT rate.
     *
     * @return The VAT rate.
     */
    public double getVatRate() {
        return vatRate;
    }
}
