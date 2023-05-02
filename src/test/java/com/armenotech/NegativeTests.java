package com.armenotech;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.armenotech.URL.DOMAIN;
import static com.armenotech.URL.MAIN_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NegativeTests {


    @Test(description = "Verification of status code in case of invalid endpoint ")
    public void verifyStatusCodeInCaseOfInvalidEndpoint() {
         given()
                .baseUri(DOMAIN + "/")
                .param("countries", "SEN")
                .param("asset", "ATUSD")
                .when()
                .get(MAIN_ENDPOINT).
                then().statusCode(404);
    }

    @Test(description = "Verification of status code in case of invalid endpoint")
    public void verifyRequestByInvalidCountyParams() {
        given()
                .baseUri(DOMAIN )
                .param("countries", "invalid")
                .param("asset", "ATUSD")
                .when()
                .get(MAIN_ENDPOINT).
                then().
                statusCode(400);
    }

    @Test(description = "Verification of status code in case of invalid endpoint")
    public void verifyRequestByInvalidAsset() {
       Response response = given()
                .baseUri(DOMAIN )
                .param("countries", "WWC")
                .param("asset", "invalid")
                .when()
                .get(MAIN_ENDPOINT);
               response.then().
                statusCode(200);

               String strResponse = response.jsonPath().prettify();
        Assert.assertEquals(strResponse, "{\n" +
                "    \"receive\": {\n" +
                "        \n" +
                "    }\n" +
                "}");
    }


}
