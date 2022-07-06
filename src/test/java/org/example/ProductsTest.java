package org.example;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTest extends BaseTest {

    public static final String SAUCE_LABS_BACKPACK = "Sauce Labs Backpack";
    public static final String SAUCE_LABS_BOLT_T_SHIRT = "Sauce Labs Bolt T-Shirt";

    @Test
    public void productShouldBeAddedToCart() {
        loginSteps.loginAsDefaultUser();
        productsPage.addToCart(SAUCE_LABS_BACKPACK);
        headerPage.openCart();
        List<WebElement> allProductsInCart = cartPage.getAllProductsInCart();

        Assert.assertEquals(allProductsInCart.size(), 1,
                "One product should be in the cart");
        Assert.assertEquals(allProductsInCart.get(0).getText(), SAUCE_LABS_BACKPACK,
                "\"" + SAUCE_LABS_BACKPACK + "\" product should be in the cart");
    }

    @Test
    public void productShouldBeRemovedFromCart() {
        loginSteps.loginAsDefaultUser();
        productsPage.addToCart(SAUCE_LABS_BACKPACK, SAUCE_LABS_BOLT_T_SHIRT);
        headerPage.openCart();
        List<WebElement> allProductsInCartBeforeRemove = cartPage.getAllProductsInCart();
        Assert.assertEquals(allProductsInCartBeforeRemove.size(), 2,
                "Two products should be in the cart");

        cartPage.removeProductFromCart(SAUCE_LABS_BOLT_T_SHIRT);
        List<WebElement> allProductsInCartAfterRemove = cartPage.getAllProductsInCart();

        Assert.assertEquals(allProductsInCartAfterRemove.size(), 1,
                "Only one product should left in the cart after removing");
        Assert.assertEquals(allProductsInCartAfterRemove.get(0).getText(), SAUCE_LABS_BACKPACK,
                "\"" + SAUCE_LABS_BACKPACK + "\" product should be in the cart after removing");
    }
}
