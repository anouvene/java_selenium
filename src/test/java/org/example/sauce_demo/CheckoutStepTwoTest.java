package org.example.sauce_demo;

import org.example.pageObjectsModel.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutStepTwoTest {
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
    public void t001_verifyStepTwoInformations() {
        checkoutStepOnePage.fillInformations("Olive", "Stone", "78120");
        checkoutStepOnePage.continueButtonClick();

        // URL ok
        assertTrue(checkoutStepTwoPage.getURL().startsWith("https://www.saucedemo.com/checkout-step-two"), "Wrong URL !");

        // Product name
        assertEquals(ProductPage.inventoryItemName, checkoutStepTwoPage.getInventoryItemName());

        // Total price
        assertEquals(checkoutStepTwoPage.getInventoryItemPrice() + checkoutStepTwoPage.getTaxLabel(), checkoutStepTwoPage.getTotalLabel());
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("End of checkout step two test");
        driver.close();
    }
}
