package com.example.framework.pages;

import com.example.framework.utils.WaitUtils;
import org.openqa.selenium.By;

public class InventoryPage extends BasePage {
    private static final By PAGE_TITLE = By.cssSelector(".title");

    public String getTitle() {
        return text(PAGE_TITLE);
    }

    public boolean isLoaded() {
        return WaitUtils.urlContains("inventory.html") && "Products".equals(getTitle());
    }
}