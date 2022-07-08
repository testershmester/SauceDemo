package org.example;

import org.example.utils.PropertiesLoader;
import org.example.utils.testng.RetryAnalyzer;
import org.testng.annotations.*;

import java.util.Properties;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void userShouldLoginWithValidCredentials() {
        loginSteps.loginAsStandardUser();
        assertTrue(productsPage.getTitle().isDisplayed(), "User was not logged in");
    }

    @DataProvider(name = "Data provider for tests with different invalid users")
    public Object[][] loginData() {
        Properties properties = PropertiesLoader.loadProperties("locked_out_user.properties");
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "12345", "Epic sadface: Username is required"},
                {"standard_user", "12345", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user1", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {properties.getProperty("username"), properties.getProperty("password"),
                        "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "Data provider for tests with different invalid users")
    public void userShouldNotLoginWithInvalidData(String username, String password, String error) {
        loginSteps.login(username, password);
        assertEquals(loginPage.getError(), error, "The error is incorrect");
    }
}
