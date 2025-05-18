package se.kth.iv1350.POS.Controller;

import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Integration.Printer;
import se.kth.iv1350.POS.Integration.ItemNotFoundException;
import se.kth.iv1350.POS.Integration.DatabaseFailureException;
import se.kth.iv1350.POS.Integration.TotalRevenueObserver;
import se.kth.iv1350.POS.Model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the controller of the application.
 */
public class Controller {
    private final InventorySystem inventorySystem;
    private final Printer printer;
    private final CashRegister cashRegister;
    private Sale currentSale;
    private double totalRevenue;
    private final List<TotalRevenueObserver> revenueObservers;

    /**
     * Creates a new controller.
     *
     * @param inventorySystem The inventory system to use.
     * @param printer The printer to use.
     */
    public Controller(InventorySystem inventorySystem, Printer printer) {
        this.inventorySystem = inventorySystem;
        this.printer = printer;
        this.cashRegister = new CashRegister();
        this.totalRevenue = 0;
        this.revenueObservers = new ArrayList<>();
    }

    /**
     * Adds an observer that will be notified when the total revenue changes.
     *
     * @param observer The observer to add.
     */
    public void addRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }

    private void notifyObservers() {
        for (TotalRevenueObserver observer : revenueObservers) {
            observer.updateTotalRevenue(totalRevenue);
        }
    }

    /**
     * Starts a new sale.
     */
    public void startSale() {
        this.currentSale = new Sale();
    }

    /**
     * Registers an item to the sale.
     *
     * @param itemId The ID of the item to register.
     * @return The registered item information.
     * @throws ItemNotFoundException if the item is not found in the inventory.
     * @throws DatabaseFailureException if the database cannot be accessed.
     */
    public ItemInformation registerItem(String itemId) throws ItemNotFoundException, DatabaseFailureException {
        ItemInformation itemInfo = this.inventorySystem.findItem(itemId);
            this.currentSale.registerItem(itemInfo);
        return itemInfo;
    }

    /**
     * Gets the last registered item's information.
     *
     * @return The last registered item, or null if no items have been registered.
     */
    public Item getLastRegisteredItem() {
        List<Item> items = this.currentSale.getItems();
        return items.isEmpty() ? null : items.get(items.size() - 1);
    }

    /**
     * Gets the current running total of the sale.
     *
     * @return The running total.
     */
    public double getRunningTotal() {
        return this.currentSale.getRunningTotal();
    }

    /**
     * Gets the total VAT of the current sale.
     *
     * @return The total VAT.
     */
    public double getTotalVAT() {
        return this.currentSale.getTotalVAT();
    }

    /**
     * Ends the current sale.
     *
     * @return The final total of the sale.
     */
    public double endSale() {
        return this.currentSale.getRunningTotal();
    }

    /**
     * Handles payment for the sale.
     *
     * @param amount The amount paid.
     * @return The change to be given to the customer.
     */
    public double pay(double amount) {
        this.cashRegister.addPayment(amount);
        Receipt receipt = new Receipt(this.currentSale, amount);
        this.printer.printReceipt(receipt.generateReceiptText());
        
        // Update total revenue and notify observers
        this.totalRevenue += this.currentSale.getRunningTotal();
        notifyObservers();
        
        return receipt.getChange();
    }
} 