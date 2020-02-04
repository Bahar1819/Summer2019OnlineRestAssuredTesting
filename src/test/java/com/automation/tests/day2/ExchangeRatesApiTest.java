package com.automation.tests.day2;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ExchangeRatesApiTest {

    private String baseURI="http://api.openrates.io";

    @Test
    public void test1(){
        Response response= given().get(baseURI+"/latest");

        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());

    }
    @Test
    public void test2(){
        Response response= given().get(baseURI+"/latest");
        //verify that content version is json
        //System.out.println(response.getHeader("Content-type"));
        assertEquals("application/json", response.getHeader("Content-type"));
        assertEquals("application/json", response.getContentType());

    }
    @Test
    public void test3(){
        Response response= given().get(baseURI+"/latest?base=USD");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());

    }
    @Test
    public void test4(){
        Response response=given().
                baseUri(baseURI+"/latest").
                queryParam("base", "GBP").
                get();
        String todaysDate= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Todays date: "+todaysDate);
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().asString().contains(todaysDate));
    }

    @Test
    public void test5(){
        Response response= given().
                baseUri(baseURI+"/history").
                queryParam("start_at", "2000-01-01").
                queryParam("end_at", "2000-12-31").
                queryParam("base", "USD").
                queryParam("symbols","EUR","GBP","JPY").
                get();
        System.out.println(response.prettyPrint());
    }
}
