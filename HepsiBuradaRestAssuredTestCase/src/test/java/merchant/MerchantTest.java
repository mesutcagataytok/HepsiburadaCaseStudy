package merchant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.Data;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class MerchantTest {

    private static String requestBody = "{\n" +
            "  \"id\": 4,\n" +
            "  \"name\": \"string\",\n" +
            "  \"price\": 12.3,\n" +
            "  \"stock\": 3\n" +
            "}";

    @BeforeClass
    public void setupURI(){
        baseURI = "https://cagataymerchant.mocklab.io";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void checkGetAllGrocery(){
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/allGrocery");
        ResponseBody body = response.getBody();
        Data results = body.as(Data.class);
        Assert.assertEquals(results.data.size(),2);
    }

    @Test
    public  void checkGetGroceryByName(){
        Response response = given()
                .contentType(ContentType.JSON)
                .param("name", "apple")
                .when()
                .get("/allGrocery")
                .then()
                .extract().response();
        ResponseBody body = response.getBody();
        Data results = body.as(Data.class);
        Assert.assertEquals(results.data.get(0).name, "apple");
    }

    @Test
    public void checkAddProductToGrocery(){
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/add")
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertTrue(response.jsonPath().get("success"));
    }
}
