package se.kth.iv1350.POS.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bundle of items that are sold together.
 * This is a composite class that can contain both individual items and other bundles.
 */
public class ItemBundle extends SaleItem {
    private final List<SaleItem> items;
    private final String bundleName;
    private final int quantity;

    /**
     * Creates a new item bundle.
     *
     * @param bundleName The name of the bundle.
     * @param quantity The quantity of the bundle.
     */
    public ItemBundle(String bundleName, int quantity) {
        if (bundleName == null || bundleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Bundle name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.items = new ArrayList<>();
        this.bundleName = bundleName;
        this.quantity = quantity;
    }

    /**
     * Adds an item to the bundle.
     *
     * @param item The item to add.
     */
    public void addItem(SaleItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
    }

    /**
     * Gets the name of the bundle.
     *
     * @return The name of the bundle.
     */
    @Override
    public String getName() {
        return bundleName;
    }

    /**
     * Gets the quantity of the bundle.
     *
     * @return The quantity of the bundle.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the total price of the bundle.
     *
     * @return The total price of the bundle.
     */
    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (SaleItem item : items) {
            total = total.add(item.getTotalPrice());
        }
        return total.multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Gets the total VAT of the bundle.
     *
     * @return The total VAT of the bundle.
     */
    @Override
    public BigDecimal getTotalVAT() {
        BigDecimal total = BigDecimal.ZERO;
        for (SaleItem item : items) {
            total = total.add(item.getTotalVAT());
        }
        return total.multiply(BigDecimal.valueOf(quantity));
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

    /**
     * Gets all items in the bundle.
     *
     * @return The items in the bundle.
     */
    public List<SaleItem> getItems() {
        return new ArrayList<>(items);
    }
} 