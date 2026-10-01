package com.example.framework.pages;

import com.example.framework.driver.DriverManager;
import com.example.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }

    protected void click(By locator) {
        WaitUtils.clickable(locator).click();
    }

    protected void type(By locator, String value) {
        var element = WaitUtils.visible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String text(By locator) {
        return WaitUtils.visible(locator).getText();
    }
}