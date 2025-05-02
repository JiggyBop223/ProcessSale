package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.Printer;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        InventorySystem inventorySystem = new InventorySystem();
        Printer printer = new Printer(); // Just prints to console
        controller = new Controller(inventorySystem, printer);
    }

    @Test
    void startSale_createsNewSale() {
        assertDoesNotThrow(() -> controller.startSale(),
                "Starting a new sale should not throw an exception");
    }

    @Test
    void registerItem_validId_addsItem() {
        controller.startSale();
        assertDoesNotThrow(() -> controller.registerItem("abc123"),
                "Registering a valid item should not throw an exception");
    }

    @Test
    void registerItem_invalidId_printsMessage() {
        controller.startSale();
        assertDoesNotThrow(() -> controller.registerItem("invalidID"),
                "Registering an invalid item should not crash the system");
        // This will print a message to the console, which is expected
    }

    @Test
    void endSale_printsTotal() {
        controller.startSale();
        controller.registerItem("abc123");
        assertDoesNotThrow(() -> controller.endSale(),
                "Ending sale should not throw an exception");
    }

    @Test
    void pay_updatesCashRegisterAndPrintsReceipt() {
        controller.startSale();
        controller.registerItem("abc123");
        assertDoesNotThrow(() -> controller.pay(100),
                "Payment should not throw an exception");
        // Output (receipt & change) is printed to console
    }
}
