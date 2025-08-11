package org.example.pageObjectsModel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverPage {
    private ChromeDriver driver;
    public DriverPage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Désactive le gestionnaire de mots de passe
        // Empêche Chrome de proposer d'enregistrer les mots de passe

        // options.addArguments("--disable-popup-blocking");
        // options.addArguments("--disable-search-engine-choice-screen");
        // options.addArguments("--disable-features=PasswordManagerEnabled,AutofillKeyBoardAccessoryView");
        // options.setExperimentalOption("prefs", Map.of(
        //         "credentials_enable_service", false,
        //         "profile.password_manager_enabled", false
        // ));

        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    public ChromeDriver getDriver() {
        return driver;
    }
}
