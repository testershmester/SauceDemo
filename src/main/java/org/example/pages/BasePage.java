package org.example.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
