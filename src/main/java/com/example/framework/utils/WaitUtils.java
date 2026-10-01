package com.example.framework.utils;

import com.example.framework.config.ConfigReader;
import com.example.framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {
    private WaitUtils() {
    }

    private static WebDriverWait waitForCondition() {
        return new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(ConfigReader.getInt("explicit.wait.seconds")));
    }

    public static WebElement visible(By locator) {
        return waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(By locator) {
        return waitForCondition().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean urlContains(String value) {
        return waitForCondition().until(ExpectedConditions.urlContains(value));
    }
}