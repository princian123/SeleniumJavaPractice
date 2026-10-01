package com.example.framework.utils;

import com.example.framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class ScreenshotUtils {
    private static final Logger LOGGER = LogManager.getLogger(ScreenshotUtils.class);
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    private ScreenshotUtils() {
    }

    public static Path capture(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (!(driver instanceof TakesScreenshot screenshotDriver)) {
            throw new IllegalStateException("Current WebDriver does not support screenshots");
        }
        String safeName = testName.replaceAll("[^a-zA-Z0-9-_]", "_");
        Path destination = Path.of("target", "screenshots", safeName + "-"
                + LocalDateTime.now().format(TIMESTAMP) + "-" + UUID.randomUUID() + ".png");
        try {
            Files.createDirectories(destination.getParent());
            Files.copy(screenshotDriver.getScreenshotAs(OutputType.FILE).toPath(), destination,
                    StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Saved failure screenshot to {}", destination);
            return destination;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to save screenshot", exception);
        }
    }
}