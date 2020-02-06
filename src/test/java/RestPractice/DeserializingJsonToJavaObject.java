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

        //First way
        Spartan sp1=get(baseURI+"/spartans/100").prettyPeek().
                jsonPath().
                getObject("", Spartan.class)
                ;
        System.out.println(sp1);

        //Second Way
        Spartan sp2= get(baseURI+"/spartans/15").prettyPeek().as(Spartan.class);
        System.out.println(sp2);

    }

}
