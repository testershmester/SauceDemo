package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.*;
import org.example.steps.LoginSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    ProductsPage productsPage;
    HeaderPage headerPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;
    LoginSteps loginSteps;

    @Parameters("browser")
    @BeforeMethod()
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
        if (browser.equals("chrome")) {
            //Initialize web driver and create driver instance
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            driver = new ChromeDriver(options);
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Set up driver settings
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //Set driver into test context
        iTestContext.setAttribute("driver", driver);
        //Pages
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);
        //Steps
        loginSteps = new LoginSteps(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
