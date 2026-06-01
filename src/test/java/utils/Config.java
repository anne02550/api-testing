package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    //Properties: build in java class for reading .properties file : key: value

    // properties -> hold all key-value pairs from config.properties file
    private static final Properties properties = new Properties();

    //run when the class is used to find and read config.properties file
    static {
        try {
            InputStream input = Config.class.getClassLoader().getResourceAsStream("secrets/config.properties");
            properties.load(input);

        } catch (IOException e){
            throw new RuntimeException("could not load config.properties file", e);
        }
    }

    //return uri, email, password from config file
    public static String getBaseUri(){
        return properties.getProperty("base.uri");

    }

    public static String getEmail(){
        return properties.getProperty("user.email");

    }
    public static String getPassword(){
        return properties.getProperty("user.password");

    }

    public static String getName() {
        return properties.getProperty("user.name");
    }

    public static String getTitle() {
        return properties.getProperty("user.title");
    }

    public static String getBirthDay() {
        return properties.getProperty("user.birth_day");
    }

    public static String getBirthMonth() {
        return properties.getProperty("user.birth_month");
    }

    public static String getBirthYear() {
        return properties.getProperty("user.birth_year");
    }

    public static String getFirstName() {
        return properties.getProperty("user.first_name");
    }

    public static String getLastName() {
        return properties.getProperty("user.last_name");
    }

    public static String getCompany() {
        return properties.getProperty("user.company");
    }

    public static String getAddress1() {
        return properties.getProperty("user.address1");
    }

    public static String getAddress2() {
        return properties.getProperty("user.address2");
    }

    public static String getCountry() {
        return properties.getProperty("user.country");
    }

    public static String getState() {
        return properties.getProperty("user.state");
    }

    public static String getCity() {
        return properties.getProperty("user.city");
    }

    public static String getZipcode() {
        return properties.getProperty("user.zipcode");
    }

    public static String getMobile() {
        return properties.getProperty("user.mobile");
    }


}
