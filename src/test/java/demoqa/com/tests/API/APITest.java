package demoqa.com.tests.API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class APITest {
    @Test
    void getAllBooks() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest request = playwright.request();
        APIRequestContext apiRequestContext = request.newContext();

        APIResponse apiResponse = apiRequestContext.get("https://demoqa.com/BookStore/v1/Books");

        int statusCode = apiResponse.status();
        Assertions.assertEquals(statusCode, 200);
        String statusResText = apiResponse.statusText();
        Assertions.assertEquals(statusResText, "OK");
        System.out.println("response code = " + statusCode);
        System.out.println("response text = " + statusResText);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        System.out.println("---print api json response---");
        System.out.println(jsonResponse.toPrettyString());

        System.out.println("---print api url---");
        System.out.println(apiResponse.url());
    }
}
