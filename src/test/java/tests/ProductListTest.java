package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductListTest extends BaseTest {
    @Test
    @DisplayName("Check if the return status code is 200 - success")
    void getProductListReturn200() {
        Response response; // store everything that comeback, including status code, header, body
        response = given()
                .when()
                .get("/productsList");

        //Check status code
        assertThat(response.statusCode(), equalTo(200));

        //Check product field is not empty
        List<Object> products = response.jsonPath().getList("products");
        assertThat(products, notNullValue());
        assertThat(products, not(empty()));

        //Check if the return body has values -> first product name
        String firstName =
                response.jsonPath().get("products[0].name");
        System.out.println("first product name: " + firstName);
        assertThat(firstName, notNullValue());
    }


    @Test
    @DisplayName("Check if POST method return 405 - method not allow")
    void postProductListReturn405() {
        Response response =
        given()
                .when()
                .post("/productsList");

        System.out.println("status code: " + response.statusCode());
        System.out.println("response body: " + response.body().asString());

        //api flaw : should return 405 but returned 200 + error code + error messages inside the response body
        int  responseCode = response.jsonPath().get("responseCode");
        assertThat(responseCode, equalTo(405));
    }



}
