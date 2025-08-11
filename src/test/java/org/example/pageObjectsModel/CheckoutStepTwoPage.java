package org.example.pageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {
    //@FindBy(id = "finish")
    @FindBy(xpath = "//button[contains(text(), 'Finish')]")
    WebElement btnFinish;

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    WebElement inventoryItemName;

    @FindBy(xpath = "//div[@data-test='inventory-item-price']")
    WebElement inventoryItemPrice;

    @FindBy(xpath = "//div[@data-test='tax-label']")
    WebElement taxLabel;

    @FindBy(xpath = "//div[@data-test='total-label']") // Ou bien //div[contains(@class, 'summary_total_label')]
    WebElement totalLabel;


    private WebDriver driver;
    public CheckoutStepTwoPage(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(d, this);
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public void finishButtonClick() {
        btnFinish.click();
    }

    public String getInventoryItemName() {
        return inventoryItemName.getText();
    }

    public float getInventoryItemPrice() {
        return Float.parseFloat(inventoryItemPrice.getText().substring(1));
    }

    public float getTaxLabel() {
        // regex [^\d.] : remplace tout par  "" sauf les chiffres et le point
        return Float.parseFloat(taxLabel.getText().replaceAll("[^\\d.]", ""));
    }

    public float getTotalLabel() {
        return Float.parseFloat(totalLabel.getText().replaceAll("[^\\d.]", ""));
    }
}
