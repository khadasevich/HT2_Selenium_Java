package mailer.firefox_mailer;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Test_Selenium extends TestBase{


    private static final String EMPTY_DRAFT = "Your \"Drafts\" folder is empty\n" +
            "Catch up on the most popular videos on Yahoo";
    private static final String EMAIL = "khadasevich.aleksey@gmail.com";
    private static final String SUBJECT = "Testing Selenium";
    private static final String DESCRIPTION_TEXT = "Wake up, Neo. The matrix has you";
    private static final String LAST_FIRST_NAME = "Test Selenium";



    @Test(priority = 1, description = "Checks successful login")
    public void testingLogin() {
//      Run methods to login
        MakingLogin makeLogin = new MakingLogin(driver);
        makeLogin.doLogin();
//      Let's check success of the login
//      Let's check that username is on the page
        String messageText = makeLogin.openProfile();
//      Checking that first and last name are on the page
        Assert.assertEquals(LAST_FIRST_NAME, messageText);
    }

    @Test (priority = 2, description = "Checks creation of the draft message")
    public void testingDrafts() {
//      Run methods to login
        MakingLogin makeLogin = new MakingLogin(driver);
        makeLogin.doLogin();
//      Let's create draft message
        CreateDraft createDraft = new CreateDraft(driver);
        createDraft.draftCreate();
//      Setting delay to draft message appeared in the draft box
//      Note: In my case internal selenium implicit and explicit waits didn't help that's why I've used java delay
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      Let's check draft box
//      Let's open created draft message and check parameters
        String[] results = createDraft.takeParamDraft();
//      Let's clean environment for future tests
        createDraft.deleteDraft();
//      Checking subject
        String subject = results[0];
        Assert.assertEquals(SUBJECT, subject);
//      Checking of the body of the message
        String body_message = results[1];
//      Checking body
        Assert.assertEquals(DESCRIPTION_TEXT, body_message);
    }

    @Test (priority = 3, description = "Sends draft and checks sending")
    public void testingSending() {
//      Run methods to login
        MakingLogin makeLogin = new MakingLogin(driver);
        makeLogin.doLogin();
//      Let's create draft message
        CreateDraft createDraft = new CreateDraft(driver);
        createDraft.draftCreate();
//      Setting delay to draft message appeared in the draft box
//      Note: In my case internal selenium implicit and explicit waits didn't help that's why I've used java delay
        try {
        TimeUnit.SECONDS.sleep(20);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
//      Let's send draft and check draft box
        SendDraft sendDraft = new SendDraft(driver);
        sendDraft.sendMessage();
        createDraft.openDraft();
        TestingUtilities testUtl = new TestingUtilities(driver);
        String draftMsg = testUtl.isElementPresent();
        Assert.assertEquals(EMPTY_DRAFT, draftMsg);
//      Let's check sent box
        sendDraft.openSntBox();
        sendDraft.openSntMsg();
//      Let's check that sent message is in the sent box and has right email
        String email_path_string = sendDraft.getParamOfSnt();
        Assert.assertEquals(EMAIL, email_path_string);
    }
}





