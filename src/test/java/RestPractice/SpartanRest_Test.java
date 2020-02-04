package RestPractice;

import io.restassured.http.ContentType;
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

    // Given no header is provided
    // When User send request to /api/spartans/20000
    // Then Response status code should be 404
    // and header should have content Type /JSON
    // and response payload should contains "spartans Not Found"

    @Test
    public void Invalid_Spartan_ID_should_return_404_Test(){
        Response response=
                given().
                        pathParam("id", 20000).
                when().
                        get(baseURI+"/spartans/{id}");
        response.prettyPrint();
        assertEquals(404, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        assertTrue(response.asString().contains("Not Found"));
    }

    // Given Accept header is provided as JSON
    // When User send request to /api/spartans/2
    // Then Response status code should be 200
    // and header should have content Type /JSON
    // and json object id should be 2

    @Test
    public void SingleSpartanDataWihHeader_Test(){

        //Request specification object hold the information about the request
        //like header, path variable, query parameter, body
        //Response is the object to store Response data

        Response response=
                given().
                       // header("accept","application/json").
                       //  accept("application/json").
                       accept(ContentType.JSON).
                when().
                        get(baseURI+"/spartans/13");
        assertEquals("application/json;charset=UTF-8",response.contentType());

    }

    // Given Accept header is provided as XML
    // When User send request to /api/spartans/2
    // Then Response status code should be 406
    // and header should have content Type /JSON
    // and json object id should be 2

    @Test
    public void SingleSpartanDataWihHeader_XMLstatus_code_406_Test(){

        //Request specification object hold the information about the request
        //like header, path variable, query parameter, body
        //Response is the object to store Response data

        Response response=
                given().
                        // header("accept","application/json").
                        //  accept("application/json").
                                accept(ContentType.XML).
                        when().
                        get(baseURI+"/spartans/13");
        assertEquals(406, response.statusCode());
        System.out.println(response.statusLine());




    }



}
