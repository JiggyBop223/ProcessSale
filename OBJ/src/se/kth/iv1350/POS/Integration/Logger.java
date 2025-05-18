package se.kth.iv1350.POS.Integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Prints log messages to a file. The log file will be in the current directory and will be called
 * pos-log.txt.
 */
public class Logger {
    private static final String LOG_FILE_NAME = "pos-log.txt";
    private PrintWriter logStream;

    /**
     * Creates a new instance of Logger.
     * @throws RuntimeException if the log file cannot be created or written to.
     */
    public Logger() {
        try {
            logStream = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize logger", e);
        }
    }

    /**
     * Logs the specified exception to the log file.
     *
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logStream.println("Time: " + time);
        logStream.println("Exception: " + exception.getMessage());
        logStream.println("Stack trace:");
        exception.printStackTrace(logStream);
        logStream.println();
    }
} 