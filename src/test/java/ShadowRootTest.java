import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ShadowRootTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://canvas.apps.chrome/");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        WebElement drawingApp = (WebElement) getShadowRoot(driver, driver.findElement(By.id("drawing-app")));
        WebElement welcomeDialog = (WebElement) getShadowRoot(driver,drawingApp.findElement(By.cssSelector(
                "test-state = 'open'")));

        String initHandle = driver.getWindowHandle();
        welcomeDialog.findElement(By.id("get-started")).click();

        String newHandle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newHandle);

        driver.close();
        driver.switchTo().window(initHandle);

    }

    private SearchContext getShadowRoot(WebDriver driver, WebElement root) {
        return (SearchContext) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", root);
    }

    @Test
    public void ShadowRootTest() {
        // to do
    }
}
