package mailer.firefox_mailer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class Test_Selenium {


    private static final String SUBJECT_FIELD_XPATH = "//*[@data-test-id='compose-subject']";
    private static final String OPEN_DRAFT = "//*[@data-test-folder-name='Draft']";
    private static final String OPEN_CREATED_DRAFT_XPATH = "//*[@data-test-id='message-subject']";
    private static final String PROFILE_BUTTON = "//*[@alt='Profile image']";
    private static final String PROFILE_TEXT = "//*[text()='Test Selenium']";
    private static final String SENT_BOX = "//*[@data-test-folder-name='Sent']";
    private static final String SENT_MESSAGE = "//*[@title='khadasevich.aleksey@gmail.com']";
    private static final String EMAIL_OF_SENT_MESSAGE = "//*[@data-test-id='message-to']";
    private static final String TEXTBOX_DESCRIPTION = "//*[text()='Wake up, Neo. The matrix has you']";
    private static final String EMAIL = "khadasevich.aleksey@gmail.com";
    private static final String SUBJECT = "Testing Selenium";
    private static final String DESCRIPTION_TEXT = "Wake up, Neo. The matrix has you";
    private static final String LAST_FIRST_NAME = "Test Selenium";

    private WebDriver driver;

    @BeforeClass(description = "Start browser and setting waiting variable")
    public void startBrowser() {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
//      Open browser on full screen
        driver.manage().window().maximize();
//      Open main page
        new YahooMainPage(driver).openMainPage();
    }


    @Test(description = "Checks successful login, creation of draft message and sending")
    public void testingYahooMailer() {
//      Run methods to login
        new MakingLogin(driver).doLogin();
//      Let's check success of the login
        WebElement profile_button = driver.findElement(By.xpath(PROFILE_BUTTON));
        profile_button.click();
//      Let's check that username is on the page
        WebElement text_on_profile = driver.findElement(By.xpath(PROFILE_TEXT));
//      Get the message elements text
        String messageText = text_on_profile.getText();
//      Checking that first and last name are on the page
        Assert.assertEquals(LAST_FIRST_NAME, messageText);
//      Let's create draft message
        new CreateDraft(driver).draftCreate();
//      Setting delay to draft message appeared in the draft box
//      Note: In my case internal selenium implicit and explicit waits didn't help that's why I've used java delay
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      Let's check draft box
        WebElement draft_button = driver.findElement(By.xpath(OPEN_DRAFT));
        draft_button.click();
//      Let's open created draft message and check parameters
        WebElement draft_message = driver.findElement(By.xpath(OPEN_CREATED_DRAFT_XPATH));
        draft_message.click();
        WebElement text_subject = driver.findElement(By.xpath(SUBJECT_FIELD_XPATH));
        String subject = text_subject.getAttribute("value");
//      Checking subject
        Assert.assertEquals(SUBJECT, subject);
//      Checking of the body of the message
        WebElement text_body = driver.findElement(By.xpath(TEXTBOX_DESCRIPTION));
        String body_message = text_body.getText();
//      Checking body
        Assert.assertEquals(DESCRIPTION_TEXT, body_message);
//      Let's send draft and check draft box
        new SendDraft(driver).sendMessage();
        draft_button.click();
        Assert.assertTrue(isElementPresent(By.xpath(OPEN_CREATED_DRAFT_XPATH)), "Draft message was found");
//      Let's check sent box
        WebElement sent_box = driver.findElement(By.xpath(SENT_BOX));
        sent_box.click();
        WebElement sent_message = driver.findElement(By.xpath(SENT_MESSAGE));
        sent_message.click();
//      Let's check that sent message is in the sent box and has right email
        WebElement email_path = driver.findElement(By.xpath(EMAIL_OF_SENT_MESSAGE));
        String email_path_string = email_path.getText();
        Assert.assertEquals(EMAIL, email_path_string);
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
//      Closes browser after test
        driver.quit();
    }

    private boolean isElementPresent(By by) {
//  Checking absence of the element
        return driver.findElements(by).isEmpty();
    }
}





