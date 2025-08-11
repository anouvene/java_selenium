package org.example.sauce_demo;

import org.example.pageObjectsModel.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class CartOrderTest {

    public static ChromeDriver driver;
    public static LoginPage loginPage;
    public static ProductPage productPage;
    private static HeaderPage headerPage;
    private static CartPage cartPage;
    private static CheckoutStepOnePage checkoutStepOnePage;
    private static CheckoutStepTwoPage checkoutStepTwoPage;
    private static CheckoutStepThreePage checkoutStepThreePage;

    @BeforeAll
    public static void setUp() {
        driver = new DriverPage().getDriver();

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);

        headerPage = new HeaderPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutStepThreePage = new CheckoutStepThreePage(driver);
    }
    @Test
    public void t001_loginAsUser() {
        loginPage.login("standard_user", "secret_sauce");

        // Assertion
        assertEquals("https://www.saucedemo.com/inventory.html", productPage.getURL());
        //assertTrue(productPage.getURL().contains("inventory.html"));
        assertTrue(productPage.getURL().startsWith("https://www.saucedemo.com/inventory"));
    }
    @Test
    public void t002_filterByPriceAsc() {
        // Cliquer sur le filtre de tri "Prix : du plus bas au plus élevé"
        productPage.setFilterToLoHi();

        // Attendre que le DOM se mette à jour en surveillant un changement dans les prix
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.inventory_item_price"), 0));

        //assertTrue(productPage.sortedByPriceAsc());
        assertTrue(productPage.sortedByPriceAscBis());
    }
    @Test
    public void t003_addToCard() {
        productPage.addToCard0Click();

        assertTrue(productPage.isCard0CanBeRemoved());
        assertEquals(1, headerPage.getCardBadgeQuantity());
    }
    @Test
    public void t004_cartCheck() {
        headerPage.cardBadgeClick();
        assertTrue(cartPage.getURL().startsWith("https://www.saucedemo.com/cart"));
        assertEquals(ProductPage.inventoryItemName, cartPage.getCartItemName());
    }
    @Test
    public void t005_checoutButtonClick() {
        cartPage.checkoutClick();
        assertTrue(checkoutStepOnePage.getURL().startsWith("https://www.saucedemo.com/checkout-step-one"));
    }
    @Test
    public void t006_fillInformationsInStepOne() {
        checkoutStepOnePage.fillInformations("Tom", "Oliver", "78120");
        checkoutStepOnePage.continueButtonClick();

        // URL ok
        assertTrue(checkoutStepTwoPage.getURL().startsWith("https://www.saucedemo.com/checkout-step-two"), "Wrong URL !");
    }
    @Test
    public void t007_verifyInformationsInStepTwo() {
        // Product name
        assertEquals(ProductPage.inventoryItemName, checkoutStepTwoPage.getInventoryItemName());

        // Total price
        assertEquals(checkoutStepTwoPage.getInventoryItemPrice() + checkoutStepTwoPage.getTaxLabel(), checkoutStepTwoPage.getTotalLabel());
    }
    @Test
    public void t008_stepThreeCheck() {
        checkoutStepTwoPage.finishButtonClick();

        assertTrue(checkoutStepThreePage.getURL().startsWith("https://www.saucedemo.com/checkout-complete"));
        assertTrue(checkoutStepThreePage.getCompleteHeader().contains("Thank you for your order!"));
    }
    @AfterAll
    public static void tearDown() {
        System.out.println("End of cart order test");
        //driver.quit();
        driver.close();
    }
}
