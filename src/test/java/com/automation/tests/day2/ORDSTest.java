package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ORDSTest {

    private String baseURI= "http://ec2-54-152-50-187.compute-1.amazonaws.com:1000/ords/hr";

    @Test
    public void test1(){
        Response response= given().get( baseURI+"/employees");
        System.out.println(response.getBody().asString());
        Assertions.assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

}
