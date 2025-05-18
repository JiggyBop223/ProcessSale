package view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.DatabaseFailureException;
import se.kth.iv1350.POS.Integration.ItemNotFoundException;
import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.Printer;
import se.kth.iv1350.POS.View.View;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the View class, focusing on exception handling.
 */
public class ViewTest {
    private View view;
    private Controller controller;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        InventorySystem inventorySystem = InventorySystem.getInstance();
        Printer printer = new Printer();
        controller = new Controller(inventorySystem, printer);
        view = new View(controller);
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testViewHandlesNonExistentItem() {
        assertDoesNotThrow(() -> view.sampleExecution(), 
            "View should handle ItemNotFoundException without throwing");
    }

    @Test
    void testViewHandlesDatabaseFailure() {
        assertDoesNotThrow(() -> view.sampleExecution(), 
            "View should handle DatabaseFailureException without throwing");
    }
} 