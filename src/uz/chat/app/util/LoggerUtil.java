package uz.chat.app.util;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    private static final Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());
    private static final String LOG_FILE = "db/chat_app.log";

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(fileHandler);
            LOGGER.addHandler(consoleHandler);

            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("❌ Log konfiguratsiyasida xatolik: " + e.getMessage());
        }
    }

    public static void logInfo(String message) {
        LOGGER.info(message);
    }

    public static void logWarning(String message) {
        LOGGER.warning(message);
    }

    public static void logSevere(String message) {
        LOGGER.severe(message);
    }
}

