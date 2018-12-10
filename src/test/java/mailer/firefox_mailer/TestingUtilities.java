package mailer.firefox_mailer;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestingUtilities extends YahooAbstractClass{

    public TestingUtilities (WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@data-test-id='efv-header']")
    WebElement sntDrft;

    @FindBy (xpath = "//*[@name='targetId']")
    WebElement clsButton;

    public String isElementPresent() {
//  Checking absence of the element
        waitClickability(sntDrft);
        String result = sntDrft.getText();
        return result;
    }


    public void closeOldAcc() {
        try {
            clsButton.click();
        } catch (NoSuchElementException e) {
        }

    }
}



