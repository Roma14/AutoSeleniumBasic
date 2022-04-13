package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageobject.enums.Browser;

public class TestBase {
    protected WebDriver driver;
    protected final String baseUrl = "https://litecart.stqa.ru/en/";

    @BeforeTest
    public void setup() {

        Browser browserName = Browser.valueOf(System.getProperty("browser",
                Browser.CHROME.toString()));

        switch (browserName) {
            case CHROME: driver = new ChromeDriver();
                break;
            case FIREFOX: driver = new FirefoxDriver();
                break;
            default: driver = new ChromeDriver();
                break;
        }

        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
