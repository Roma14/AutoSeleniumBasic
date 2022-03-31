package pageobject.patternTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

public class HomePageFactory {

        @FindBy(xpath = "//input[@name = 'email']")
        private WebElement usernameInput;

        @FindBy(xpath = "//input[@name = 'password']")
        private WebElement passwordInput;

        @FindBy(xpath = "//button[text() = 'Login']")
        private WebElement loginButton;

        @FindBy(xpath = "//div[@class = 'notice success']")
        private WebElement noticeMessage;

        @FindBy(xpath = "//a[text() = 'Rubber Ducks']")
        private WebElement rubberDuckButton;


    public void attemptLogin(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }


    public WebElement getSuccessfulLoginMessage() {
        return noticeMessage;

    }

    public void moveToRubberDuck() {
        rubberDuckButton.click();
    }

}
