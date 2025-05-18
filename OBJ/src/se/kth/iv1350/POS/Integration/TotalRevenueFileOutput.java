package se.kth.iv1350.POS.Integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Writes the total revenue to a file.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private static final String FILE_NAME = "total-revenue.txt";
    private PrintWriter logStream;

    /**
     * Creates a new instance and also creates a new log file. An existing log file will be deleted.
     * @throws RuntimeException if the file cannot be created.
     */
    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter(FILE_NAME), true);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize revenue file output", e);
        }
    }

    /**
     * Updates the total revenue and writes it to the file.
     *
     * @param totalRevenue The new total revenue.
     */
    @Override
    public void updateTotalRevenue(double totalRevenue) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = String.format("Time: %s\nTotal Revenue: %.2f SEK\n", time, totalRevenue);
        logStream.println(logMessage);
    }
} 