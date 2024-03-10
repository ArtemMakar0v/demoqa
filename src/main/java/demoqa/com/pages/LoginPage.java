package demoqa.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage extends BasePage {

    private final Locator inputUserName;
    private final Locator inputPassword;
    private final Locator loginButton;
    private final Locator cookieConsentButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        super(page);
        this.inputUserName = getLocator("#userName");
        this.inputPassword = getLocator("#password");
        this.loginButton = getLocator("#login");
        this.cookieConsentButton = getLocator("[aria-label='Zgadzam się']");
        this.errorMessage = getLocator("p#name");
    }

    public LoginPage acceptCookies() {
        if (cookieConsentButton.isVisible()) {
            cookieConsentButton.click();
        }
        return this;
    }

    public LoginPage fillCredentials(String username, String password) {
        inputUserName.isVisible();
        inputUserName.fill(username);
        inputPassword.isVisible();
        inputPassword.fill(password);
        return this;
    }

    public LoginPage submitLogin() {
        loginButton.isVisible();
        loginButton.click();
        return this;
    }

    public void expectErrorMessage(String expectedErrorMessage) {
        assertThat(errorMessage).hasText(expectedErrorMessage);
    }

}
