package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final String productInCart = "//div[text()='%s']//ancestor::div[@class='cart_item']";
    private final String removeButton = "//div[text()='%s']//ancestor::div[@class='cart_item']//button[text()='Remove']";
    private final String product = "//div[text()='%s']//ancestor::div[@class='cart_item']";

    private final By ALL_PRODUCTS_IN_CART = By.xpath("//div[@class='inventory_item_name']");
    private final By CHECKOUT = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(baseUrl + "cart.html");
    }

    public List<WebElement> getAllProductsInCart() {
        return driver.findElements(ALL_PRODUCTS_IN_CART);
    }

    public void checkout() {
        driver.findElement(CHECKOUT).click();
    }

    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(removeButton, productName))).click();
    }

    public WebElement getProduct(String productName) {
        By fullLocator = By.xpath(String.format(product, productName));
        return driver.findElement(fullLocator);
    }
}
