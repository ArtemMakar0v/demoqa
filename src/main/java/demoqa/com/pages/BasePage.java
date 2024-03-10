package demoqa.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BasePage {
    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
        this.page.setDefaultNavigationTimeout(5000);
        this.page.setDefaultTimeout(5000);
    }

    protected Locator getLocator(String selector) {
        return page.locator(selector);
    }

    public void navigateToUrl(String url) {
        page.navigate(url);
        page.waitForLoadState();
    }

    public void assertUrl(String expectedUrl) {
        assertThat(page).hasURL(expectedUrl);
    }

}
