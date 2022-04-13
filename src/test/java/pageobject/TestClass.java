package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.pages.HomePage;
import pageobject.pages.SubcategoryPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestClass extends TestBase {

    @Test
    public void successfulLoginTest() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.attemptLogin("romapetrikevich1995@gmail.com", "12345!");

        WebElement successfulLoginMessage = homePage.getSuccessfulLoginMessage();
        String expectedResult = "You are now logged in as Roman Petrikevich.";

        Assert.assertEquals(successfulLoginMessage.getText(), expectedResult);
    }

    @Test
    public void  moveToRubberDuck() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.moveToRubberDuck();
        String actualTitle = driver.getTitle();

        assertEquals(actualTitle, "Rubber Ducks | My Store1" );
    }

    @Test
    public void moveToDeliveryInfo() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.moveToDeliveryInfo();
        String actualTitle = driver.getTitle();

        assertEquals(actualTitle, "Delivery Information | My Store1" );
    }

    @Test
    public void moveToTermsAndConditions() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.moveToTermsAndConditions();
        String actualTitle = driver.getTitle();

        assertEquals(actualTitle, "Terms & Conditions | My Store1" );
    }

    @Test
    public void successfulSotrByName() throws Exception {
        SubcategoryPage subPage = new SubcategoryPage(driver);

        String actualResult = subPage.sortByName();
        String expectedResult = "[Green DucK, Yellow Duck, Розовая уточка]";

        assertTrue(actualResult.equals(expectedResult), String.format("Expected order of ducks after sorting: %s." +
                "\nActual result: %s",expectedResult, actualResult));
    }

    @Test
    public void successfulSortByPrice() throws Exception {
        SubcategoryPage subPage = new SubcategoryPage(driver);

        String actualResult = subPage.sortByPrice();
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
    public void checkDuckLabel(String duckName, String expectedResult) throws Exception {
        SubcategoryPage subPage = new SubcategoryPage(driver);
        subPage.moveToSubcategory();

        String actualLabel = driver.findElement(By.xpath(String.format("//img[@alt = '%s']/following-sibling::div",duckName)))
                .getText();
        assertEquals(actualLabel, expectedResult, String.format("For %s expected label: %s, actual label: %s", duckName,
                expectedResult, actualLabel));
    }
}
