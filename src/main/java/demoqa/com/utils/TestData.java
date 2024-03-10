package demoqa.com.utils;

import java.util.stream.Stream;

public class TestData {

    private static final String BASE_URL = "https://demoqa.com/";
    private static final String LOGIN_URL = "https://demoqa.com/login";
    private static final String PROFILE_URL = "https://demoqa.com/profile";
    private static final String USERNAME = "tau-playwright";
    private static final String PASSWORD = "TestingWithR3n@t@";
    private static final String ERROR_DATA = "Invalid Data";
    private static final String ERROR_MESSAGE = "Invalid username or password!";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getLoginUrl() {
        return LOGIN_URL;
    }

    public static String getProfileUrl() {
        return PROFILE_URL;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    public static Stream<String[]> invalidLoginDataProvider() {
        return Stream.of(
                new String[]{ERROR_DATA, PASSWORD},
                new String[]{USERNAME, ERROR_DATA},
                new String[]{ERROR_DATA, ERROR_DATA}
        );
    }
    
}
