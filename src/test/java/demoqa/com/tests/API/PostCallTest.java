package demoqa.com.tests.API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static demoqa.com.utils.TestData.getPassword;
import static demoqa.com.utils.TestData.getUsername;

public class PostCallTest {
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
    void postApiGenerateTokenTest() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", getUsername());
        data.put("password", getPassword());

        APIResponse apiPOSTResponse = apiRequestContext.post("https://bookstore.toolsqa.com/Account/v1/GenerateToken",
                RequestOptions.create()
                        .setData(data)
                 );
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiPOSTResponse.body());

        System.out.println(jsonResponse.toPrettyString());

        String token = jsonResponse.get("token").asText();
        System.out.println("token" + token);
    }

}


