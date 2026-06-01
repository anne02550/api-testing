package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BrandListTest extends BaseTest {
    @Test
    @DisplayName("Get request return 200")
    void getRequestReturn200() {
        Response response;
        response = given()
                .when()
                .get("/brandsList");
        //check status code
        assertThat(response.statusCode(), equalTo(200));
        System.out.println("Response body: "  + response.getBody().asString());


        //check the return field is not empty
        List<Object> brands = response.jsonPath().getList("brands");
        assertThat(brands, notNullValue());
        assertThat(brands, not(empty()));

        //check if the return has values
        String firstBrand = response.jsonPath().get("brands[0].brand");
        System.out.println(firstBrand);
        assertThat(firstBrand, notNullValue());
    }

    @Test
    @DisplayName("PUT request return 405")
    void putRequestReturn405() {
        Response response =
                given()
                .when()
                .put("/brandsList");

        System.out.println("status code: " + response.statusCode());
        System.out.println("response body: " + response.body().asString());

        //api flaw : should return 405 but returned 200 + error code + error messages inside the response body
        int responseCode = response.jsonPath().get("responseCode");
        assertThat(responseCode, equalTo(405));
    }

}
