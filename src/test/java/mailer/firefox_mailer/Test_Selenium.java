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
    private static final String PASSWORD_FIELD_ID = "login-passwd";
    private static final String SIGN_IN_BUTTON = "login-signin";
    private static final String OPEN_EMAIL_BUTTON = "uh-mail-link";
    private static final String COMPOSE_BUTTON_XPATH = "//*[@id=\"app\"]/div[1]/div/div[1]/nav/div/div[1]/a";
    private static final String SUBJECT_FIELD_XPATH = "//*[@id=\"mail-app-component\"]/div/div/div[1]/div[3]/div/div/input";
    private static final String TEXTBOX_FIELD = "//*[@id=\"editor-container\"]/div[1]";
    private static final String CLOSE_AND_SAVE_DRAFT = "//*[@id=\"mail-app-component\"]/div/div/div[1]/div[1]/span/button";

    private static final String EMAIL = "khadasevich.aleksey@gmail.com";
    private static final String SUBJECT = "Testing Selenium";
    private static final String DESCRIPTION_TEXT = "Wake up, Neo. The matrix has you";
    private static final String USERNAME = "testselenium42@yahoo.com";
    private static final String PASSWORD = "123456Qww";

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
        createDraft(SUBJECT, DESCRIPTION_TEXT);
        sendDraft(EMAIL);

        // !!!!!!!!!!!
        Assert.assertTrue(isElementPresent(By.xpath("//*[@id=\"yui_3_18_0_3_1542941505979_578\"]")), "Some custom text");
    }

    private boolean isElementPresent(By by) {
        // Custom implementation for is ElementPresent
        return !driver.findElements(by).isEmpty();
    }

    private void doLogin(String username, String password) {
//      Method which makes login and opens inbox of the user
//      Find the Sign In button
        WebElement sign_in = driver.findElement(By.xpath(SIGN_IN_XPATH));
//      Clicking Sign In button
        sign_in.click();
//      Waiting of the login field
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id(USERNAME_FIELD_ID))));
//      Find login field
        WebElement input_name = driver.findElement(By.id(USERNAME_FIELD_ID));
//      Send login name to field
        input_name.sendKeys(username);
//      Submitting login name
        input_name.submit();
//      Waiting of the password field
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id(PASSWORD_FIELD_ID))));
//      Find password field
        WebElement input_password = driver.findElement(By.id(PASSWORD_FIELD_ID));
//      Send password to field
        input_password.sendKeys(password);
//      Submitting password
        WebElement main_sign_in = driver.findElement(By.id(SIGN_IN_BUTTON));
        main_sign_in.click();
//      Waiting 'Mail' icon appearing
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id(OPEN_EMAIL_BUTTON))));
//      Opening of the mailbox
        WebElement mail_button = driver.findElement(By.id(OPEN_EMAIL_BUTTON));
        mail_button.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }
    private void createDraft(String subject, String text) {
//      Method which creates new draft
//      Let's find compose button
        WebElement compose_button = driver.findElement(By.xpath(COMPOSE_BUTTON_XPATH));
        compose_button.click();
//      Wait until input fields appear
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(SUBJECT_FIELD_XPATH))));
//      Filling Subject field
        WebElement subject_field = driver.findElement(By.xpath(SUBJECT_FIELD_XPATH));
        subject_field.sendKeys(subject);
//      Filling textbox
        WebElement text_box = driver.findElement(By.xpath(TEXTBOX_FIELD));
        text_box.sendKeys(text);
//      Closing and saving as graft
        WebElement cross_button = driver.findElement(By.xpath(CLOSE_AND_SAVE_DRAFT));
        cross_button.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }
    private void sendDraft(String email) {
//  Method which sends draft
    }
}




