package mailer.firefox_mailer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YahooAbstractClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public YahooAbstractClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

//  Basic waiting method
//    public void waitClickability(WebElement element) {
//        wait.until(ExpectedConditions.(element));
//    }

//  Basic click method
//    public void click (By elementBy) {
//        waitVisibility(elementBy);
//        driver.findElement(elementBy).click();
//    }

//  Basic click method
    public void click (WebElement element) {
        element.click();
    }

//  Basic write text method
//    public void writeText (By elementBy, String text) {
//        waitVisibility(elementBy);
//        driver.findElement(elementBy).sendKeys(text);
//    }

}
