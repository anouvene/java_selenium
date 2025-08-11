package org.example.pageObjectsModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "user-name")
    WebElement usernameInput;
    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginSubmit;

    public LoginPage(ChromeDriver cd) {
        //this.driver = d;

        PageFactory.initElements(cd, this);
    }
    public void login(String user, String secret) {
        usernameInput.click();
        usernameInput.sendKeys(user);

        passwordInput.click();
        passwordInput.sendKeys(secret);

        loginSubmit.click();
    }
}
