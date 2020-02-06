package RestPractice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class SpartanRest_Weekend {


    private String baseURI= "http://54.152.50.187:8000/api";

    @Test
    public void test1(){
        Response response=
        //Request specification
    given().
            accept(ContentType.JSON).
    when().
       //Actual request is being sent HTTP verbs methods with URL
            get(baseURI+"/spartans/6");
       //eventually it will return a Response object
        response.prettyPrint();
        assertEquals("Male", response.path("gender").toString());
    }

    @Test
    public void Search_By_Providing_Query_Parameter(){
        Response response=
                given().
                        accept(ContentType.JSON).
                        queryParam("gender", "Male").
                        //  param("gender", "Male").
                                when().
                        get(baseURI+"/spartans/search");
        assertEquals(200, response.statusCode());
        assertFalse(response.asString().contains("Female"));
        response.prettyPrint();

        System.out.println(response.path("pageable.sort.empty").toString());
        //json path -->> just like xpath, it is for finding elements in json object/ document
        boolean isEmpty=  response.jsonPath().getBoolean("pageable.sort.empty");
        assertTrue(isEmpty);

        int totalElements= response.jsonPath().getInt("totalElements");
        System.out.println("totalElements is: "+ totalElements);

        //find out first guys phone number

    }

    @Test
    public void Search_By_Providing_JsonPath_Practice_For_Array(){
        Response response=
                given().
                        accept(ContentType.JSON).
                        queryParam("gender", "Male").
                        //  param("gender", "Male").
                                when().
                        get(baseURI+"/spartans/search");
        assertEquals(200, response.statusCode());
        assertFalse(response.asString().contains("Female"));
        //response.prettyPrint();

        long firstPhone=response.jsonPath().getLong("content[0].phone");
        System.out.println("first guy phone is "+firstPhone);
        //JsonPath for content return a json array
        //in order to get single json object we would use content[indexnumber]
        //in order to get single field in that json object : content[indexnumber].fieldname
        //for example content[1].phone--> second items phone number
        //if we want to store entire phone as a List
        //we can use getList methods with JsonPath by taking out index
        //content.phone
      List<Long> phoneList= response.jsonPath().getList("content.phone");
        System.out.println(phoneList);

        List<String> nameList= response.jsonPath().getList("content.name");
        System.out.println(nameList);
    }

    //get single Spartan as json response by calling /api/spartans{id}
    //store it inside a MAp of String and Object
    // do some assertion expected value you already set

    @Test
    public void Single_Spartan_Map_Test(){

        Response response= given().pathParam("my_id", 3).get(baseURI+"/spartans/{my_id}");
        response.prettyPrint();
        Map<String, Object> myJsonMap= response.jsonPath().getMap("");
        System.out.println(myJsonMap.get("name"));
        System.out.println(myJsonMap.get("gender"));
        System.out.println(myJsonMap.get("phone"));

    }

    @Test
    public void All_Spartan_Map_Test(){

        Response response= get(baseURI+"/spartans");
        List<Map<String, Object>> allSpartans=  response.jsonPath().getList("");
        System.out.println(allSpartans);
        for (Map<String, Object> each : allSpartans){
            System.out.println(each);
        }
    }

    @Test
    public void Search_All_spartan_Map_Test(){
        Response response=
                given().
                        accept(ContentType.JSON).
                        queryParam("gender", "Male").
                when().
                        get(baseURI+"/spartans/search");

        List<Map<String, Object>> allSpartans= response.jsonPath().getList("content");
        for (Map<String, Object> each : allSpartans){
            System.out.println(each);
        }
    }



    }
