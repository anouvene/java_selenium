package org.example.pageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;
    @FindBy(className = "inventory_item_name")
    private WebElement itemNameElement;

    @FindBy(id = "checkout")
    private WebElement btnCheckout;

    public CartPage(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(d,this);
    }

    public String getURL() {
        return this.driver.getCurrentUrl();
    }

    public String getCartItemName() {
        return itemNameElement.getText();
    }

    public void checkoutClick() {
        btnCheckout.click();
    }
}
