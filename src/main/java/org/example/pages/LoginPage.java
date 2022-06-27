package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    private final By userNameInput = By.id("user-name");

    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By error = By.cssSelector("h3[data-test=error]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String user, String password) {
        driver.findElement(userNameInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).submit();
    }

    public String getError() {
        return driver.findElement(error).getText();
    }
}
