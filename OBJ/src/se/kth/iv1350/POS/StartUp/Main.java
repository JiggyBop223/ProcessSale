package se.kth.iv1350.POS.StartUp;

import se.kth.iv1350.POS.Controller.Controller;
import se.kth.iv1350.POS.Integration.InventorySystem;
import se.kth.iv1350.POS.Integration.Printer;
import se.kth.iv1350.POS.View.View;

public class Main {
    public static void main(String[] args) {
        InventorySystem inventorySystem = InventorySystem.getInstance();
        Printer printer = new Printer();
        Controller controller = new Controller(inventorySystem, printer);
        View view = new View(controller);
        view.sampleExecution();
    }
} 