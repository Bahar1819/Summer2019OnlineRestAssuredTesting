package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HR_ORDS_API_Test {

    String baseURI= "http://54.152.50.187:1000/ords/hr/";

    @Test
    public void test1(){
        Response response= get(baseURI+"regions");
        response.prettyPrint();
    }

    @AfterClass
    public void tearDown(){
        //this will reset all the set up we made to void accidental collusion between different test classes
        RestAssured.reset();
    }

    @Test
    public void test_all_regions(){
        Response response= get(baseURI+"regions");

        String first_regionName= response.jsonPath().getString("items[0].region_name");
        System.out.println(first_regionName);

        List<String> all_regionName= response.jsonPath().getList("items.region_name");

        System.out.println(all_regionName);

        String all_regionLinks= response.jsonPath().getString("items[1].links[0].href");
        System.out.println(all_regionLinks);

        List<String> all_regionlinksList= response.jsonPath().getList("items.links.href");
        System.out.println(all_regionlinksList);

        String lastLinksRel= response.jsonPath().getString("links[3].rel");
        assertEquals("first", lastLinksRel);
        System.out.println(lastLinksRel);
        assertEquals(200, response.statusCode());
    }

    @Test
    public void test_single_Region(){

        Response response= given().pathParam("my_id", 1).get(baseURI+"regions/{my_id}");
        response.prettyPrint();

        Map<String, Object> myJsonMap= response.jsonPath().getMap("");
        System.out.println(myJsonMap.get("region_name"));
        System.out.println(myJsonMap.get("links"));
    }





}
