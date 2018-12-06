package mailer.firefox_mailer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YahooAbstractClass {

    protected WebDriver driver;


    public YahooAbstractClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void waitClickability(WebElement element) {
//  Basic waiting method
        new WebDriverWait (driver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click (WebElement element) {
//  Basic click method
        waitClickability(element);
        element.click();
    }

    public void writeText(WebElement element, String text) {
//  Basic write text method
        waitClickability(element);
        element.sendKeys(text);
    }

    public void submitText(WebElement element) {
//  Basic submitting method
        waitClickability(element);
        element.submit();
    }

}
