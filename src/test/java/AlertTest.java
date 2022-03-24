import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AlertTest {
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
    public void alertTest() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text() = 'Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();
        String alerText = alert.getText();
        Assert.assertEquals(alerText, "I am a JS Alert");
        alert.accept();
    }

    @Test
    public void confirmAlertTest() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String alertResult = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(alertResult, "You clicked: Cancel");
    }

    @Test
    public void promptmAlertTest() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text() = 'Click for JS Prompt']")).click();
        Alert alert = driver.switchTo().alert();
        String enteredText = "Test";
        alert.sendKeys(enteredText);
        alert.accept();
        String alertResult = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(alertResult, String.format("You entered: %s",enteredText));
    }
}
