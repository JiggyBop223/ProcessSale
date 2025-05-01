package se.kth.iv1350.POS.Model;

import java.math.BigDecimal;
import se.kth.iv1350.POS.Integration.ItemInformation;

/**
 * Represents an item in the sale.
 */
public class Item {
    /**
     * The information about the item from the inventory system.
     */
    private final ItemInformation itemInfo;

    /**
     * The quantity of this item in the sale.
     */
    private int quantity;

    /**
     * Creates a new item.
     *
     * @param itemInfo The information about the item.
     * @param quantity The quantity of the item.
     */
    public Item(ItemInformation itemInfo, int quantity) {
        if (itemInfo == null) {
            throw new IllegalArgumentException("Item information cannot be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.itemInfo = itemInfo;
        this.quantity = quantity;
    }

    /**
     * Gets the item information.
     *
     * @return The item information.
     */
    public ItemInformation getItemInfo() {
        return itemInfo;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Increases the quantity of the item.
     *
     * @param quantity The quantity to increase by.
     */
    public void increaseQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity += quantity;
    }

    /**
     * Gets the total price of the item.
     *
     * @return The total price of the item.
     */
    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(itemInfo.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Gets the total VAT of the item.
     *
     * @return The total VAT of the item.
     */
    public BigDecimal getTotalVAT() {
        return getTotalPrice().multiply(BigDecimal.valueOf(itemInfo.getVatRate()));
    }

    /**
     * Gets the total price including VAT.
     *
     * @return The total price including VAT.
     */
    public BigDecimal getTotalPriceWithVAT() {
        return getTotalPrice().add(getTotalVAT());
    }
}
