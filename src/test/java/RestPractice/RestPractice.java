package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class RestPractice {

    //http://54.152.50.187:8000/api/hello

//    @BeforeClass
//    public static void setup(){
//        baseURI="http://54.152.50.187";
//        port=8000;
//        basePath="/api";
//        //above will generate a BASE REQUEST URL OF http://54.152.50.187:8000/api
//    }

    private String baseURI= "http://54.152.50.187:8000/api";
    @Test
    public void test(){
        Response result = RestAssured.get(baseURI+"/hello");
        //this code will give you status code
        System.out.println(result.statusCode());
        //this code will give you the body in String format
        System.out.println(result.asString());
        System.out.println(result.getBody().asString());
        System.out.println(result.body().asString());

        System.out.println(result.getHeader("content-type"));


    }
    @Test
    public void HEllo_Endpoint_Test(){

        Response result = RestAssured.get(baseURI+"/hello");
        assertEquals(200, result.statusCode());
        assertEquals("Hello from Sparta", result.asString());
        //rest assured library provide multiple method with same meaning
        //like Header= header, getContentTpe= contenType, statusCode= getStatusCode
        assertEquals("text/plain;charset=UTF-8", result.header("content-type"));

    }

    @Test
    public void Hello_Endpoint_HeaderContains_Test(){

        //1st approach to test header exists is by checking the value is null or not

        Response response= RestAssured.get(baseURI+"/hello");
        String headerValue=response.getHeader("Date");
        System.out.println(headerValue);
        assertNotNull(headerValue);

        //2nd approach : use existing method
       boolean dateHeaderExists= response.getHeaders().hasHeaderWithName("Date");
       assertTrue(dateHeaderExists);

    }
}
