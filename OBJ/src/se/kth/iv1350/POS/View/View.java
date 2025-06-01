package se.kth.iv1350.POS.View;

import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.*;
import se.kth.iv1350.POS.Model.Item;
import se.kth.iv1350.POS.Model.ItemBundle;
import se.kth.iv1350.POS.Model.SaleItem;

/**
 * Simulates the user interface using hardcoded method calls.
 */
public class View {
    private final Controller controller;
    private final Logger logger;
    private final TotalRevenueView revenueView;
    private final TotalRevenueFileOutput revenueFileOutput;

    /**
     * Creates a new instance of View.
     *
     * @param controller The controller to use.
     */
    public View(Controller controller) {
        this.controller = controller;
        this.logger = new Logger();
        this.revenueView = new TotalRevenueView();
        this.revenueFileOutput = new TotalRevenueFileOutput();
        controller.addRevenueObserver(revenueView);
        controller.addRevenueObserver(revenueFileOutput);
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

        // Add a bundle of items
        try {
            controller.addBundle("Breakfast Bundle", 1, "abc123", "def456");
            SaleItem lastItem = controller.getLastRegisteredItem();
            if (lastItem != null && lastItem instanceof ItemBundle) {
                displayBundleInfo((ItemBundle)lastItem);
                displayRunningTotal();
            }
        } catch (ItemNotFoundException | DatabaseFailureException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e);
        }

        // Try to add a non-existent item
        registerAndDisplayItem("invalid123");

        // Try to add an item that causes database failure
        registerAndDisplayItem("999999");

        displayEndSale();

        // Pay with 100 SEK
        displayPayment();
    }

    private void registerAndDisplayItem(String itemId) {
        try {
            ItemInformation itemInfo = controller.registerItem(itemId);
            SaleItem lastItem = controller.getLastRegisteredItem();
            if (lastItem != null && lastItem instanceof Item) {
                displayItemInfo((Item)lastItem);
                displayRunningTotal();
            }
        } catch (ItemNotFoundException | DatabaseFailureException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e);
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
        double change = controller.pay(200);
        System.out.printf("Change to give the customer: %.2f SEK\n", change);
    }

    private void displayBundleInfo(ItemBundle bundle) {
        System.out.printf("Add bundle: %s (Quantity: %d)\n", bundle.getName(), bundle.getQuantity());
        System.out.println("Bundle contents:");
        for (SaleItem item : bundle.getItems()) {
            if (item instanceof Item) {
                ItemInformation itemInfo = ((Item)item).getItemInfo();
                System.out.printf("- %s (%.2f SEK)\n", itemInfo.getName(), itemInfo.getPrice());
            }
        }
        System.out.printf("Bundle total: %.2f SEK\n\n", bundle.getTotalPriceWithVAT().doubleValue());
    }
}
