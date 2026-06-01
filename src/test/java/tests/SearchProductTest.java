package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchProductTest extends BaseTest {

    @Test
    @DisplayName("POST request return responseCode 200")
    void postRequestReturnResponseCode200() {
        Response response = given()
                .formParam("search_product", "top")
                .when()
                .post("/searchProduct");
        //print status code and response body
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        //check if response code =200
        int responseCode = response.jsonPath().get("responseCode");
        assertThat(responseCode, equalTo(200));

        //check the return field is not empty
        List<Object> products = response.jsonPath().getList("products");
        assertThat(products, notNullValue());
        assertThat(products, not(empty()));

        //check if the return has values
        String firstProductName = response.jsonPath().get("products[0].name");
        System.out.println(firstProductName);
        assertThat(firstProductName, notNullValue());

    }

    @Test
    @DisplayName("empty POST request return response code 400")
    void postRequestReturnResponseCode400() {
        Response response =
                given()
                        .when()
                        .post("/searchProduct");

        System.out.println("status code: " + response.statusCode());
        System.out.println("response body: " + response.body().asString());

        // missing required parameter returns 400 Bad Request
        int responseCode = response.jsonPath().get("responseCode");
        assertThat(responseCode, equalTo(400));
    }
}
