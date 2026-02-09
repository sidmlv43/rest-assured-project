package com.comcast.testcases;

import com.comcast.pojo.product.Product;
import com.comcast.routes.Routes;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ProductDataDrivenTest extends BaseClass{


    @Test(
            testName = "Test Adding and Deleting product utilizing dataProviders",
            dataProvider = "jsonDataProvider",
            dataProviderClass = com.comcast.utils.DataProviders.class
    )
    public void testAddNewProduct(Map<String, String>  data ) {
        String title = data.get("title");
        double price = Double.parseDouble(data.get("price"));
        String category = data.get("category");
        String image = data.get("image");
        String description = data.get("description");
        Product prod = Product.builder()
                        .title(title)
                                .price(price)
                                        .category(category)
                                                .description(description)
                                                        .image(image)
                                                                .build();


        int id = given()
                .contentType(ContentType.JSON)
                .body(prod)

        .when()
                .post(Routes.CREATE_PRODUCT)
        .then()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(prod.getTitle()))
                .extract().jsonPath().get("id");
        ;


        given()
                .pathParams("id", id)
                .contentType(ContentType.JSON)
        .when()
                .delete(Routes.DELETE_PRODUCT)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
        ;
    }

}
