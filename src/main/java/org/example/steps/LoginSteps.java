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
        String userName = System.getenv("username_var");
        String password = System.getenv("password_var");
        log.info("User is {}, password is {}", userName, password);
        login(userName, password);
    }

    public void loginAsDefaultUser() {
        login(System.getenv("username_var"), System.getenv("password_var"));
    }
}
