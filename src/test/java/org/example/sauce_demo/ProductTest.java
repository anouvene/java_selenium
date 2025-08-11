package org.example.sauce_demo;

import org.example.pageObjectsModel.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProductTest {
    private static ChromeDriver driver;
    private static HeaderPage hp;
    private static LoginPage lp;
    private static ProductPage pp;

    @BeforeAll
    public static void setUp() {
        driver = new DriverPage().getDriver();

        hp = new HeaderPage(driver);

        lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");

        pp = new ProductPage(driver);

    }
    @Test
    public void t001_filterByPriceAsc() {
        // Cliquer sur le filtre de tri "Prix : du plus bas au plus élevé"
        pp.setFilterToLoHi();

        // Attendre que le DOM se mette à jour en surveillant un changement dans les prix
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.inventory_item_price"), 0));

        //assertTrue(pp.sortedByPriceAsc());
        assertTrue(pp.sortedByPriceAscBis());
    }
    @Test
    public void t002_addToCard() {
        pp.addToCard0Click();

        assertTrue(pp.isCard0CanBeRemoved());
        assertEquals(1, hp.getCardBadgeQuantity());
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("End of product test");
        driver.close();
    }
}
