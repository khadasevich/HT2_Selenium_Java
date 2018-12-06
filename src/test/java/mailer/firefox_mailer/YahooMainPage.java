package mailer.firefox_mailer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class YahooMainPage extends YahooAbstractClass {

    public YahooMainPage (WebDriver driver) {
        super(driver);
    }

    private static final String YAHOO_URL = "https://www.yahoo.com/";

    @FindBy (xpath = "//*[@id=\"uh-signin\"]")
    WebElement signinBtn;

    public YahooMainPage openMainPage() {
//      Let's open Yahoo main page
        driver.get(YAHOO_URL);
//      Let's wait till Sign in button appears
        waitClickability(signinBtn);
        return this;
    }
}
