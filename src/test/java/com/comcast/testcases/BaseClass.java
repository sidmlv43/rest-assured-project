package com.comcast.testcases;

import com.comcast.utils.PropReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static com.comcast.routes.Routes.BASE_URI;

public class BaseClass {

    RequestLoggingFilter requestLoggingFilter;
    ResponseLoggingFilter responseLoggingFilter;

    protected PropReader propReader;
    @BeforeClass
    public void setup() throws IOException {
        RestAssured.baseURI = BASE_URI;
        propReader = new PropReader("src/test/resources/config.properties");

        // Setup filters for logging

        FileOutputStream fos = new FileOutputStream("./logs/test_logging.txt");
        PrintStream log = new PrintStream(fos, true);

        requestLoggingFilter = new RequestLoggingFilter(log);
        responseLoggingFilter = new ResponseLoggingFilter(log);

        RestAssured.filters(requestLoggingFilter, responseLoggingFilter);

        RestAssured.config =
                RestAssured.config()
                        .objectMapperConfig(
                                ObjectMapperConfig.objectMapperConfig()
                                        .jackson2ObjectMapperFactory((cls, charset) ->
                                                new ObjectMapper())
                        );

    }


}
