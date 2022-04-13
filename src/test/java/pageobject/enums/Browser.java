package pageobject.enums;

public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox");

    private String BrowserName;

    private Browser(String BrowserName) {
        this.BrowserName = BrowserName;
    }
}
