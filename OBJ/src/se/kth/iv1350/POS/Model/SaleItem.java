package se.kth.iv1350.POS.Model;

import java.math.BigDecimal;

/**
 * Abstract class representing an item that can be sold.
 * This is the base class for both individual items and item bundles.
 */
public abstract class SaleItem {
    /**
     * Gets the total price of the item.
     *
     * @return The total price.
     */
    public abstract BigDecimal getTotalPrice();

    /**
     * Gets the total VAT of the item.
     *
     * @return The total VAT.
     */
    public abstract BigDecimal getTotalVAT();

    /**
     * Gets the total price including VAT.
     *
     * @return The total price including VAT.
     */
    public abstract BigDecimal getTotalPriceWithVAT();

    /**
     * Gets the name of the item.
     *
     * @return The name.
     */
    public abstract String getName();

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity.
     */
    public abstract int getQuantity();
} 