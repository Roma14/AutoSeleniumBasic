import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class FrameTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/frames");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void ckeckIframeText() {
        driver.findElement(By.linkText("iFrame")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.switchTo().frame("mce_0_ifr");

        String expectedResult = "Your content goes here.";
        String actualText = driver.findElement(By.xpath("//body/p")).getText();

        Assert.assertEquals(actualText, expectedResult);

        driver.switchTo().defaultContent();
    }

    @BeforeGroups(groups = "NestedFrames")
    public void moveToTopFrames() {
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.switchTo().frame("frame-top");
    }

    @AfterGroups(groups = "NestedFrames")
    public void moveToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    @Test(groups = "NestedFrames")
    public void ckeckLeftFrames() {
        driver.switchTo().frame("frame-left");
        WebElement body = driver.findElement(By.tagName("body"));

        Assert.assertEquals(body.getText(), "LEFT");
    }

    @Test(groups = "NestedFrames")
    public void ckeckMiddleFrame() {
        driver.switchTo().frame("frame-middle");
        WebElement body = driver.findElement(By.id("content"));

        Assert.assertEquals(body.getText(), "MIDDLE");
    }

    @Test(groups = "NestedFrames")
    public void ckeckRightFrames() {
        driver.switchTo().frame("frame-right");
        WebElement body = driver.findElement(By.tagName("body"));

        Assert.assertEquals(body.getText(), "RIGHT");
    }
}
