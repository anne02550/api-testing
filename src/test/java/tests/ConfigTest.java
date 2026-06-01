package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class ConfigTest {
    @Test
    @DisplayName("config should load base.uri")
    void configShouldLoadBaseUri(){
        String baseUri = Config.getBaseUri();
        System.out.println("baseUri: " + baseUri);
        assertThat(baseUri, notNullValue());
    }

    @Test
    @DisplayName("config should load user.email")
    void configShouldLoadEmail(){
        String userEmail = Config.getEmail();
        System.out.println("userEmail: " + userEmail);
    }

    @Test
    @DisplayName("config should load user.password")
    void configShouldLoadPassword(){
        String userPassword = Config.getPassword();
        System.out.println("userPassword: " + userPassword);
    }
}
