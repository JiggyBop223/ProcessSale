package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.POS.Integration.DatabaseFailureException;
import se.kth.iv1350.POS.Integration.ItemNotFoundException;
import se.kth.iv1350.POS.Integration.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Logger class, focusing on exception handling.
 */
public class LoggerTest {
    private Logger logger;
    private static final String LOG_FILE_NAME = "pos-log.txt";

    @BeforeEach
    void setUp() {
        // Clean up any existing log file
        try {
            Files.deleteIfExists(Paths.get(LOG_FILE_NAME));
        } catch (IOException e) {
            fail("Failed to clean up log file before test");
        }
        logger = new Logger();
    }

    @Test
    void testLogItemNotFoundException() {
        ItemNotFoundException exception = new ItemNotFoundException("test123");
        logger.logException(exception);
        
        assertTrue(new File(LOG_FILE_NAME).exists(), "Log file should be created");
        String logContent = readLogFile();
        assertTrue(logContent.contains("ItemNotFoundException"), "Log should contain exception type");
        assertTrue(logContent.contains("test123"), "Log should contain item ID");
    }

    @Test
    void testLogDatabaseFailureException() {
        DatabaseFailureException exception = new DatabaseFailureException("test123");
        logger.logException(exception);

        assertTrue(new File(LOG_FILE_NAME).exists(), "Log file should be created");
        String logContent = readLogFile();
        assertTrue(logContent.contains("DatabaseFailureException"), "Log should contain exception type");
        assertTrue(logContent.contains("test123"), "Log should contain item ID");
    }

    private String readLogFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(LOG_FILE_NAME)));
        } catch (IOException e) {
            fail("Failed to read log file");
            return "";
        }
    }
} 