package mailer.firefox_mailer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestingUtilities extends YahooAbstractClass{

    public TestingUtilities (WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@data-test-id='efv-header']")
    WebElement sntDrft;

    public String isElementPresent() {
//  Checking absence of the element
        String result = sntDrft.getText();
        return result;
    }
}

