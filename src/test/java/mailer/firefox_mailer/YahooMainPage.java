package mailer.firefox_mailer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class YahooMainPage {

    private static final String YAHOO_URL = "https://www.yahoo.com/";
//    private static final String SIGN_IN_XPATH = "//*[@id=\"uh-signin\"]";

    @FindBy (xpath = "//*[@id=\"uh-signin\"]")
    WebElement signinBtn;

    public YahooMainPage openMainPage(WebDriver driver) {
//      Let's open Yahoo main page
        driver.get(YAHOO_URL);
//      Let's wait till Sign in button appears
        new WebDriverWait (driver, 10).until(ExpectedConditions.elementToBeClickable(signinBtn));
        return this;
    }
}
