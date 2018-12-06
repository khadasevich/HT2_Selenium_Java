package mailer.firefox_mailer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendDraft extends YahooAbstractClass {
    public SendDraft (WebDriver driver) {
        super(driver);
    }


    @FindBy (xpath = "//*[@data-test-folder-name='Draft']")
    WebElement opnDrftBox;

    @FindBy (xpath = "//*[@data-test-id='message-subject']")
    WebElement crtdDraft;

    @FindBy (xpath = "//*[@data-test-id='compose-send-button']")
    WebElement sndBtn;

    public SendDraft sendMessage() {
//  Method which sends draft
//      Let's go to the Draft
        opnDrftBox.click();
//      Let's open created draft
        waitClickability(crtdDraft);
        crtdDraft.click();
//      Let's wait when send button appears
        waitClickability(sndBtn);
//      Let's send draft
        sndBtn.click();
        return this;
    }

}
