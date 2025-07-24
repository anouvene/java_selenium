package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageObjectsModel.MainPage;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URISyntaxException;

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
        WebElement username = mp.findUsername();
        username.click();
        username.sendKeys("udemy");

        // Password
        WebElement password = mp.findPassword();
        password.click();
        password.sendKeys("Udemy");

        // Comments
        WebElement comments = mp.findComments();
        comments.click();
        comments.clear();
        comments.sendKeys("Form subscription testing");

        // FileName
        WebElement filename = mp.findFilename();
        try {
            String imagePath = MainPage.getAbsolutePath("images/dog.jpg");
            filename.sendKeys(imagePath);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Checkbox
        //WebElement checkboxe3 = driver.findElement(By.cssSelector("input[name='checkboxes[]'][value='cb3']"));
        //WebElement checkboxe3 = driver.findElement(By.xpath("//input[@name='checkboxes[]' and @value='cb3']"));
        mp.findInput("cb3").click();
        mp.findInput("cb2").click();

        // Radio
        mp.findInput("rd1").click();

        // Select multiple
        mp.findOption("ms1").click();
        mp.findOption("ms2").click();
        mp.findOption("ms4").click(); // Désactiver option 4

        // Select simple : dropdown et click sur option 5
        // Optionnel: pas besoin de dérouler le sélect
        // WebElement dropdown = driver.findElement(By.cssSelector("select[name='dropdown']"));
        // dropdown.click();

        mp.findOption("dd5").click();

        // Bouton submit :
        mp.findInput("submit").click();

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
