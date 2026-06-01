package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class VerifyLoginTest extends BaseTest {

    @Test
    @DisplayName(" POST valid email and password, return 200")
    void verifyLoginReturn200() {
        Response response = given()
                .formParam("email", Config.getEmail())
                .formParam("password", Config.getPassword())
                .when()
                .post("/verifyLogin");
        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");
        assertThat(responseCode, equalTo(200));
        assertThat(message, equalTo("User exists!"));
    }

    @Test
    @DisplayName("POST wrong password return 404")
    void verifyLoginWrongPassword() {
        Response response = given()
                .formParam("email", Config.getEmail())
                .formParam("password", "pass")
                .when()
                .post("/verifyLogin");
        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");
        assertThat(responseCode, equalTo(404));
        assertThat(message, equalTo("User not found!"));
    }

    @Test
    @DisplayName("POST empty email and password return 400")
    void verifyLoginEmptyEmailAndPassword() {
        Response response =
                given()
                .when()
                .post("/verifyLogin");
        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");

        assertThat(responseCode, equalTo(400));
        assertThat(message, equalTo("Bad request, email or password parameter is missing in POST request."));
    }


}
