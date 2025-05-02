package view;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.Printer;
import se.kth.iv1350.POS.View.View;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ViewTest {

    @Test
    void sampleExecution() {
        InventorySystem inventorySystem = new InventorySystem();
        Printer printer = new Printer();
        Controller controller = new Controller(inventorySystem, printer);
        View view = new View(controller);

        assertDoesNotThrow(view::sampleExecution, "sampleExecution() should run without throwing exceptions");
    }
}
