package org.example.pageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage {
    @FindBy(id = "first-name")
    WebElement firstNameInput;
    @FindBy(id = "last-name")
    WebElement lastNameInput;
    @FindBy(id = "postal-code")
    WebElement postalCodeInput;
    @FindBy(id = "continue")
    WebElement btnContinue;

    private WebDriver driver;
    public CheckoutStepOnePage(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(d, this);
    }

    public String getURL() {
        return this.driver.getCurrentUrl();
    }

    public void fillInformations(String firstName, String lastName, String postalCode) {
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);
        lastNameInput.click();
        lastNameInput.sendKeys(lastName);
        postalCodeInput.click();
        postalCodeInput.sendKeys(postalCode);
    }

    public void continueButtonClick() {
        btnContinue.click();
    }


}
