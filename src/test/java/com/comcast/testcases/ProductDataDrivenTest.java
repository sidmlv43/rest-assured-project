package com.comcast.testcases;

import org.testng.annotations.Test;

import java.util.Map;

public class ProductDataDrivenTest extends BaseClass{


    @Test(
            testName = "Test Adding new product utilizing dataProviders",
            dataProvider = "jsonDataProvider",
            dataProviderClass = com.comcast.utils.DataProviders.class
    )
    public void testAddNewProduct(Map<String, String>  data ) {
        System.out.println(data);
    }

}
