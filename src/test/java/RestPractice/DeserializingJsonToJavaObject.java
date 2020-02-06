package RestPractice;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class DeserializingJsonToJavaObject {

    private String baseURI= "http://54.152.50.187:8000/api";


    @Test
    public void DeserializeAnJsonToObject_Test(){

        Spartan sp1=get(baseURI+"/spartans/100").
                jsonPath().
                getObject("", Spartan.class)
                ;
        System.out.println(sp1);





    }

}
