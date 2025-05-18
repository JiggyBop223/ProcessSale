package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private MockPrinter mockPrinter;

    private static class MockPrinter extends Printer {
        private String lastPrintedText;

        @Override
        public void printReceipt(String receiptText) {
            lastPrintedText = receiptText;
        }

        public String getLastPrintedText() {
            return lastPrintedText;
        }
    }

    @BeforeEach
    void setUp() {
        InventorySystem inventorySystem = InventorySystem.getInstance();
        mockPrinter = new MockPrinter();
        controller = new Controller(inventorySystem, mockPrinter);
    }

    @Test
    void startSale_createsNewSale() {
        assertDoesNotThrow(() -> controller.startSale(),
                "Starting a new sale should not throw an exception");
    }

    @Test
    void registerItem_validId_addsItem() throws ItemNotFoundException, DatabaseFailureException {
        controller.startSale();
        assertDoesNotThrow(() -> controller.registerItem("abc123"),
                "Registering a valid item should not throw an exception");
    }

    @Test
    void registerItem_invalidId_throwsItemNotFoundException() {
        controller.startSale();
        assertThrows(ItemNotFoundException.class, () -> controller.registerItem("invalidID"),
                "Registering an invalid item should throw ItemNotFoundException");
    }

    @Test
    void registerItem_databaseFailure_throwsDatabaseFailureException() {
        controller.startSale();
        assertThrows(DatabaseFailureException.class, () -> controller.registerItem("999999"),
                "Registering an item with database failure ID should throw DatabaseFailureException");
    }

    @Test
    void endSale_returnsTotal() throws ItemNotFoundException, DatabaseFailureException {
        controller.startSale();
        controller.registerItem("abc123");
        double total = controller.endSale();
        assertEquals(29.90, total, 0.001, "End sale should return correct total");
    }

    @Test
    void pay_updatesCashRegisterAndPrintsReceipt() throws ItemNotFoundException, DatabaseFailureException {
        controller.startSale();
        controller.registerItem("abc123");
        double change = controller.pay(100);
        
        assertEquals(70.10, change, 0.001, "Change should be correct");
        assertNotNull(mockPrinter.getLastPrintedText(), "Receipt should be printed");
        assertTrue(mockPrinter.getLastPrintedText().contains("BigWheel Oatmeal"), "Receipt should contain item name");
    }
}
