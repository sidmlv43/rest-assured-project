package com.comcast.testcases;

import com.comcast.routes.Routes;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ProductTests extends BaseClass{


    @Test(testName = "Test To retrieve all products")
    public void testGetAllProducts() {
        given()
        .when()
                .get(Routes.GET_ALL_PRODUCTS)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all()
                .body("size()", greaterThan(0))
        ;
    }


    @Test(testName = "Test to retrieve single product by ID")
    public void testGetProductById() {
        String prodID = propReader.get("productId");

        given()
                .pathParams("id", prodID)
        .when()
                .get(Routes.GET_PRODUCT_BY_ID)
        .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .log().all()
                .body("rating.rate", greaterThan(0.0F))
                ;

    }

    @Test(testName = "Test to retrieve a limited number of product")
    public void testGetProductWithLimit() {

        given()
                .queryParam("limit", 3)
        .when()
                .get(Routes.GET_PRODUCT_WITH_LIMIT)
        .then()
                .statusCode(200)
                .body("size()", equalTo(3))
                .log().all()
        ;
    }
}
