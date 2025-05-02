package model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Model.CashRegister;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    @Test
    void addPayment_shouldIncreaseBalanceCorrectly() {
        CashRegister cashRegister = new CashRegister();
        cashRegister.addPayment(50.0);
        cashRegister.addPayment(25.0);

        double expected = 75.0;
        double actual = cashRegister.getBalance();

        assertEquals(expected, actual, 0.0001, "Balance should be sum of all payments");
    }

    @Test
    void getBalance_shouldReturnZeroInitially() {
        CashRegister cashRegister = new CashRegister();

        double expected = 0.0;
        double actual = cashRegister.getBalance();

        assertEquals(expected, actual, 0.0001, "Initial balance should be zero");
    }
}
