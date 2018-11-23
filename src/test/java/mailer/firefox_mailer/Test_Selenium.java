package mailer.firefox_mailer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Test_Selenium {

    private static final String YAHOO_URL = "https://www.yahoo.com/";
    private static final String SIGN_IN_XPATH = "//*[@id=\"uh-signin\"]";
    private static final String USERNAME_FIELD_ID = "login-username";
    private static final String USERNAME = "testselenium42@yahoo.com";
    private static final String PASSWORD = "123456Qww";
    private static final String PASSWORD_FIELD_ID = "login-passwd";
    private static final String SIGN_IN_BUTTON = "login-signin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(description = "Start browser and setting waiting variable")
    public void startBrowser() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @BeforeClass(dependsOnMethods = "startBrowser", description = "Add wait and maximize window")
    public void addWaiting() {
//      Open browser on full screen
        driver.manage().window().maximize();
//      Let's visit my mailbox
        driver.get(YAHOO_URL);
//      Let's wait till Sign in button appears
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(SIGN_IN_XPATH))));
    }

    @Test(description = "!!!!!!!!!!!!")
    public void loginToGithub() {
        // !!!!!!!!!!!!!
        doLogin(USERNAME, PASSWORD);

        // !!!!!!!!!!!
        Assert.assertTrue(isElementPresent(By.xpath("//*[@id=\"yui_3_18_0_3_1542941505979_578\"]")), "Some custom text");
    }

    private boolean isElementPresent(By by) {
        // Custom implementation for is ElementPresent
        return !driver.findElements(by).isEmpty();
    }

    private void doLogin(String username, String password) {
//        Find the Sign In button
          WebElement sign_in = driver.findElement(By.xpath(SIGN_IN_XPATH));
//        Clicking Sign In button
          sign_in.click();
//        Waiting of the login field
          wait.until(ExpectedConditions.presenceOfElementLocated((By.id(USERNAME_FIELD_ID))));
//        Find login field
          WebElement input_name = driver.findElement(By.id(USERNAME_FIELD_ID));
//        Send login name to field
          input_name.sendKeys(username);
//        Submitting login name
          input_name.submit();
//        Waiting of the password field
          wait.until(ExpectedConditions.presenceOfElementLocated((By.id(PASSWORD_FIELD_ID))));
//        Find password field
          WebElement input_password = driver.findElement(By.id(PASSWORD_FIELD_ID));
//        Send password to field
          input_password.sendKeys(password);
//        Submitting password
          WebElement main_sign_in = driver.findElement(By.id(SIGN_IN_BUTTON));
          main_sign_in.click();
//        Wait 5 seconds
          driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      Closing browser
////        driver.close();
    }
}




