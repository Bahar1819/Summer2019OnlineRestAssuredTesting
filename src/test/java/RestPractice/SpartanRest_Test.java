package RestPractice;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanRest_Test {

    private String baseURI= "http://54.152.50.187:8000/api";


    @Test
    public void Spartan_All_Test(){
        //Response response= RestAssured.get("/spartans/")
        Response response= get(baseURI+"/spartans/");
        assertEquals(200, response.statusCode());
        //below codes are doing same exact thing
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        assertEquals("application/json;charset=UTF-8", response.getHeader("content-type"));

        boolean hasDateHeader= response.getHeaders().hasHeaderWithName("Date");
        assertTrue(hasDateHeader);

    }

    @Test
    public void SingleSpartanData_Test(){

        Response response= get(baseURI+"/spartans/15");
        System.out.println(response.asString());
        System.out.println(response.body().asString());
        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        assertTrue(response.asString().contains("Meta"));
    }



}
