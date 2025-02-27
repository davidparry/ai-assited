package ai.qodo.joke;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerConfig {
    public static void configureGlobalLogger(CommandFlags flags) {
        // Set default log level
        Level logLevel = Level.INFO;
        String logFilePath = null;

        // Parse flags for log level and log file path
        if (flags.getFlagValue("log").isPresent()) {
            String logLevelStr = flags.getFlagValue("log").get();
            if ("DEBUG".equalsIgnoreCase(logLevelStr)) {
                logLevel = Level.FINE;
            } else if ("INFO".equalsIgnoreCase(logLevelStr)) {
                logLevel = Level.INFO;
            } else if ("ERROR".equalsIgnoreCase(logLevelStr)) {
                logLevel = Level.SEVERE;
            }
        }

        if (flags.getFlagValue("logfile").isPresent()) {
            logFilePath = flags.getFlagValue("logfile").get();
        }

        // Configure root logger
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(logLevel);

        // Remove existing handlers
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        // Add console handler
        if (flags.hasFlag("-consolelog")) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(logLevel);
            rootLogger.addHandler(consoleHandler);
        }
        // Add file handler if log file path is provided
        if (logFilePath != null) {
            try {
                FileHandler fileHandler = new FileHandler(logFilePath, true);
                fileHandler.setLevel(logLevel);
                rootLogger.addHandler(fileHandler);
            } catch (IOException e) {
                rootLogger.severe("Failed to set up file handler for logging: " + e.getMessage());
            }
        }
    }
}
