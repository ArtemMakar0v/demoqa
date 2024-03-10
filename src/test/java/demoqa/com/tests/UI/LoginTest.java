package demoqa.com.tests.UI;

import demoqa.com.pages.LoginPage;
import demoqa.com.utils.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static demoqa.com.utils.TestData.getErrorMessage;
import static demoqa.com.utils.TestData.getLoginUrl;
import static demoqa.com.utils.TestData.getPassword;
import static demoqa.com.utils.TestData.getProfileUrl;
import static demoqa.com.utils.TestData.getUsername;

public class LoginTest extends BaseTest {
    protected static Stream<Arguments> invalidLoginData() {
        return TestData.invalidLoginDataProvider().map(Arguments::of);
    }

    @Test
    void positiveLoginDataTest() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToUrl(getLoginUrl());
        loginPage
                .acceptCookies()
                .fillCredentials(getUsername(), getPassword())
                .submitLogin()
                .assertUrl(getProfileUrl());
    }

    @ParameterizedTest
    @MethodSource("invalidLoginData")
    void negativeLoginDataTest(String username, String password) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToUrl(getLoginUrl());
        loginPage
                .acceptCookies()
                .fillCredentials(username, password)
                .submitLogin()
                .expectErrorMessage(getErrorMessage());
    }

}
