import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class DragAndDropTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void dragNDrop() {
        driver.get("https://learn.javascript.ru/article/mouse-drag-and-drop/ball4/");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        WebElement ball = driver.findElement(By.id("ball"));
        WebElement gate = driver.findElement(By.id("gate"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(ball,gate).perform();

        String gateColor = gate.getCssValue("background-color");
        Assert.assertEquals(gateColor, "rgba(255, 192, 203, 1)" );
    }

}
