package RestPractice;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class Spartan_Rest_API_sunday {

    private String baseURI= "http://54.152.50.187:8000/api";

    @Test
    public void All_Spartan_With_Size_And_Items_Test(){

        given()
                .accept(ContentType.JSON).
        when()
                .get(baseURI+"/spartans")
                 .prettyPeek().
        then()
                 .statusCode(200)
                .body("[0].name",equalTo("Ata"))
                //.body("name", hasSize(104))
                .body("[1].gender", endsWithIgnoringCase("male"))
                .header("Transfer-Encoding", "chunked")
                .header("Date", notNullValue())
                ;
    }

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

}
