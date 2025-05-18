package se.kth.iv1350.POS.Integration;

/**
 * Interface for observers that want to be notified about total revenue updates.
 */
public interface TotalRevenueObserver {
    /**
     * Called when a new sale is completed and the total revenue is updated.
     *
     * @param totalRevenue The new total revenue.
     */
    void updateTotalRevenue(double totalRevenue);
} 