package se.kth.iv1350.POS.View;

import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.*;
import se.kth.iv1350.POS.Model.Item;
import se.kth.iv1350.POS.Model.ItemBundle;
import se.kth.iv1350.POS.Model.SaleItem;
import java.util.List;

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
            String bundleName = controller.getLastRegisteredBundleName();
            int bundleQuantity = controller.getLastRegisteredBundleQuantity();
            List<ItemDTO> bundleItems = controller.getLastRegisteredBundleItems();

            if (bundleName != null && bundleItems != null) {
                displayBundleInfo(bundleName, bundleQuantity, bundleItems);
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
            ItemDTO lastItem = controller.getLastRegisteredItem();
            if (lastItem != null) {
                displayItemInfo(lastItem);
                displayRunningTotal();
            }
        } catch (ItemNotFoundException | DatabaseFailureException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e);
        }
    }

    private void displayItemInfo(ItemDTO item) {
        System.out.printf("Add 1 item with item id %s :\n", item.getItemId());
        System.out.printf("Item ID: %s\nItem name: %s\nItem cost: %.2f SEK\nVAT: %.0f%%\nItem description: %s\n\n",
                item.getItemId(),
                item.getName(),
                item.getPrice(),
                item.getVatRate() * 100.0,
                item.getDescription());
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

    private void displayBundleInfo(String bundleName, int quantity, List<ItemDTO> items) {
        System.out.printf("Add bundle: %s (Quantity: %d)\n", bundleName, quantity);
        System.out.println("Bundle contents:");
        for (ItemDTO item : items) {
            System.out.printf("- %s (%.2f SEK)\n", item.getName(), item.getPrice());
        }
        System.out.printf("Bundle total: %.2f SEK\n\n", controller.getRunningTotal());
    }
}
