package mailer.firefox_mailer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Test_Selenium  {
    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();

//      Open browser on full screen
        driver.manage().window().maximize();

//      Let's visit my mailbox
        driver.get("https://www.yahoo.com/");

//      Wait 5 sec till page open
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      Find the Sign In button
        WebElement sign_in = driver.findElement(By.xpath("//*[@id=\"uh-signin\"]"));

//      Clicking Sign In button
        sign_in.click();

//      Wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      Find password field
        WebElement input_name = driver.findElement(By.id("login-username"));

//      Send login name to field
        input_name.sendKeys("testselenium42@yahoo.com");

//      Submitting login name
        input_name.submit();

//      Wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      Find password field
        WebElement input_password = driver.findElement(By.id("login-passwd"));

//      Send password to field
        input_password.sendKeys("123456Qww");

//      Submitting password
        WebElement main_sign_in = driver.findElement(By.id("login-signin"));
        main_sign_in.click();

//      Wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      Closing browser
//        driver.close();
    }
}




        // Enter something to search for
//        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
//        element.submit();

        // Check the title of the page
//        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getTitle().toLowerCase().startsWith("cheese!");
//            }
