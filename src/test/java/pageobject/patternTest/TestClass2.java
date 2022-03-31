package pageobject.patternTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.pages.HomePage;
import pageobject.patternTest.pages.HomePageFactory;
import pageobject.patternTest.pages.HomePageStatic;

import static org.testng.Assert.assertEquals;

public class TestClass2 {

    @Test
    public void successfulLoginTestStatic() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://litecart.stqa.ru/en/");
        driver.manage().window().maximize();

        HomePageStatic.attemptLogin(driver, "romapetrikevich1995@gmail.com", "12345!");

        WebElement successfulLoginMessage = HomePageStatic.getSuccessfulLoginMessage();
        String expectedResult = "You are now logged in as Roman Petrikevich.";

        Assert.assertEquals(successfulLoginMessage.getText(), expectedResult);

        driver.quit();
    }

    @Test
    public void moveToRubberDuckStatic() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://litecart.stqa.ru/en/");
        driver.manage().window().maximize();

        HomePageStatic.moveToRubberDuck();
        String actualTitle = driver.getTitle();

        assertEquals(actualTitle, "Rubber Ducks | My Store1");

        driver.quit();
    }

    @Test
    public void successfulLoginTesFactory() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://litecart.stqa.ru/en/");
        driver.manage().window().maximize();

        HomePageFactory loginPage = PageFactory.initElements(driver, HomePageFactory.class);
        loginPage.attemptLogin("romapetrikevich1995@gmail.com", "12345!");

        WebElement successfulLoginMessage = loginPage.getSuccessfulLoginMessage();
        String expectedResult = "You are now logged in as Roman Petrikevich.";

        Assert.assertEquals(successfulLoginMessage.getText(), expectedResult);

        driver.quit();
    }

    @Test
    public void moveToRubberDuckFactory() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://litecart.stqa.ru/en/");
        driver.manage().window().maximize();

        HomePageFactory loginPage = PageFactory.initElements(driver, HomePageFactory.class);
        loginPage.moveToRubberDuck();
        String actualTitle = driver.getTitle();

        assertEquals(actualTitle, "Rubber Ducks | My Store1");

        driver.quit();
    }
}