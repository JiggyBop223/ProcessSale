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
    private List<Item> items;

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
        for (Item item : items) {
            if (item.getItemInfo().getItemId().equals(itemInfo.getItemId())) {
                item.increaseQuantity(1);
                updateTotals();
                return;
            }
        }
        items.add(new Item(itemInfo, 1));
        updateTotals();
    }

    /**
     * Updates the running total and total VAT for the sale.
     * Called after each change in items.
     */
    private void updateTotals() {
        this.runningTotal = 0;
        this.totalVAT = 0;
        for (Item item : items) {
            this.runningTotal += item.getTotalPriceWithVAT().doubleValue();
            this.totalVAT += item.getTotalVAT().doubleValue();
        }
    }

    /**
     * Gets all items in the sale.
     *
     * @return The items in the sale.
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Gets the running total.
     *
     * @return The running total.
     */
    public double getRunningTotal() {
        return runningTotal;
    }

    /**
     * Gets the total VAT.
     *
     * @return The total VAT.
     */
    public double getTotalVAT() {
        return totalVAT;
    }
}
