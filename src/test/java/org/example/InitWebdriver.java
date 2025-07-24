package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitWebdriver {
    @Test
    public void t001_initWebdriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://www.amazon.com");
    }



}
