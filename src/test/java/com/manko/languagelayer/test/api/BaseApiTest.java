package com.manko.languagelayer.test.api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;

public class BaseApiTest {

    private static final String BASE_URI = "http://api.languagelayer.com";
    private static final String ACCESS_KEY = "6c5d2a057fe331a3e17291df03e0cd44";

    @Before
    public void setUpAssuredSpec() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addQueryParam("access_key", ACCESS_KEY)
                .build();
    }

    @After
    public void tearDownAssuredSpec() {
        RestAssured.requestSpecification = null;
    }
}
