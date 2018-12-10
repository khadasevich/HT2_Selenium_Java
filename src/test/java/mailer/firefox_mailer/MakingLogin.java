package mailer.firefox_mailer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MakingLogin extends YahooAbstractClass {

    public MakingLogin (WebDriver driver) {
        super(driver);
    }

    private static final String USERNAME = "testselenium42@yahoo.com";
    private static final String PASSWORD = "123456Qww";

    @FindBy (id = "uh-signin")
    WebElement signinBtn;

    @FindBy (id = "login-username")
    WebElement usernameField;

    @FindBy (id = "login-passwd")
    WebElement passwordField;

    @FindBy (id = "login-signin")
    WebElement loginBth;

    @FindBy (id = "uh-mail-link")
    WebElement mailBtn;

    @FindBy (xpath = "//*[@alt='Profile image']")
    WebElement prflBtn;

    @FindBy (xpath = "//*[text()='Test Selenium']")
    WebElement prfText;

    @FindBy (xpath = "//*[@data-test-id='search-box']")
    WebElement inptFld;

    @FindBy (xpath = "/html/body/header/div/div[3]/div[1]/div/div/div/a[3]/span")
    WebElement lgtBtn;

    public MakingLogin doLogin() {
//      Method which makes login and opens inbox of the user
//      Find the Sign In button and click it
        waitClickability(signinBtn);
        signinBtn.click();
//      Checking whether new session is using
        TestingUtilities testUtl = new TestingUtilities(driver);
        testUtl.closeOldAcc();
//      Find login field and submit user name
        waitClickability(usernameField);
        usernameField.sendKeys(USERNAME);
        usernameField.submit();
//      Find password field and submit password
        waitClickability(passwordField);
        passwordField.sendKeys(PASSWORD);
        loginBth.click();
//      Opening of the mail box
        waitClickability(mailBtn);
        mailBtn.click();
        return this;
    }

    public MakingLogin openProfileCard() {
//  Opens profile ticket for tests
        waitClickability(prflBtn);
        prflBtn.click();
        return this;
    }

    public String openProfile() {
//      Gets login info
        openProfileCard();
        String messageText = prfText.getText();
        return messageText;
    }

    public MakingLogin logOut() {
//      Makes logout after tests
        waitClickability(inptFld);
        inptFld.click();
        openProfileCard();
        waitClickability(lgtBtn);
        lgtBtn.click();
        return this;
    }
}

