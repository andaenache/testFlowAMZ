package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

public class SignInPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign-In\")")
    protected WebElement weSignInRadio;

    @AndroidFindBy(xpath = "//*[@resource-id='ap_email_login']")
    protected WebElement weEmailAddressField;

    @AndroidFindBy(xpath = "//*[@resource-id='continue']")
    protected WebElement weContinueButton;

    @AndroidFindBy(xpath = "//*[@resource-id='ap_password']")
    protected WebElement wePasswordField;

    @AndroidFindBy(xpath = "//*[@resource-id='signInSubmit']")
    protected WebElement weSubmitSignIn;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Not now\")")
    protected WebElement weNotNow;

    public void enterEmailAddress(String email) {
        clickElementAfterWait(weEmailAddressField);
        setTextInField(weEmailAddressField, email);
    }

    public void enterPassword(String pass) {
        clickElementAfterWait(wePasswordField);
        setTextInField(wePasswordField, pass);
    }

    public void signInE2E(String email, String pass) {
        clickElementAfterWait(weSignInRadio);
        enterEmailAddress(email);
        clickElementAfterWait(weContinueButton);
        enterPassword(pass);
        clickElementAfterWait(weSubmitSignIn);
        if (waitForElementToBeVisible(weNotNow)) {
            clickElementAfterWait(weNotNow);
        }
    }

    public SignInPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
