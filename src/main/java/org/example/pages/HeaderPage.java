package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderPage extends BasePage {

    private final By CART_LINK = By.xpath("//div[@id='shopping_cart_container']/a");
    private final By BURGER_MENU = By.id("react-burger-menu-btn");
    private final By BURGER_MENU_ITEM_LIST = By.xpath("//nav[@class='bm-item-list']");
    private final By BURGER_MENU_LOGOUT = By.xpath("//div[@class='bm-menu']//a[text()='Logout']");


    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        driver.findElement(CART_LINK).click();
    }

    public void openBurgerMenu() {
        driver.findElement(BURGER_MENU).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(BURGER_MENU_ITEM_LIST));
    }

    public void logout() {
        driver.findElement(BURGER_MENU_LOGOUT).click();
    }
}
