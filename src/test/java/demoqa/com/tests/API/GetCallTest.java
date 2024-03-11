package demoqa.com.tests.API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GetCallTest {
    Playwright playwright;
    APIRequest request;
    APIRequestContext apiRequestContext;

    @BeforeEach
    void setUp(){
        playwright = Playwright.create();
        request = playwright.request();
        apiRequestContext = request.newContext();
    }

    @AfterEach
    void tearDown(){
        playwright.close();
    }

    @Test
    void getSpecificApiBooksTest() throws IOException {
        APIResponse apiResponse = apiRequestContext.get("https://demoqa.com/BookStore/v1/Book",
                RequestOptions.create()
                        .setQueryParam("ISBN", "9781449325862"));
        Assertions.assertTrue(apiResponse.ok());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        System.out.println("---print api json response---");
        System.out.println(jsonResponse.toPrettyString());
    }

    @Test
    void getAllApiBooksTest() throws IOException {

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
