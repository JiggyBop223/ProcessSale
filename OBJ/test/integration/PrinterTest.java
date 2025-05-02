package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Integration.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void printReceipt_shouldPrintCorrectText() {
        Printer printer = new Printer();
        String testReceipt = "=== TEST RECEIPT ===\nItem: abc123\nTotal: 100.00 SEK\n=== END ===";

        printer.printReceipt(testReceipt);

        String printedOutput = outContent.toString().trim(); // Remove extra newlines
        assertEquals(testReceipt, printedOutput, "Printer should print the correct receipt text.");
    }
}
