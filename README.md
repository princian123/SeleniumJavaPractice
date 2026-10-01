# SeleniumJavaPractice# Selenium UI Automation Framework

A Java 25, Maven, Selenium, and TestNG starter framework using Page Object Model, thread-local browser drivers, explicit waits, Log4j2 logging, and ExtentReports.

## Requirements

- JDK 25 or newer
- Maven 3.8 or newer
- Chrome, Firefox, or Microsoft Edge installed for the selected browser
- Internet access for WebDriverManager to obtain the matching browser driver and for the sample SauceDemo site

## Run tests

From the project root:

```shell
mvn clean test
```

Select another browser or enable headless mode with system properties:

```shell
mvn clean test -Dbrowser=firefox -Dheadless=true
mvn clean test -Dbrowser=edge -Dheadless=false
```

TestNG runs methods in parallel using a dedicated WebDriver per thread. To execute only one group, pass a TestNG group filter, for example `mvn test -Dgroups=smoke`; the default suite includes both `smoke` and `regression` groups.

## Output

- HTML report: `target/extent-reports/extent-report.html`
- Failure screenshots: `target/screenshots/`
- Log file: `target/logs/automation.log`
- Surefire/TestNG results: `target/surefire-reports/`

## Project map

- `src/main/java/com/example/framework/config`: properties access with JVM system-property overrides.
- `src/main/java/com/example/framework/driver`: browser creation and thread-local driver lifecycle support.
- `src/main/java/com/example/framework/pages`: reusable page objects for SauceDemo login and inventory pages.
- `src/main/java/com/example/framework/utils`: explicit waits, screenshots, and Log4j2 logger access.
- `src/test/java`: shared test lifecycle, reporting listener, and sample login tests.
- `src/test/resources`: environment/test data properties, Log4j2 configuration, and TestNG suite definition.
- `.github/workflows/maven.yml`: headless Chrome CI execution with reports and screenshots uploaded as artifacts.

## Configuration and extension

Update `src/test/resources/config.properties` for the base URL, demo credentials, waits, or defaults. JVM properties such as `-Dbrowser=firefox` take precedence. For additional pages, add page objects under `src/main/java/com/example/framework/pages` and keep test assertions in test classes. API automation can be introduced as a separate package/module using Rest Assured without coupling API clients to browser lifecycle code.