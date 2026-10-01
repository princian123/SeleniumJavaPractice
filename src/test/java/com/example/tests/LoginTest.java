package com.example.tests;

import com.example.framework.BaseTest;
import com.example.framework.config.ConfigReader;
import com.example.framework.pages.InventoryPage;
import com.example.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(groups = {"smoke", "regression"})
    public void validUserCanLogIn() {
        logger.info("Verify a valid user can log in");
        new LoginPage().open().loginAs(ConfigReader.get("test.username"), ConfigReader.get("test.password"));
        Assert.assertTrue(new InventoryPage().isLoaded(), "Expected the inventory page after login");
    }

    @Test(groups = {"regression"})
    public void invalidUserSeesLoginError() {
        logger.info("Verify invalid credentials are rejected");
        String error = new LoginPage().open().loginAs(ConfigReader.get("invalid.username"),
                ConfigReader.get("test.password")).getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"),
                "Unexpected login error message: " + error);
    }
}