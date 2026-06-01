package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateAccountTest extends BaseTest {
    private static final String TEST_EMAIL = "testuser" + System.currentTimeMillis() + "@test.com";
    private static final String TEST_PASSWORD = "password123";

    private Response createAccount(String email) {
        return given()
                .formParam("name", "Test User")
                .formParam("email", email)
                .formParam("password", TEST_PASSWORD)
                .formParam("title", "Mr")
                .formParam("birth_date", "1")
                .formParam("birth_month", "1")
                .formParam("birth_year", "1990")
                .formParam("firstname", "Test")
                .formParam("lastname", "User")
                .formParam("company", "Test Company")
                .formParam("address1", "123 Test Street")
                .formParam("address2", "")
                .formParam("country", "United Kingdom")
                .formParam("state", "Surrey")
                .formParam("city", "London")
                .formParam("zipcode", "SW1A1AA")
                .formParam("mobile_number", "12345678900")
                .when()
                .post("/createAccount");
    }
    @Test
    @DisplayName("creat account return 201 = created")
    void createAccountShouldReturn201() {
        Response response = createAccount(TEST_EMAIL);

        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");

        assertThat(responseCode, equalTo(201));
        assertThat(message, equalTo("User created!"));
    }

    @Test
    @DisplayName("create account with existing email should return 400")
    void createAccountWithExistingEmailShouldReturn400() {
        createAccount(TEST_EMAIL); // first call creates it
        Response response = createAccount(TEST_EMAIL); // second call should fail

        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");

        assertThat(responseCode, equalTo(400));
        assertThat(message, equalTo("Email already exists!"));
    }

    @Test
    @DisplayName("POST with no params returns 400")
    void createAccountWithNoParamsShouldReturn400() {
        Response response = given()
                .when()
                .post("/createAccount");

        int responseCode = response.jsonPath().get("responseCode");
        assertThat("Expected 400 for missing params", responseCode, equalTo(400));
    }
}
