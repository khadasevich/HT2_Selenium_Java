package mailer.firefox_mailer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public WebDriver driver;

    @BeforeClass(description = "Start browser and setting waiting variable")
    public void startBrowser() {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
//      Open browser on full screen
        driver.manage().window().maximize();
//      Open main page
        new YahooMainPage(driver).openMainPage();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
//      Closes browser after test
        driver.quit();
    }

}
