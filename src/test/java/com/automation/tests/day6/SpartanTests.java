package com.automation.tests.day6;

import com.automation.pojos.Spartan;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeAll;
import com.automation.pojos.Job;
import com.automation.pojos.Location;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
    public class SpartanTests {
        @BeforeAll
        public static void setup(){
            baseURI = ConfigurationReader.getProperty("spartan.uri");
        }

        /**
         * given accept content type as JSON
         * when user sends GET request to /spartans
         * then user verifies that status code is 200
         * and user verifies that content type is JSON
         */

        @Test
        @DisplayName("Verify that/ spartans end-point returns 200 and content type JSON")
        public void test1(){
            given().
                    accept(ContentType.JSON).
            when().
                    get("/spartans").prettyPeek().
            then().assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON);
        }
        /**
         * given accept content type as XML
         * when user sends GET request to /spartans
         * then user verifies that status code is 200
         * and user verifies that content type is XML
         */

        @Test
        @DisplayName("Verify that/ spartans end-point returns 200 and content type XML")
        public void test2(){
            given().
                    accept(ContentType.JSON).
                    when().
                    get("/spartans").prettyPeek().
                    then().assertThat().
                    statusCode(200).
                    contentType(ContentType.XML);
        }

        @Test
        @DisplayName("Save payload into Collection")
        public void test3(){
            Response response= given().
                                        contentType(ContentType.JSON).
                               when()
                                       .get("/spartans");

            List<Map<String,?>> collection= response.jsonPath().get();

            for (Map<String, ?> map: collection){
                System.out.println(map);
            }

        }
        @Test
        public void test4(){
            Response response= given().
                    contentType(ContentType.JSON).
            when().
                    get("/spartans").prettyPeek();

            List<Spartan> collection= response.jsonPath().getList("", Spartan.class);

            for (Spartan spartan: collection){
                System.out.println(spartan);
            }
        }

        @Test
        @DisplayName("Create new spartan and verify that status code is 201")
        public void test5(){
            Spartan spartan = new Spartan();
            spartan.setGender("male");
            spartan.setName("Mister Twister");
            spartan.setPhone(5712134235L);

            //builder pattern, one of the design patterns in OOP
            Spartan spartan1 = new Spartan().
                    withGender("Male").
                    withName("Some User").
                    withPhone(5712134235L);

            Response response=given().
                                   contentType(ContentType.JSON).
                                   body(spartan).
                              when().
                                    post("/spartans");

            assertEquals(201, response.getStatusCode(), "Status code is wrong!");
            assertEquals("application/json", response.getContentType(), "Content type is invalid!");
            assertEquals(response.jsonPath().getString("success"), "A Spartan is Born!");

            response.prettyPrint();
        }

    }

