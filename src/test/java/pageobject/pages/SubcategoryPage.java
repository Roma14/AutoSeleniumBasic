package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageobject.helpers.Locators;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryPage {
    private By nameButton = Locators.getLocator("SubcategoryPage.nameButton");
    private By priceButton = Locators.getLocator("SubcategoryPage.priceButton");
    private By rubberDuckButton = Locators.getLocator("SubcategoryPage.rubberDuckButton");
    private By subCategory = Locators.getLocator("SubcategoryPage.subCategory");

    private final WebDriver driver;

    public SubcategoryPage(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    public void moveToSubcategory() {
        WebElement button = driver.findElement(rubberDuckButton);
        Actions builder = new Actions(driver);
        builder.moveToElement(button).perform();
        driver.findElement(subCategory).click();
    }

    public String sortByName() {
        moveToSubcategory();
        driver.findElement(nameButton).click();

        List<WebElement> ducksByName = driver.findElements(By.xpath("//a/div[@class ='name']"));
        ArrayList<String> duckNames = new ArrayList<String>();
        for (WebElement duck: ducksByName ) {
            duckNames.add(duck.getText());
        }
       return duckNames.toString();
    }

    public String sortByPrice() {
        moveToSubcategory();
        driver.findElement(priceButton).click();

        List <WebElement> ducksByPrice = driver.findElements(By.xpath("//span[@class= 'price'] | " +
                "//strong[@class ='campaign-price']"));
        ArrayList<String> duckPrices = new ArrayList<String>();
        for (WebElement duck: ducksByPrice ) {
            duckPrices.add(duck.getText());
        }
        return duckPrices.toString();
    }
}
