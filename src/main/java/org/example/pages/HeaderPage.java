package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderPage extends BasePage {

    private final static String BURGER_MENU_LOCATOR = "//nav[@class='bm-item-list']";

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    private WebElement cartLink;

    @FindBy(xpath = BURGER_MENU_LOCATOR)
    private WebElement burgerMenu;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(xpath = "//div[@class='bm-menu']//a[text()='Logout']")
    private WebElement burgerMenuLogoutItem;

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openCart() {
        cartLink.click();
    }

    public void openBurgerMenu() {
        burgerMenuButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BURGER_MENU_LOCATOR)));
    }

    public void logout() {
        burgerMenuLogoutItem.click();
    }
}
