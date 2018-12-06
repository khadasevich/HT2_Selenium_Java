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

    @FindBy (xpath = "//*[@id=\"uh-signin\"]")
    WebElement signinBtn;

    @FindBy (id = "login-username")
    WebElement usernameField;

    @FindBy (id = "login-passwd")
    WebElement passwordField;

    @FindBy (id = "login-signin")
    WebElement loginBth;

    @FindBy (id = "uh-mail-link")
    WebElement mailBtn;

    public MakingLogin doLogin() {
//      Method which makes login and opens inbox of the user
//      Find the Sign In button and click it
        waitClickability(signinBtn);
        signinBtn.click();
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
}

