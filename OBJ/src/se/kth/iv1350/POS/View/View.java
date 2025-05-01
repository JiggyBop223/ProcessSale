package se.kth.iv1350.POS.View;

import se.kth.iv1350.POS.Controller.Controller;

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
        controller.registerItem("abc123");

        // Add same item again to trigger alt flow 3-4b
        controller.registerItem("abc123");

        // Add a different item
        controller.registerItem("def456");

        controller.endSale();

        // Pay with 100 SEK
        controller.pay(100);
    }
}
