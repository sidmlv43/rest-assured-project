package com.comcast.testcases;

import com.comcast.utils.PropReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static com.comcast.routes.Routes.BASE_URI;

public class BaseClass {

    protected PropReader propReader;
    @BeforeClass
    public void setup() throws IOException {
        RestAssured.baseURI = BASE_URI;
        propReader = new PropReader("config.properties");
    }
}
