package mailer.firefox_mailer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateDraft extends YahooAbstractClass {

    public CreateDraft (WebDriver driver) {
        super(driver);
    }

    private static final String EMAIL = "khadasevich.aleksey@gmail.com";
    private static final String SUBJECT = "Testing Selenium";
    private static final String DESCRIPTION_TEXT = "Wake up, Neo. The matrix has you";

    @FindBy (xpath = "//*[@id='message-to-field']")
    WebElement emailFiled;

    @FindBy (xpath = "//*[text()='Compose']")
    WebElement cmpsBtn;

    @FindBy (xpath = "//*[@data-test-id='compose-subject']")
    WebElement subjectField;

    @FindBy (xpath = "//*[@id='editor-container']/div[1]")
    WebElement txtbxField;

    @FindBy (xpath = "//*[@data-test-id='icon-btn-close']")
    WebElement clssvBtn;

    public CreateDraft draftCreate() {
//      Method which creates new draft
//      Let's find compose button and click it
        waitClickability(cmpsBtn);
        cmpsBtn.click();
//      Wait until input fields appear
        waitClickability(subjectField);
//      Filling email field
        emailFiled.sendKeys(EMAIL);
//      Filling Subject field
        subjectField.sendKeys(SUBJECT);
//      Filling text box
        txtbxField.sendKeys(DESCRIPTION_TEXT);
//      Closing and saving as draft
        clssvBtn.click();
        return this;
    }
}
