package com.comcast.testcases;

import com.comcast.utils.PropReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static com.comcast.routes.Routes.BASE_URI;

public class BaseClass {

    protected PropReader propReader;
    @BeforeClass
    public void setup() throws IOException {
        RestAssured.baseURI = BASE_URI;
        propReader = new PropReader("src/test/resources/config.properties");
        RestAssured.config =
                RestAssured.config()
                        .objectMapperConfig(
                                ObjectMapperConfig.objectMapperConfig()
                                        .jackson2ObjectMapperFactory((cls, charset) ->
                                                new ObjectMapper())
                        );

    }
}
