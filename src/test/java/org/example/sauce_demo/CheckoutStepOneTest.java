package org.example.sauce_demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pageObjectsModel.*;

public class CheckoutStepOneTest {
    private static ChromeDriver driver;
    private static HeaderPage headerPage;
    private static LoginPage loginPage;
    private static ProductPage productPage;
    private static CartPage cartPage;
    private static CheckoutStepOnePage checkoutStepOnePage;
    private static CheckoutStepTwoPage checkoutStepTwoPage;

    @BeforeAll
    public static void setUp(){
        driver = new DriverPage().getDriver();

        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        productPage = new ProductPage(driver);
        productPage.setFilterToLoHi();
        productPage.sortedByPriceAsc();
        productPage.addToCard0Click();

        headerPage = new HeaderPage(driver);
        headerPage.cardBadgeClick();

        cartPage = new CartPage(driver);
        cartPage.checkoutClick();

        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
    }

    @Test
    public void t001_fillInformations() {
        checkoutStepOnePage.fillInformations("Olive", "Stone", "78120");
        checkoutStepOnePage.continueButtonClick();

        assertTrue(checkoutStepTwoPage.getURL().startsWith("https://www.saucedemo.com/checkout-step-two"));
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("End of checkout step one test");
        driver.close();
    }
}
