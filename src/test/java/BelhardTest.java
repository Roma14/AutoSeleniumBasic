import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BelhardTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://belhard.academy/");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void newTabCheck() {
        String initHandle = driver.getWindowHandle();

        driver.findElement(By.cssSelector("[href = 'https://belhard.academy/ru']")).click();
        driver.findElement(By.cssSelector(".t393")).click();

        String newHandle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newHandle);

        String telNumber = driver.findElement(By.cssSelector(".tn-atom[field*='tn_text'] a")).getText();
        Assert.assertEquals(telNumber, "+7 (901) 369 94 96");

//        driver.close();
//        driver.switchTo().window(initHandle);
//
//        WebElement closePopupBt = driver.findElement(By.id("jivo_close_button"));
//        WebElement popup = driver.findElement(By.id("jivo_action"));
//
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        wait.until(ExpectedConditions.attributeContains(popup, "class", "show"));
//
//        closePopupBt.click();
//        String attributeValue = popup.getAttribute("class");
//
//        Assert.assertFalse(attributeValue.contains("__show"));
    }

}
