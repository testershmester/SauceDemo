package org.example.steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.pages.LoginPage;
import org.example.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

@Log4j2
public class LoginSteps {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String STANDARD_USER_PROPERTIES = "standard_user.properties";

    WebDriver webDriver;
    LoginPage loginPage;

    public LoginSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        loginPage = new LoginPage(webDriver);
    }

    @Step("Login as {user} with {password}")
    public void login(String user, String password) {
        loginPage.open();
        loginPage.fillInUserName(user);
        loginPage.fillInPassword(password);
        loginPage.submitForm();
    }

    @Step("Login as standard user")
    public void loginAsStandardUser() {
        Properties properties = PropertiesLoader.loadProperties(STANDARD_USER_PROPERTIES);
        login(System.getProperty(USERNAME, properties.getProperty(USERNAME)),
              System.getProperty(PASSWORD, properties.getProperty(PASSWORD)));
        log.info("Password for standard user is {}", System.getProperty(PASSWORD, properties.getProperty(PASSWORD)));
    }

    public void loginAsDefaultUser() {
        Properties properties = PropertiesLoader.loadProperties();
        login(properties.getProperty(USERNAME), properties.getProperty(PASSWORD));
    }
}
