package com.automation.tests.day3;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSTestsDay3 {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI=ConfigurationReader.getProperty("ords.uri");
    }

    @Test
    public void test1(){
        given().
                accept("application/json").
                get("employees").
         then().
                assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                log().all(true);
    }

    @Test
    public void test2(){
        given().
                accept("application/json").
                pathParam("id", "100").
         when().get("employees/{id}").
         then().assertThat().statusCode(200).
         and().assertThat().body("employee_id",is(100)).log().all(true);
    }

    @Test
    public void test3(){
        given().
                accept("application/json").
                pathParam("id",1).when().
                get("regions/{id}").
        then().assertThat().statusCode(200).
                assertThat().body("region_name", is("Europe")).
                time(lessThan(3L), TimeUnit.SECONDS).log().body(true);
    }

    @Test
    public void test4(){
        JsonPath json= given().
                accept("application/json").
         when().
                get("employees").
         thenReturn().jsonPath();

        String nameOfFirstEmployee=json.getString("items[0].first_name");
        String nameOfLastEmployee=json.getString("items[-1].first_name");

        System.out.println("First employee name: "+nameOfFirstEmployee);
        System.out.println("Last employee name: "+nameOfLastEmployee);

        Map<String, String>firstEmployee=json.get("items[0]");
        System.out.println(firstEmployee);
        for (Map.Entry<String, ?> entry: firstEmployee.entrySet()){
            System.out.println("key: "+ entry.getKey()+", value: "+ entry.getValue());
        }

    }
}
