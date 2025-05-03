package model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Model.Sale;
import se.kth.iv1350.POS.Integration.ItemInformation;
import se.kth.iv1350.POS.Model.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    @Test
    void registerItem_shouldAddNewItemToList() {
        Sale sale = new Sale();
        ItemInformation itemInfo = new ItemInformation("abc123", "Oatmeal", "Description", 10.0, 0.1);

        sale.registerItem(itemInfo);
        List<Item> items = sale.getItems();

        assertEquals(1, items.size(), "Sale should contain one item after registration");
        assertEquals("abc123", items.get(0).getItemInfo().getItemId(), "Item ID should match");
        assertEquals(1, items.get(0).getQuantity(), "Quantity should be 1");
    }

    @Test
    void registerItem_shouldIncreaseQuantityIfSameItem() {
        Sale sale = new Sale();
        ItemInformation itemInfo = new ItemInformation("abc123", "Oatmeal", "Description", 10.0, 0.1);

        sale.registerItem(itemInfo);
        sale.registerItem(itemInfo);
        List<Item> items = sale.getItems();

        assertEquals(1, items.size(), "Sale should not duplicate items with same ID");
        assertEquals(2, items.get(0).getQuantity(), "Quantity should be increased to 2");
    }

    @Test
    void getRunningTotal_shouldReturnCorrectTotalWithVAT() {
        Sale sale = new Sale();

        ItemInformation itemInfo = new ItemInformation("xyz999", "Juice", "Fruit juice", 22.0, 0.1);

        sale.registerItem(itemInfo); // total = 22
        sale.registerItem(itemInfo); // total = 44

        assertEquals(44.0, sale.getRunningTotal(), 0.0001, "Running total should include VAT");
    }

    @Test
    void getTotalVAT_shouldReturnCorrectVATOnly() {
        Sale sale = new Sale();

        ItemInformation itemInfo = new ItemInformation("bcd456", "Protein Bar", "Bar", 51.0, 0.2);

        sale.registerItem(itemInfo);
        sale.registerItem(itemInfo);

        assertEquals(17.0, sale.getTotalVAT(), 0.0001, "Total VAT should be correct for quantity 2");
    }
}
