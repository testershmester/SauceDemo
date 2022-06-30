package org.example;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTest extends BaseTest {

    @Test
    public void productsShouldBeAddedToCart() {
        loginPage.open();
        loginPage.loginAsStandardUser();
        String firstProduct = "Sauce Labs Backpack";
        String secondProduct = "Sauce Labs Bolt T-Shirt";
        productsPage.addToCart(firstProduct);
        productsPage.addToCart(secondProduct);
        headerPage.openCart();
        cartPage.removeProductFromCart(secondProduct);
        List<WebElement> allProductsInCartAfterRemove = cartPage.getAllProductsInCart();

        Assert.assertEquals(allProductsInCartAfterRemove.size(), 1,
                "Only one product should be in the cart after removing");
        Assert.assertEquals(allProductsInCartAfterRemove.get(0).getText(), firstProduct,
                "\"Sauce Labs Backpack\" product should be in the cart after removing");
    }

    @Test
    public void logoutInBurgerMenuShouldLogoutUser() {
        loginPage.open();
        loginPage.loginAsStandardUser();
        headerPage.openBurgerMenu();
        headerPage.logout();
    }
}
