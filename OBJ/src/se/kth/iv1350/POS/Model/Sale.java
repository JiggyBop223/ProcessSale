package se.kth.iv1350.POS.Model;

import se.kth.iv1350.POS.Integration.ItemInformation;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale in the POS system. Handles all sale-related operations
 * including item registration and total calculations.
 */
public class Sale {
    /**
     * List of items in the current sale.
     */
    private final List<SaleItem> items;

    /**
     * The running total of the sale including VAT.
     */
    private double runningTotal;

    /**
     * The total VAT amount for all items in the sale.
     */
    private double totalVAT;

    /**
     * Creates a new sale instance.
     */
    public Sale() {
        this.items = new ArrayList<>();
        this.runningTotal = 0;
        this.totalVAT = 0;
    }

    /**
     * Registers an item to the sale. If the item already exists,
     * its quantity is increased by one.
     *
     * @param itemInfo The information about the item to register.
     */
    public void registerItem(ItemInformation itemInfo) {
        for (SaleItem item : items) {
            if (item instanceof Item && ((Item)item).getItemInfo().getItemId().equals(itemInfo.getItemId())) {
                ((Item)item).increaseQuantity(1);
                updateTotals();
                return;
            }
        }
        items.add(new Item(itemInfo, 1));
        updateTotals();
    }

    /**
     * Adds a bundle to the sale.
     *
     * @param bundle The bundle to add.
     */
    public void addBundle(ItemBundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle cannot be null");
        }
        items.add(bundle);
        updateTotals();
    }

    /**
     * Updates the running total and total VAT for the sale.
     * Called after each change in items.
     */
    private void updateTotals() {
        this.runningTotal = 0;
        this.totalVAT = 0;
        for (SaleItem item : items) {
            this.runningTotal += item.getTotalPriceWithVAT().doubleValue();
            this.totalVAT += item.getTotalVAT().doubleValue();
        }
    }

    /**
     * Gets the list of items in the sale.
     *
     * @return The list of items.
     */
    public List<SaleItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Gets the running total of the sale.
     *
     * @return The running total.
     */
    public double getRunningTotal() {
        return runningTotal;
    }

    /**
     * Gets the total VAT of the sale.
     *
     * @return The total VAT.
     */
    public double getTotalVAT() {
        return totalVAT;
    }
}
