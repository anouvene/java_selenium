package org.example.sauce_demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pageObjectsModel.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class CartTest {
    private static ChromeDriver driver;
    private static CartPage cartPage;
    private static HeaderPage headerPage;
    private static LoginPage lp;
    private static ProductPage pp;
    private static CheckoutStepOnePage checkoutStepOnePage;

    @BeforeAll
    public static void setUp() {
        driver = new DriverPage().getDriver();
        cartPage = new CartPage(driver);
        headerPage = new HeaderPage(driver);

        lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");

        pp = new ProductPage(driver);
        pp.addToCard0Click();

        checkoutStepOnePage = new CheckoutStepOnePage(driver);
    }
    @Test
    public void t001_cartCheck() {
        headerPage.cardBadgeClick();
        assertTrue(cartPage.getURL().startsWith("https://www.saucedemo.com/cart"));
        assertEquals(ProductPage.inventoryItemName, cartPage.getCartItemName());
    }

    @Test
    public void t002_checoutButtonClick() {
        cartPage.checkoutClick();
        assertTrue(checkoutStepOnePage.getURL().startsWith("https://www.saucedemo.com/checkout-step-one"));
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("End of cart test");
        driver.close();
    }
}
