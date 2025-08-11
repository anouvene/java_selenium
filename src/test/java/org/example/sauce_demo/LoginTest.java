package org.example.sauce_demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.chrome.ChromeDriver;

import org.example.pageObjectsModel.DriverPage;
import org.example.pageObjectsModel.LoginPage;
import org.example.pageObjectsModel.ProductPage;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class LoginTest {

    public static ChromeDriver driver;

    public static LoginPage lp;
    public static ProductPage pp;

    @BeforeAll
    public static void setUp() {
        driver = new DriverPage().getDriver();

        lp = new LoginPage(driver);
        pp = new ProductPage(driver);
    }
    @Test
    public void t001_loginAsUser() {
        lp.login("standard_user", "secret_sauce");

        // Assertion
        assertEquals("https://www.saucedemo.com/inventory.html", pp.getURL());
        //assertTrue(pp.getURL().contains("inventory.html"));
        assertTrue(pp.getURL().startsWith("https://www.saucedemo.com/inventory"));
    }
    @AfterAll
    public static void tearDown() {
        System.out.println("End of login test");
        //driver.quit();
        driver.close();
    }
}
