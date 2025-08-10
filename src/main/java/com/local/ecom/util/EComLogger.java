package com.local.ecom.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EComLogger {
    private static final Logger logger = LoggerFactory.getLogger(EComLogger.class);
    private static final String LOG_PREFIX = "ECOM: ";

    private EComLogger() {

    }

    public static void logDebug(String message) {
        if(logger.isDebugEnabled()) {
            logger.debug(LOG_PREFIX + "{}", message);
        }
    }

    public static void logError(String message) {
        if(logger.isErrorEnabled()) {
            logger.error(LOG_PREFIX + "{}", message);
        }
    }

    public static void logError(String message, Exception exception) {
        if (logger.isErrorEnabled()) {
            if(exception == null) {
                logger.error(LOG_PREFIX + "{}", message);
            } else {
                message += ".\n Exception message: " + exception.getMessage() + "\n Exception cause: " + exception.getCause();
                logger.error(LOG_PREFIX + "{}", message,exception);
            }
        }
    }
}
