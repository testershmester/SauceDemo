package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    @Test
    public void logoutInBurgerMenuShouldLogoutUser() {
        System.out.println(Thread.currentThread().getId());
        loginSteps.loginAsDefaultUser();
        headerPage.openBurgerMenu();
        headerPage.logout();
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
        assertTrue(loginButton.isDisplayed(), "User was not logged out");
    }
}
