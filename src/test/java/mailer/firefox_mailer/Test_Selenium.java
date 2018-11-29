package mailer.firefox_mailer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
    private static final String COMPOSE_BUTTON_XPATH = "//*[text()='Compose']";
    private static final String SUBJECT_FIELD_XPATH = "//*[@data-test-id='compose-subject']";
    private static final String TEXTBOX_FIELD = "//*[@id='editor-container']/div[1]";
    private static final String CLOSE_AND_SAVE_DRAFT = "//*[@data-test-id='icon-btn-close']";
    private static final String OPEN_DRAFT = "//*[@data-test-folder-name='Draft']";
    private static final String OPEN_CREATED_DRAFT_XPATH =  "//*[@title='testselenium42@yahoo.com']";
    private static final String TO_INPUT_FIELD = "//*[@id='message-to-field']";
    private static final String SEND_BUTTON = "//*[@data-test-id='compose-send-button']";
    private static final String PROFILE_BUTTON = "//*[@alt='Profile image']";
    private static final String PROFILE_TEXT = "//*[text()='Test Selenium']";
    private static final String SENT_BOX = "//*[@data-test-folder-name='Sent']";
    private static final String SENT_MESSAGE = "//*[@title='khadasevich.aleksey@gmail.com']";
    private static final String EMAIL_OF_SENT_MESSAGE = "//*[@data-test-id='message-to']";
    private static final String TEXTBOX_DESCRIPTION = "//*[text()='Wake up, Neo. The matrix has you']";
    private static final String EMAIL = "khadasevich.aleksey@gmail.com";
    private static final String SUBJECT = "Testing Selenium";
    private static final String DESCRIPTION_TEXT = "Wake up, Neo. The matrix has you";
    private static final String USERNAME = "testselenium42@yahoo.com";
    private static final String PASSWORD = "123456Qww";
    private static final String LAST_FIRST_NAME = "Test Selenium";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(description = "Start browser and setting waiting variable")
    public void startBrowser() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @BeforeClass(dependsOnMethods = "startBrowser", description = "Maximize window and check that button appeared")
    public void checkSignin() {
//      Open browser on full screen
        driver.manage().window().maximize();
//      Let's visit my mailbox
        driver.get(YAHOO_URL);
//      Let's wait till Sign in button appears
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(SIGN_IN_XPATH))));
    }

    @Test(description = "Checks successful login, creation of draft message and sending")
    public void testingYahooMailer() {
//      Run methods to login
        doLogin(USERNAME, PASSWORD);
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
        createDraft(SUBJECT, DESCRIPTION_TEXT);
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
//        Checking of the body of the message
        WebElement text_body = driver.findElement(By.xpath(TEXTBOX_DESCRIPTION));
//        String body_message = text_body.getAttribute("value");
        String body_message = text_body.getText();
//      Checking body
        Assert.assertEquals(DESCRIPTION_TEXT, body_message);
//      Let's send draft and check draft box
        sendDraft(EMAIL);
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
    }

    private void sendDraft(String email) {
//  Method which sends draft
//      Let's go to the Draft
        WebElement drafts_open = driver.findElement(By.xpath(OPEN_DRAFT));
        drafts_open.click();
//      Let's open created draft
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(OPEN_CREATED_DRAFT_XPATH))));
        WebElement open_created_draft = driver.findElement(By.xpath(OPEN_CREATED_DRAFT_XPATH));
        open_created_draft.click();
//      Let's input email
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(TO_INPUT_FIELD))));
        WebElement fill_email = driver.findElement(By.xpath(TO_INPUT_FIELD));
        fill_email.sendKeys(email);
//      Let's send our email
        WebElement send_button = driver.findElement(By.xpath(SEND_BUTTON));
        send_button.click();
    }
}




