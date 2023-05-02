package com.armenotech;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static com.armenotech.URL.DOMAIN;
import static com.armenotech.URL.MAIN_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PositiveTests {


    @Test(description = "Verification of filtration by various asset params",dataProvider = "Assets",dataProviderClass = ApiData.class)
    public void verifyAssetValueFilteringByQueryParam(String actualAsset, String expectedAsset) {
        Response response = given()
                .baseUri(DOMAIN)
                .param("countries", "WWC")
                .param("asset", actualAsset)
                .when()
                .get(MAIN_ENDPOINT);

        response
                .then()
                .statusCode(200);

        Set<String> assets = response.path("receive.keySet()");

        //Assert count of assets Set size, it is not acceptable to have duplicate keys in some hierarchy
        Assert.assertEquals(assets.size(), 1);


        // Assert if asset key equals to query param key
        for (String values : assets) {
            Assert.assertEquals(values, expectedAsset);
        }

    }

    @Test(description = "Verification of countries query by SEN value")
    public void verifyQueryByCountries() {
        Response response = given()
                .baseUri(DOMAIN)
                .param("countries", "SEN")
                .param("asset", "ATUSD")
                .when()
                .get(MAIN_ENDPOINT);

        response
                .then()
                .statusCode(200)
                .body("receive.ATUSD.fields.05bac156-c20c-40bd-8bdf-d177224d905f:b249b395-7bce-427b-b7df-52053f10818a.country_code",equalTo("SEN"));

    }
}


