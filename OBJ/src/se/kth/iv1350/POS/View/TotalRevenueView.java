package se.kth.iv1350.POS.View;

import se.kth.iv1350.POS.Integration.TotalRevenueObserver;

/**
 * A view that displays the total revenue on the user interface.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    private double totalRevenue;

    /**
     * Creates a new instance of TotalRevenueView.
     */
    public TotalRevenueView() {
        this.totalRevenue = 0;
    }

    /**
     * Updates the total revenue and displays it on the user interface.
     *
     * @param totalRevenue The new total revenue.
     */
    @Override
    public void updateTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
        System.out.println("\nTotal Revenue: " + String.format("%.2f", this.totalRevenue) + " SEK");
    }
} 