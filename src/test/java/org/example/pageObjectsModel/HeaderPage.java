package org.example.pageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
    @FindBy(xpath = "//span[@data-test='shopping-cart-badge']")
    WebElement cardBadge;

    public HeaderPage(WebDriver d) {
        PageFactory.initElements(d, this);
    }
    public int getCardBadgeQuantity() {
        return Integer.parseInt(cardBadge.getText());
    }
    public void cardBadgeClick() {
        cardBadge.click();
    }
}
