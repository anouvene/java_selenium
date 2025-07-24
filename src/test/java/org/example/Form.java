package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageObjectsModel.MainPage;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Form {
    public static FirefoxDriver driver;
    public static MainPage mp;
    @BeforeAll
    public static void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        mp = new MainPage(driver);
    }

    @Test
    public void test001_fillForm() {
        // Username
        mp.setUsernameInput("udemy");

        // Password
        mp.setPasswordInput("Udemy");

        // Comments
        mp.setCommentsTexarea("Form subscription testing");

        // FileName
       mp.upload("images/dog.jpg");

        // Checkbox
        mp.clickInput("cb3"); // Désactiver Checkbox 3
        mp.clickInput("cb2");

        // Radio
        mp.clickInput("rd1");

        // Select multiple
        mp.clickOption("ms1");
        mp.clickOption("ms2");
        mp.clickOption("ms4"); // Désactiver Selection item 4

        // Select simple : dropdown et click sur option 5
        mp.clickOption("dd5");

        // Bouton submit :
        mp.clickInput("submit");

    }
    @Test
    public void test002_checkFormFields() {
        // Username
        WebElement _chkUsername = driver.findElement(By.id("_valueusername"));
        assertEquals("udemy", _chkUsername.getText());

        // Password
        WebElement _chkPassword = driver.findElement(By.id("_valuepassword"));
        assertEquals("Udemy", _chkPassword.getText());

        // Comment
        WebElement _chkComments = driver.findElement(By.id("_valuecomments"));
        assertEquals("Form subscription testing", _chkComments.getText());

        // FileName
        WebElement _chkFilename = driver.findElement(By.id("_valuefilename"));
        assertEquals("dog.jpg", _chkFilename.getText());

        // Checkbox
        WebElement _chkCheckboxe = driver.findElement(By.id("_valuecheckboxes0"));
        assertEquals("cb2", _chkCheckboxe.getText());

        // Radio
        WebElement _chkRadio = driver.findElement(By.id("_valueradioval"));
        assertEquals("rd1", _chkRadio.getText());

        // Select multiple
        WebElement _chkSelect1 = driver.findElement(By.id("_valuemultipleselect0"));
        WebElement _chkSelect2 = driver.findElement(By.id("_valuemultipleselect1"));

        assertEquals("ms1", _chkSelect1.getText());
        assertEquals("ms2", _chkSelect2.getText());

        // Select simple : dropdown et click sur option 5
        WebElement _chkDropdown = driver.findElement(By.id("_valuedropdown"));
        assertEquals("dd5", _chkDropdown.getText());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
