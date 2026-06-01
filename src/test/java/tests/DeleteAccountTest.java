package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAccountTest extends BaseTest {
    private static final String TEST_PASSWORD = "password123";
    private final String testEmail = "testdelete" + System.currentTimeMillis() + "@test.com";

    @BeforeEach
    void createTestAccount() {
        given()
                .formParam("name", "Test User")
                .formParam("email", testEmail)
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
    @DisplayName("DELETE account returns 200")
    void deleteAccountShouldReturn200() {
        Response response = given()
                .formParam("email", testEmail)
                .formParam("password", TEST_PASSWORD)
                .when()
                .delete("/deleteAccount");

        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");

        assertThat(responseCode, equalTo(200));
        assertThat(message, equalTo("Account deleted!"));
    }

    @Test
    @DisplayName("DELETE with no params returns 400")
    void deleteAccountWithNoParamsShouldReturn400() {
        Response response = given()
                .when()
                .delete("/deleteAccount");

        int responseCode = response.jsonPath().get("responseCode");
        assertThat(responseCode, equalTo(400));
    }
}
