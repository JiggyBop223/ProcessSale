package se.kth.iv1350.POS.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import se.kth.iv1350.POS.Integration.ItemInformation;

/**
 * Represents an item in the sale.
 */
public class Item extends SaleItem {
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
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return itemInfo.getName();
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
    @Override
    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(itemInfo.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Gets the total VAT of the item.
     *
     * @return The total VAT of the item.
     */
    @Override
    public BigDecimal getTotalVAT() {
        BigDecimal vatRate = BigDecimal.valueOf(itemInfo.getVatRate());
        BigDecimal divisor = BigDecimal.ONE.add(vatRate);
        return getTotalPrice().multiply(vatRate).divide(divisor, 2, RoundingMode.HALF_UP);
    }

    /**
     * Gets the total price including VAT.
     *
     * @return The total price including VAT.
     */
    @Override
    public BigDecimal getTotalPriceWithVAT() {
        return getTotalPrice();
    }
}
