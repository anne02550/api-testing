package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetUserDetailByEmailTest extends BaseTest {
    @Test
    @DisplayName("GET user details by email, expect response code 200")
    void getUserDetailByEmailReturn200() {
        Response response = given()
                .queryParam("email", Config.getEmail())
                .when()
                .get("/getUserDetailByEmail");

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        int responseCode = response.jsonPath().get("responseCode");
        assertThat(responseCode, equalTo(200));

        String userEmail = response.jsonPath().getString("user.email");
        int userId = response.jsonPath().getInt("user.id");
        assertThat(userEmail, equalTo(Config.getEmail()) );
        assertThat(userId, greaterThan(0));
    }

    @Test
    @DisplayName("GET user details with no email, expect code 400 ")
    void getUserDetailByEmailReturn400() {
        Response response = given()
                .when()
                .get("/getUserDetailByEmail");
        int responseCode = response.jsonPath().get("responseCode");
        assertThat(responseCode, equalTo(400));

    }
}
