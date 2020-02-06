package RestPractice;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class SendingPostRequest {

    private String baseURI= "http://54.152.50.187:8000/api";

    @Test
    public void Single_Spartan_LoggingAll_Details_Test(){

        given()
                .pathParam("my_id", 3)
                .log().all().//we can put log().all() to see all request information in console
                when()
                .get(baseURI+"/spartans/{my_id}").prettyPeek().
                then()
                //we can put log().all() to see all response information in console
                //there ae multiple option to see exactly when we want to see the log
                //in below example we only want to see the log if any validation fails
                .log().ifValidationFails()
                .statusCode(200)

        ;

    }

    //Sending a post request
    @Test
    public void Add_NewSpartan_WithStringAsBody_Test(){
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"name\": \"Habib\",\n" +
                        "    \"phone\": \"35963258747\"\n" +
                        "}").
        when()
                .post(baseURI+"/spartans").prettyPeek().
        then()
                .log().all()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", equalToIgnoringCase("Habib"))
                .body("data.phone", hasToString("35963258747"))


                ;
    }

    @Test
    public void Add_NewSpartan_WithMapAsBody_Test(){

        Map<String, Object> bodyMap= new HashMap<>();
        bodyMap.put("name", "Eshref");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", "96542577851");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"name\": \"Habib\",\n" +
                        "    \"phone\": \"35963258747\"\n" +
                        "}").
                when()
                .post(baseURI+"/spartans").prettyPeek().
                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", equalToIgnoringCase("Habib"))
                .body("data.phone", hasToString("35963258747"))


        ;



    }
}
