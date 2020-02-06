package RestPractice;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class HamCrestLibrary {

    //hamcrest is a testing library to provide many built in testing methods
    //that comes from hamcrest Matcher

    private String baseURI= "http://54.152.50.187:8000/api";

    @Test
    public void Calculation_Test(){
        int a =10, b=20;

        assertEquals(30, a+b);
        assertThat(30,equalTo(a+b));
        assertThat(20, greaterThan(a+b));
    }

    @Test
    public void DoingAssertionWithHamcrest_forSpartan(){

     // Response response= given().pathParam("my_id", 3).get("/spartans/{my_id}");

      given()
              .pathParam("my_id", 3).
      when()
              .get(baseURI+"/spartans/{my_id}").
      then()
              .assertThat()
              .statusCode(200)
              .contentType(ContentType.JSON)
              .body("id",equalTo(3))
              .body("gender", equalTo("Male"))
              .body("phone", hasToString("6105035231"));

    }

    @Test
    public void arrayTest(){

        int [] nums= {1,4,6,7,8};

        //assertThat(1, hasItemInArray(1));

    }




}
