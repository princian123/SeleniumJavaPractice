package com.example.framework;

import com.example.framework.driver.DriverFactory;
import com.example.framework.driver.DriverManager;
import com.example.framework.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected final Logger logger = LoggerUtil.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        logger.info("Starting test with browser configuration");
        DriverManager.setDriver(DriverFactory.createDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}