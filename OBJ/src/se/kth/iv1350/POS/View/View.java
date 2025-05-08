package se.kth.iv1350.POS.View;

import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Model.Item;

/**
 * Simulates the user interface using hardcoded method calls.
 */
public class View {
    private final Controller controller;

    /**
     * Creates a new instance of View.
     *
     * @param controller The controller to use.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Runs a sample execution of the program.
     */
    public void sampleExecution() {
        controller.startSale();

        // Add item abc123 once
        registerAndDisplayItem("abc123");

        // Add same item again to trigger alt flow 3-4b
        registerAndDisplayItem("abc123");

        // Add a different item
        registerAndDisplayItem("def456");

        displayEndSale();

        // Pay with 100 SEK
        displayPayment();
    }

    private void registerAndDisplayItem(String itemId) {
        ItemInformation itemInfo = controller.registerItem(itemId);
        if (itemInfo == null) {
            System.out.println("No item found with ID: " + itemId);
            return;
        }

        Item lastItem = controller.getLastRegisteredItem();
        if (lastItem != null) {
            displayItemInfo(lastItem);
            displayRunningTotal();
        }
    }

    private void displayItemInfo(Item item) {
        ItemInformation itemInfo = item.getItemInfo();
        System.out.printf("Add 1 item with item id %s :\n", itemInfo.getItemId());
        System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %.2f SEK\nVAT: %.0f%%\nItem description: %s\n\n",
            itemInfo.getItemId(),
            itemInfo.getName(),
            itemInfo.getPrice(),
            itemInfo.getVatRate() * 100.0,
            itemInfo.getDescription());
    }

    private void displayRunningTotal() {
        System.out.printf("Total cost (incl VAT): %.2f SEK\n", controller.getRunningTotal());
        System.out.printf("Total VAT: %.2f SEK\n\n", controller.getTotalVAT());
    }

    private void displayEndSale() {
        double total = controller.endSale();
        System.out.printf("End sale:\nTotal cost (incl VAT): %.2f SEK\n\n", total);
    }

    private void displayPayment() {
        double change = controller.pay(100);
        System.out.printf("Change to give the customer: %.2f SEK\n", change);
    }
}
