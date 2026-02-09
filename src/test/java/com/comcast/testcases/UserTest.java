package com.comcast.testcases;



import com.comcast.pojo.user.User;
import com.comcast.routes.Routes;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static com.comcast.payloads.Payload.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseClass{
    @Test(testName = "Test Create User")
    public void createUser() {
        User user = getUserPayLoad();
        given()
                .body(user)
        .when()
                .post(Routes.CREATE_USER)
        .then()
                .contentType(ContentType.JSON)
                .statusCode(201)
                .log().all();
    }

    @Test(testName = "Testing get All users")

    public void testGetAllUsers() {
        List<User> users = given()
                .when().get(Routes.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .extract().response().as(new TypeRef<List<User>>() {
                });
        System.out.println(users);
    }
}
