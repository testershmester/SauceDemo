package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement title;
    @FindBy(id = "checkout")
    private WebElement checkout;
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;
    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> allProductsInCart;

    private final String productInCart = "//div[text()='%s']//ancestor::div[@class='cart_item']";
    private final String removeButton = "//div[text()='%s']//ancestor::div[@class='cart_item']//button[text()='Remove']";
    private final String product = "//div[text()='%s']//ancestor::div[@class='cart_item']";

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(baseUrl + "cart.html");
    }

    public List<WebElement> getAllProductsInCart() {
        return allProductsInCart;
    }

    public void checkout() {
        checkout.click();
    }

    @Step("Remove product {productName} from cart")
    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(removeButton, productName))).click();
    }

    public WebElement getProduct(String productName) {
        By fullLocator = By.xpath(String.format(product, productName));
        return driver.findElement(fullLocator);
    }

    public WebElement getTitle() {
        return title;
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }
}
