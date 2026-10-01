package com.example.framework.pages;

import com.example.framework.config.ConfigReader;
import com.example.framework.utils.WaitUtils;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public LoginPage open() {
        driver.get(ConfigReader.get("base.url"));
        WaitUtils.visible(USERNAME);
        return this;
    }

    public LoginPage loginAs(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
        return this;
    }

    public String getErrorMessage() {
        return text(ERROR_MESSAGE);
    }
}