package org.example;

import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @Test
    public void productsShouldBeAddedToCart() {
        loginPage.open();
        loginPage.loginAsStandardUser();
        productsPage.addToCart("Sauce Labs Backpack");
    }
}
