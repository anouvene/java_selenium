package org.example.pageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepThreePage {
    @FindBy(tagName = "h2")
    WebElement completeHeader;

    private WebDriver driver;
    public CheckoutStepThreePage(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(d, this);
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getCompleteHeader() {
        return completeHeader.getText();
    }
}
