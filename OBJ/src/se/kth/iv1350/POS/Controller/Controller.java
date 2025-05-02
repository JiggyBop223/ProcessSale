package se.kth.iv1350.POS.Controller;

import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Integration.Printer;
import se.kth.iv1350.POS.Model.*;
import java.util.List;

/**
 * Represents the controller of the application.
 */
public class Controller {
    private final InventorySystem inventorySystem;
    private final Printer printer;
    private final CashRegister cashRegister;
    private Sale currentSale;

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
     */
    public void registerItem(String itemId) {
        ItemInformation itemInfo = this.inventorySystem.findItem(itemId);
        if (itemInfo == null) {
            System.out.println("No item found with ID: " + itemId);
        } else {
            this.currentSale.registerItem(itemInfo);
            this.printLastItemInfo();
            this.printRunningTotal();
        }
    }

    private void printLastItemInfo() {
        List<Item> items = this.currentSale.getItems();
        Item item = items.get(items.size() - 1);
        ItemInformation itemInfo = item.getItemInfo();
        System.out.printf("Add 1 item with item id %s :\n", itemInfo.getItemId());
        System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %.2f SEK\nVAT: %.0f%%\nItem description: %s\n\n",
            itemInfo.getItemId(),
            itemInfo.getName(),
            itemInfo.getPrice(),
            itemInfo.getVatRate() * 100.0,
            itemInfo.getDescription());
    }

    private void printRunningTotal() {
        System.out.printf("Total cost (incl VAT): %.2f SEK\n", this.currentSale.getRunningTotal());
        System.out.printf("Total VAT: %.2f SEK\n\n", this.currentSale.getTotalVAT());
    }

    /**
     * Ends the current sale.
     */
    public void endSale() {
        System.out.printf("End sale:\nTotal cost (incl VAT): %.2f SEK\n\n", this.currentSale.getRunningTotal());
    }

    /**
     * Handles payment for the sale.
     *
     * @param amount The amount paid.
     */
    public void pay(double amount) {
        this.cashRegister.addPayment(amount);
        Receipt receipt = new Receipt(this.currentSale, amount);
        this.printer.printReceipt(receipt.generateReceiptText());
        System.out.printf("Change to give the customer: %.2f SEK\n", receipt.getChange());
    }
} 