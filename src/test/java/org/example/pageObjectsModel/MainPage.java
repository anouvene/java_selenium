package org.example.pageObjectsModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Page Objects :
 * Améliorer la qualité, la maintenabilité et la lisibilité des tests automatisés
 * en :
 * - encapsulant des éléments de la page web dans des objets spécifiques
 * - évitant la duplication de code par le biais des méthodes réutilisables.
 * - regroupant tous les éléments ciblés en un seul endroit
 *
 * Page Factory :
 * - Simplifier nos Pages Objects
 * - Utiliser les annotations de manière automatique
 * - Initialiser les WebElements de manière Lazy (Économie des ressources)
 */
public class MainPage {
    private FirefoxDriver driver;
    @FindBy(css = "input[name='username']")
    private WebElement usernameInput;
    @FindBy(css = "input[name='password']")
    private WebElement passwordInput;
    @FindBy(css = "textarea[name='comments']")
    private WebElement commentsTexarea;
    @FindBy(css = "input[name='filename']")
    private WebElement filenameInput;

    public MainPage(FirefoxDriver fd) {
        this.driver = fd;
        PageFactory.initElements(driver, this);
    }

    public static String getAbsolutePath(String resourcePath) throws URISyntaxException {
        ClassLoader classLoader = MainPage.class.getClassLoader();
        URL resource = classLoader.getResource(resourcePath);

        if(resource == null) {
            throw new IllegalArgumentException("File not found ! " + resourcePath);
        }

        return Paths.get(resource.toURI()).toFile().getAbsolutePath();
    }

    public WebElement findInput(String inputValue) {
        //return driver.findElement(By.cssSelector("input[value='" + inputValue + "']"));
        return driver.findElement(By.cssSelector(String.format("input[value='%s']", inputValue)));
    }

    public WebElement findOption(String optionValue) {
        return driver.findElement(By.cssSelector(String.format("option[value='%s']", optionValue)));
    }

    public void setUsernameInput(String username) {
        usernameInput.click();
        usernameInput.sendKeys(username);
    }
    public void setPasswordInput(String password) {
        passwordInput.click();
        passwordInput.sendKeys(password);
    }

    public void setCommentsTexarea(String comments) {
        commentsTexarea.click();
        commentsTexarea.clear();
        commentsTexarea.sendKeys(comments);
    }

    public void upload(String pathToFile) {
        try {
            String imagePath = MainPage.getAbsolutePath(pathToFile);
            filenameInput.sendKeys(imagePath);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    // Actions sur les checkboxes, options, submit
    public void clickInput(String inputValue) {
        findInput(inputValue).click();
    }

    public void clickOption(String optionValue) {
        findOption(optionValue).click();
    }

}
