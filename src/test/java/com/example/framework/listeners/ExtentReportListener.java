package com.example.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.framework.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Path;

public class ExtentReportListener implements ITestListener {
    private static final ExtentReports REPORTS = createReports();
    private final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    private static ExtentReports createReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-reports/extent-report.html");
        reporter.config().setDocumentTitle("Selenium UI Test Results");
        reporter.config().setReportName("UI Automation Results");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        return reports;
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = REPORTS.createTest(result.getMethod().getMethodName());
        for (String group : result.getMethod().getGroups()) {
            test.assignCategory(group);
        }
        currentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.get().pass("Test passed");
        currentTest.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = currentTest.get();
        test.fail(result.getThrowable());
        try {
            Path screenshot = ScreenshotUtils.capture(result.getMethod().getMethodName());
            test.fail("Failure screenshot", MediaEntityBuilder.createScreenCaptureFromPath(
                    "../screenshots/" + screenshot.getFileName()).build());
        } catch (RuntimeException exception) {
            test.warning("Could not capture failure screenshot: " + exception.getMessage());
        }
        currentTest.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.get().skip(result.getThrowable());
        currentTest.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        REPORTS.flush();
    }
}