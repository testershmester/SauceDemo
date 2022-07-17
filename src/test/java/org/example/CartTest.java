package org.example;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Sauce demo")
@Feature("New card during checkout")
public class CartTest extends BaseTest {

    @BeforeClass
    public void beforeExample() {
        System.out.println("LoginTest.@BeforeClass");
    }

    @Issue("SHRL-18")
    @TmsLink("DEMO-1")
    @Link("https://stackoverflow.com/questions/72984709/loop-through-variable-in-python")
    @Test
    public void continueShoppingShouldReturnUserOnProductsPage() {
        System.out.println(Thread.currentThread().getId());
        loginSteps.loginAsDefaultUser();
        headerPage.openCart();
        cartPage.continueShopping();
        assertFalse(productsPage.getTitle().isDisplayed(), "The continue shopping button does not redirect to the product page");
    }

    @Story("Fast checkout")
    @Test(description = "Test NG description")
    @Description("Проверка перехода к чек ауту")
    public void checkoutShouldOpenCheckoutPage() {
        loginSteps.loginAsDefaultUser();
        headerPage.openCart();
        cartPage.checkout();
        Assert.fail("Fail for screenshot");
        assertEquals(checkoutPage.getTitle().getText(), "CHECKOUT: YOUR INFORMATION", "The checkout button does not open checkout page");
    }
}
