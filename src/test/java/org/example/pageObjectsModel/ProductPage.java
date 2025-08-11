package org.example.pageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    public static String inventoryItemName;

    @FindBy(css = "option[value='lohi']")
    WebElement optionPriceLowHight;

    @FindBy(className = "inventory_item_name" )
    List<WebElement> nameElements;
    @FindBy(className = "inventory_item_price" )
    List<WebElement> priceElements;

    @FindBy(className = "btn_primary")
    List<WebElement> buttonAddToCardList;

    @FindBy(className = "btn_secondary")
    List<WebElement> buttonRemove;

    // @FindBy(xpath = "//span[@data-test='shopping-cart-badge']")
    // WebElement cardBadge;
    public ProductPage(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(d, this);
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public void setFilterToLoHi() {
        optionPriceLowHight.click();
    }
    public boolean sortedByPriceAsc() {
        try {
            // Récupérer tous les prix visibles
            List<Double> actualPrices = new ArrayList<>();
            for (WebElement priceElement : priceElements) {
                // String priceText = priceElement.getText().replace("$", "").trim();
                String priceText = priceElement.getText().replaceAll("\\D", "").trim();
                actualPrices.add(Double.parseDouble(priceText));
            }

            // Créer une copie et la trier pour comparer
            List<Double> sortedPrices = new ArrayList<>(actualPrices);
            Collections.sort(sortedPrices);

            if (actualPrices.equals(sortedPrices)) {
                System.out.println("✅ Les prix sont bien triés par ordre croissant.");
                return true;
            } else {
                System.out.println("❌ Les prix ne sont pas correctement triés !");
                System.out.println("Affiché : " + actualPrices);
                System.out.println("Attendu : " + sortedPrices);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean sortedByPriceAscBis() {
        try {
            // Récupérer tous les prix visibles
            List<Double> actualPrices = new ArrayList<>();
            for (WebElement priceElement : priceElements) {
                // String priceText = priceElement.getText().replace("$", "").trim();
                String priceText = priceElement.getText().replaceAll("\\D", "").trim();
                actualPrices.add(Double.parseDouble(priceText));
            }

            Iterator<Double> iter = actualPrices.iterator();
            Double currentPrice, previousPrice = iter.next();

            while(iter.hasNext()) {
                currentPrice = iter.next();
                if(previousPrice > currentPrice) return false;
                previousPrice = currentPrice;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void addToCard0Click() {
        buttonAddToCardList.get(0).click();
        inventoryItemName = nameElements.get(0).getText();

        //System.out.println(inventoryItemName);
    }
    public boolean isCard0CanBeRemoved() {
        return (buttonRemove.get(0).getText().equals("Remove"));
    }
}
