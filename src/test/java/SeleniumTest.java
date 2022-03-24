
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
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
    public void findLinks() {
        driver.get("https://the-internet.herokuapp.com");
        List <WebElement> linkElements = driver.findElements(By.tagName("a"));
        linkElements.get(8).click();
    }

    @Test
    public void doubleClick() {
        driver.get("http://www.pbclibrary.org/mousing/click3.htm");

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement icon = driver.findElement(By.xpath("//img[@src][@ondblclick]"));

        Actions builder = new Actions(driver);
        builder.doubleClick(icon).perform();
    }

}
