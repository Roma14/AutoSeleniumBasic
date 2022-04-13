package pageobject.patternTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePageStatic {
        private static By usernameLocator = By.xpath("//input[@name = 'email']");
        private static By passwordLocator = By.xpath("//input[@name = 'password']");
        private static By loginButtonLocator = By.xpath("//button[text() = 'Login']");
        private static By successfulLoginMessage = By.xpath("//div[@class = 'notice success']");
        private static By rubberDuckButton = By.xpath("//a[text() = 'Rubber Ducks']");

        private static WebDriver driver;

        public HomePageStatic(WebDriver driver) {
            this.driver = driver;
        }

        public static void attemptLogin(WebDriver driver, String username, String password) {
            driver.findElement(usernameLocator).sendKeys(username);
            driver.findElement(passwordLocator).sendKeys(password);
            driver.findElement(loginButtonLocator).click();
        }

        public static WebElement getSuccessfulLoginMessage() {
            return driver.findElement(successfulLoginMessage);

        }

        public static void moveToRubberDuck() {
            driver.findElement(rubberDuckButton).click();
        }

}
