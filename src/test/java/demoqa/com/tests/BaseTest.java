package demoqa.com.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    private final BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        launchOptions.setHeadless(false);
        browser = chooseBrowser().launch(launchOptions);
        page = browser.newPage();
    }

    @AfterEach
    void tearDown() {
        browser.close();
        playwright.close();
    }

    private BrowserType chooseBrowser() {
        String browserName = System.getProperty("browser", "chromium"); // Defaults to chromium
        switch (browserName.toLowerCase()) {
            case "chromium":
                return playwright.chromium();
            case "firefox":
                return playwright.firefox();
            case "webkit":
                return playwright.webkit();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

}
