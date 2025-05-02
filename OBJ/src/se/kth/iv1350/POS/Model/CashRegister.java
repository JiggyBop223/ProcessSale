package se.kth.iv1350.POS.Model;

/**
 * Represents a cash register.
 */
public class CashRegister {
    private double balance;

    /**
     * Creates a new cash register.
     */
    public CashRegister() {

        this.balance = 0;
    }

    /**
     * Adds payment to the register.
     *
     * @param amount The amount to add.
     */
    public void addPayment(double amount) {

        this.balance += amount;
    }

    /**
     * Gets the current balance.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }
}
