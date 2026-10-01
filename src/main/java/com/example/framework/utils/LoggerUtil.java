package com.example.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtil {
    private LoggerUtil() {
    }

    public static Logger getLogger(Class<?> type) {
        return LogManager.getLogger(type);
    }
}