package org.example.pageObjectsModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Page object
 * Améliorer la qualité, la maintenabilité et la lisibilité des tests automatisés
 * en :
 * - encapsulant des éléments de la page web dans des objets spécifiques
 * - évitant la duplication de code par le biais des méthodes réutilisables.
 * - regroupant tous les éléments ciblés en un seul endroit
 */
public class MainPage {
    private FirefoxDriver driver;
    public MainPage(FirefoxDriver fd) {
        this.driver = fd;
    }

    public static String getAbsolutePath(String resourcePath) throws URISyntaxException {
        ClassLoader classLoader = MainPage.class.getClassLoader();
        URL resource = classLoader.getResource(resourcePath);

        if(resource == null) {
            throw new IllegalArgumentException("File not found ! " + resourcePath);
        }

        return Paths.get(resource.toURI()).toFile().getAbsolutePath();
    }

    public WebElement findUsername() {
        return driver.findElement(By.cssSelector("input[name='username']"));
    }
    public WebElement findPassword() {
        return driver.findElement(By.cssSelector("input[name='password']"));
    }
    public WebElement findComments() {
        return driver.findElement(By.cssSelector("textarea[name='comments']"));
    }
    public WebElement findFilename() {
        return driver.findElement(By.cssSelector("input[name='filename']"));
    }

    public WebElement findInput(String inputValue) {
        return driver.findElement(By.cssSelector("input[value='" + inputValue + "']"));
    }

    public WebElement findOption(String option) {
        return driver.findElement(By.cssSelector(String.format("option[value='%s']", option)));
    }


}
