import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OnlineShopTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void moveToRubberDuck() {
        driver.get("https://litecart.stqa.ru/en/");
        driver.findElement(By.xpath("//a[text() = 'Rubber Ducks']")).click();
        String actulaTitle = driver.getTitle();
        assertEquals(actulaTitle, "Rubber Ducks | My Store1" );
    }

    @Test
    public void moveToDeliveryInfo() {
        driver.get("https://litecart.stqa.ru/en/");
        driver.findElement(By.xpath("//a[text() = 'Delivery Information']")).click();
        String actulaTitle = driver.getTitle();
        assertEquals(actulaTitle, "Delivery Information | My Store1" );
    }

    @Test
    public void moveToTermsAndConditions() {
        driver.get("https://litecart.stqa.ru/en/");
        driver.findElement(By.xpath("//a[text() = 'Terms & Conditions']")).click();
        String actulaTitle = driver.getTitle();
        assertEquals(actulaTitle, "Terms & Conditions | My Store1" );
    }

    private void moveToSubcategories() {
    driver.get("https://litecart.stqa.ru/en/");
    WebElement button = driver.findElement(By.xpath("//a[text() = 'Rubber Ducks']"));

    Actions builder = new Actions(driver);
    builder.moveToElement(button).perform();

    driver.findElement(By.xpath("//li/a[text() = 'Subcategory']")).click();
}

    @Test
    public void sotrByName() {
        moveToSubcategories();

        driver.findElement(By.xpath("//a[text()= 'Name']")).click();

        List <WebElement> ducksByName = driver.findElements(By.xpath("//a/div[@class ='name']"));
        ArrayList<String> duckNames = new ArrayList<String>();
        for (WebElement duck: ducksByName ) {
            duckNames.add(duck.getText());
        }
        String actualResult = duckNames.toString();
        String expectedResult = "[Green DucK, Yellow Duck, Розовая уточка]";

        assertTrue(actualResult.equals(expectedResult), String.format("Expected order of ducks after sorting: %s." +
                "\nActual result: %s",expectedResult, actualResult));
    }

    @Test
    public void sortByPrice() {
        moveToSubcategories();

        driver.findElement(By.xpath("//span[text()= 'Price']")).click();

        List <WebElement> ducksByPrice = driver.findElements(By.xpath("//span[@class= 'price'] | " +
                "//strong[@class ='campaign-price']"));
        ArrayList<String> duckPrices = new ArrayList<String>();
        for (WebElement duck: ducksByPrice ) {
            duckPrices.add(duck.getText());
        }
        String actualResult = duckPrices.toString();
        String expectedResult = "[13.14 €, 14.60 €, 65.70 €]";

        assertTrue(actualResult.equals(expectedResult), String.format("Expected order of ducks after sorting: %s." +
                "\nActual result: %s",expectedResult, actualResult));
    }

    @DataProvider(name = "DuckLabels")
    public static Object[][] getDataProvider() {
        return new Object[][] {
                {"Green DucK", "NEW"},
                {"Розовая уточка", "NEW"},
                {"Yellow Duck", "SALE"}
        };
    }

    @Test(dataProvider = "DuckLabels")
    public void checkDuckLabel(String duckName, String expectedResult) {
        moveToSubcategories();

        String actualLabel = driver.findElement(By.xpath(String.format("//img[@alt = '%s']/following-sibling::div",duckName)))
                .getText();
        assertEquals(actualLabel, expectedResult, String.format("For Green Duck expected label: %s, actual label: %s",
                expectedResult, actualLabel));
    }

}
