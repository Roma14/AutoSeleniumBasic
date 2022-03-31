package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageobject.helpers.Locators;

public class HomePage {
    private By usernameLocator = Locators.getLocator("HomePage.username");
    private By passwordLocator = Locators.getLocator("HomePage.password");
    private By loginButtonLocator = Locators.getLocator("HomePage.loginButton");
    private By successfulLoginMessage = Locators.getLocator("HomePage.successfulLoginMessage");
    private By rubberDuckButton = Locators.getLocator("HomePage.rubberDuckButton");
    private By deliveryInfoButton = Locators.getLocator("HomePage.deliveryInfoButton");
    private By termsConditionsButton = Locators.getLocator("HomePage.termsConditionsButton");
    private By subCategory = Locators.getLocator("HomePage.subCategory");

    private final WebDriver driver;

    public HomePage(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    public void attemptLogin(String username, String password) {
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }

    public WebElement getSuccessfulLoginMessage() {
        return driver.findElement(successfulLoginMessage);

    }

    public void moveToRubberDuck() {
        driver.findElement(rubberDuckButton).click();
    }

    public void moveToDeliveryInfo() {
        driver.findElement(deliveryInfoButton).click();
    }
    public void moveToTermsAndConditions() {
        driver.findElement(termsConditionsButton).click();
    }

    public void moveToSubcategory() {
        WebElement button = driver.findElement(rubberDuckButton);
        Actions builder = new Actions(driver);
        builder.moveToElement(button).perform();
        driver.findElement(subCategory).click();
    }

}
