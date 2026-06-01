package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateAccountTest extends BaseTest {
    @Test
    @DisplayName("update account return 200")
    void updateAccount() {
        Response response = given()
                .formParam("email", Config.getEmail())
                .formParam("password", Config.getPassword())
                .formParam("name", Config.getName())
                .formParam("title", Config.getTitle())
                .formParam("birth_date", Config.getBirthDay())
                .formParam("birth_month", Config.getBirthMonth())
                .formParam("birth_year", Config.getBirthYear())
                .formParam("firstname", Config.getFirstName())
                .formParam("lastname", Config.getLastName())
                .formParam("company", Config.getCompany())
                .formParam("address1", Config.getAddress1())
                .formParam("address2", Config.getAddress2())
                .formParam("country", Config.getCountry())
                .formParam("state", Config.getState())
                .formParam("city", Config.getCity())
                .formParam("zipcode", Config.getZipcode())
                .when()
                .put("/updateAccount");
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");

        assertThat(responseCode, equalTo(200));
        assertThat(message, equalTo("User updated!"));
    }

    @Test
    @DisplayName("PUT request with no details, return 400")
    void updateAccountWithNoParamsShouldReturn400() {
        Response response = given()
                .when()
                .put("/updateAccount");
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.getBody().asString());

        int responseCode = response.jsonPath().get("responseCode");
        String message = response.jsonPath().get("message");

        assertThat(responseCode, equalTo(400));
        assertThat(message, equalTo("Bad request, email parameter is missing in PUT request."));

    }
}
